
- 64bit build

```bash
$ gcc write_memory.c -o write_memory
$ gcc count.c -o count
```

- run count process

```bash
$ ./count
0x7ffe53102fa4 : 21
0x7ffe53102fa4 : 22
0x7ffe53102fa4 : 23
0x7ffe53102fa4 : 24
....
```

- write count process memory from write_memory

```bash
$ ps -A | grep count
12092 pts/6    00:00:00 count
12701 pts/6    00:00:00 count

$ sudo ./write_memory 12701 0x7ffe53102fa4 7777
```
