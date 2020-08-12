
- 32bit

```
ml msgbox.asm /link /subsystem:windows /Entry:Start kernel32.lib user32.lib
```

- 64bit

```
ml64 msgbox64.asm /link /subsystem:windows /entry:Start "C:\Program Files (x86)\Microsoft SDKs\Windows\v7.1A\Lib\x64\kernel32.lib" "C:\Program Files (x86)\Microsoft SDKs\Windows\v7.1A\Lib\x64\user32.lib"
```
