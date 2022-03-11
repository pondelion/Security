### SignUp user

https://auth0.com/docs/api/authentication#signup

```bash
curl --request POST --url 'https://[DOMAIN_NAME]/dbconnections/signup' --header 'content-type: application/json' --data '{"client_id":"*********", "email":"******@gmail.com", "password":"****", "connection":"Username-Password-Authentication", "username": "test_user_name", "given_name": "P", "family_name": "D", "name": "P D", "nickname": "pd", "picture": "http://example.org/jdoe.png"}'
```


### Get access token by username(email) and password

```bash
$ curl --request POST --url 'https://[DOMAIN_NAME]/oauth/token' --header 'content-type: application/x-www-form-urlencoded' --data grant_type=password --data username=**********@gmail.com --data password=******** --data scope=read:sample --data 'client_id=*****************' --data client_secret=********************
```


### Get management API access token

```bash
curl --request POST \
  --url https://[DOMAIN_NAME]/oauth/token \
  --header 'content-type: application/json' \
  --data '{"client_id":"***********","client_secret":"**********","audience":"https://[DOMAIN_NAME]/api/v2/","grant_type":"client_credentials"}'
```

### Get user info using management API


### Get user info

```bash
$ curl https://dev-0ir8e386.us.auth0.com/api/v2/users/[USER_ID] -H 'Content-Type:application/json' -H "Authorization: Bearer ${TOKEN}" | jq .
```

---

## Via FastAPi server (server.py)

### Get access token

```bash
$ curl localhost:8000/token?username=**********@gmail.com\&password=******** | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1303  100  1303    0     0   1239      0  0:00:01  0:00:01 --:--:--  1239
{
  "access_token": "****************.*********.*************",
  "scope": "read:current_user update:current_user_metadata delete:current_user_metadata create:current_user_metadata create:current_user_device_credentials delete:current_user_device_credentials update:current_user_identities",
  "expires_in": 86400,
  "token_type": "Bearer"
}
```

```bash
$ TOKEN=*****
```

### Get secret data

```bash
$ curl localhost:8000/private -H 'Content-Type:application/json' -H "Authorization: Bearer ${TOKEN}" | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   495  100   495    0     0    389      0  0:00:01  0:00:01 --:--:--   389
{
  "message": "This is secret data",
  "your_data": {
    "iss": "https://*******.us.auth0.com/",
    "sub": "auth0|62****",
    "aud": "https://*******.us.auth0.com/api/v2/",
    "iat": 1646945117,
    "exp": 1647031517,
    "azp": "**********",
    "scope": "read:current_user update:current_user_metadata delete:current_user_metadata create:current_user_metadata create:current_user_device_credentials delete:current_user_device_credentials update:current_user_identities",
    "gty": "password"
  }
}
```

### Get role of calling user

```bash
$ curl localhost:8000/role -H 'Content-Type:application/json' -H "Authorization: Bearer ${TOKEN}" | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    72  100    72    0     0     26      0  0:00:02  0:00:02 --:--:--    26
[
  {
    "id": "rol_HzAp*******",
    "name": "test_role2",
    "description": "test"
  }
]
```

### Signup user

```bash
$ curl -X POST localhost:8000/user -H 'Content-Type:application/json' -d '{"username": "ajaafsdf", "password": "pswd", "email": "sadkjadowifwo@gmail.com"}' | jq .
{
  "_id": "62**************",
  "email_verified": false,
  "email": "sadkjadowifwo@gmail.com"
}
```