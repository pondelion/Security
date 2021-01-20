- build & execute simple_buffer_overflow_func

```
$ g++ simple_buffer_overflow_func.cc -o simple_buffer_overflow_func -fno-stack-protector -fpermissive
$ ./simple_buffer_overflow_func 
func called
```

```
$ file simple_buffer_overflow_func
simple_buffer_overflow_func: ELF 64-bit LSB shared object, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, BuildID[sha1]=c837895cd4c890931daf6cf349b3824fc28173d2, for GNU/Linux 3.2.0, not stripped
```

- build & execute simple_buffer_overflow_shellcode

```
$ g++ simple_buffer_overflow_shellcode.cc -o simple_buffer_overflow_shellcode -fno-stack-protector -z execstack -fpermissive
$ ./simple_buffer_overflow_shellcode
Hello, World!
```

---

- find the stack address where return address from main func is stored.

```
$ gdb ./simple_buffer_overflow_func

gdb-peda$ b main
gdb-peda$ run
gdb-peda$ disas
Dump of assembler code for function main:
   0x00005555555551ab <+0>:     push   rbp
   0x00005555555551ac <+1>:     mov    rbp,rsp
=> 0x00005555555551af <+4>:     sub    rsp,0x30
   0x00005555555551b3 <+8>:     mov    QWORD PTR [rbp-0x30],0x0
   0x00005555555551bb <+16>:    mov    QWORD PTR [rbp-0x28],0x2
   0x00005555555551c3 <+24>:    mov    QWORD PTR [rbp-0x20],0x4
   0x00005555555551cb <+32>:    mov    QWORD PTR [rbp-0x18],0x6
   0x00005555555551d3 <+40>:    mov    QWORD PTR [rbp-0x10],0x8
   0x00005555555551db <+48>:    call   0x555555555175 <_Z4funcv>
   0x00005555555551e0 <+53>:    lea    rax,[rbp-0x30]
   0x00005555555551e4 <+57>:    add    rax,0x20
   0x00005555555551e8 <+61>:    add    rax,0x18
   0x00005555555551ec <+65>:    lea    rdx,[rip+0xffffffffffffff82]        # 0x555555555175 <_Z4funcv>
   0x00005555555551f3 <+72>:    mov    QWORD PTR [rax],rdx
   0x00005555555551f6 <+75>:    mov    eax,0x0
   0x00005555555551fb <+80>:    leave  
   0x00005555555551fc <+81>:    ret    
End of assembler dump.

gdb-peda$ x/10xg $rbp-0x30
0x7fffffffde60: 0x00007ffff7fe2b20      0x0000000000000000
0x7fffffffde70: 0x0000555555555260      0x0000555555555090
0x7fffffffde80: 0x00007fffffffdf70      0x0000000000000000
0x7fffffffde90: 0x0000555555555260      0x00007ffff7c0cb6b
0x7fffffffdea0: 0xffffffffffffff90      0x00007fffffffdf78

gdb-peda$ ni 5
gdb-peda$ disas
Dump of assembler code for function main:
   0x00005555555551ab <+0>:     push   rbp
   0x00005555555551ac <+1>:     mov    rbp,rsp
   0x00005555555551af <+4>:     sub    rsp,0x30
   0x00005555555551b3 <+8>:     mov    QWORD PTR [rbp-0x30],0x0
   0x00005555555551bb <+16>:    mov    QWORD PTR [rbp-0x28],0x2
   0x00005555555551c3 <+24>:    mov    QWORD PTR [rbp-0x20],0x4
   0x00005555555551cb <+32>:    mov    QWORD PTR [rbp-0x18],0x6
=> 0x00005555555551d3 <+40>:    mov    QWORD PTR [rbp-0x10],0x8
   0x00005555555551db <+48>:    call   0x555555555175 <_Z4funcv>
   0x00005555555551e0 <+53>:    lea    rax,[rbp-0x30]
   0x00005555555551e4 <+57>:    add    rax,0x20
   0x00005555555551e8 <+61>:    add    rax,0x18
   0x00005555555551ec <+65>:    lea    rdx,[rip+0xffffffffffffff82]        # 0x555555555175 <_Z4funcv>
   0x00005555555551f3 <+72>:    mov    QWORD PTR [rax],rdx
   0x00005555555551f6 <+75>:    mov    eax,0x0
   0x00005555555551fb <+80>:    leave  
   0x00005555555551fc <+81>:    ret    
End of assembler dump.

gdb-peda$ x/10xg $rbp-0x30
0x7fffffffde60: 0x0000000000000000      0x0000000000000002
0x7fffffffde70: 0x0000000000000004      0x0000000000000006
0x7fffffffde80: 0x00007fffffffdf70      0x0000000000000000
0x7fffffffde90: 0x0000555555555260      0x00007ffff7c0cb6b
0x7fffffffdea0: 0xffffffffffffff90      0x00007fffffffdf78

gdb-peda$ x 0x00007ffff7c0cb6b
0x7ffff7c0cb6b <__libc_start_main+235>: 0x480002084ee8c789

  0x7fffffffde60 -> &a[0]
  0x7fffffffde80 -> &a[4]
  0x7fffffffde90 -> pushed caller rbp (0x0000555555555260)
  0x7fffffffde98 -> return address (0x00007ffff7c0cb6b)

```

=> the stack address where return address is stored = 0x7fffffffde98 = &a[4] + 0x18(=3)