
- 32bit build

```bash
$ gcc write_memory.c -o write_memory -m32
$ gcc count.c -o count -m32
```

- run count process

```bash
$ ./count
0xffd37408 : 21
0xffd37408 : 22
0xffd37408 : 23
0xffd37408 : 24
....
```

- write count process memory from write_memory

```bash
$ ps -A | grep count
12092 pts/6    00:00:00 count
12537 pts/6    00:00:00 count

$ sudo ./write_memory 12537 0xffd37408 7777
```

- memo

You have to use `lseek64` instead of `lseek` when compiling 32bit binary.  
