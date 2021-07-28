
- build

```bash
$ aarch64-linux-gnu-gcc write_memory.c -o write_memory_arm64 -static
```

- push executable binary to android

```bash
$ adb push write_memory_arm64 /data/local/tmp
```

- check victim app PID

```bash
$ adb shell
$ ps -A | grep jni
```

- execute

```bash
$ su
# cd /data/local/tmp
# chmod 777 write_memory_arm64
# ./write_memory_arm64 [PID] [ADDRESS ON ANDROID TO WRITE] [VALUE TO WRITE]
```