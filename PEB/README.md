
### Get PEB via windows API

- complile & execution

```
> cl .\peb.cc
> .\peb.exe  
x64
DllName : C:\github\Security\PEB\peb.exe
==========================================================
BaseAddress : 00007FFC8E840000
==========================================================
BaseAddress : 00007FFC8DCF0000
DllName : C:\WINDOWS\System32\KERNEL32.DLL
==========================================================
BaseAddress : 00007FFC8B8C0000
DllName : C:\WINDOWS\System32\KERNELBASE.dll
==========================================================
BaseAddress : 0000000000000000
DllName :
```

---

### Get PEB via assembly code

- complile & execution

```
> ml64 -c ./peb64_asm.asm
> cl -c ./peb64_asm_call.cc
> link .\peb64_asm_call.obj .\peb64_asm.obj
> .\peb64_asm_call.exe
BaseAddress : 00007FF656160000
DllName : C:\github\Security\PEB\peb64_asm_call.exe
==========================================================
BaseAddress : 00007FFC8E840000
DllName : C:\WINDOWS\SYSTEM32\ntdll.dll
==========================================================
BaseAddress : 00007FFC8DCF0000
DllName : C:\WINDOWS\System32\KERNEL32.DLL
==========================================================
BaseAddress : 00007FFC8B8C0000
DllName : C:\WINDOWS\System32\KERNELBASE.dll
==========================================================
BaseAddress : 0000000000000000
DllName :
```
