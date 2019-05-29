### 32bit

- compile

```bash
$ nasm -f elf shellcode32.s
$ ld -s -m elf_i386 shellcode32.o -o shellcode32
```

- dump assembly/machine code

```
$ objdump -d shellcode32
```

```
shellcode32:     ファイル形式 elf32-i386


セクション .text の逆アセンブル:

08049000 <.text>:
 8049000:       eb 1e                   jmp    0x8049020
 8049002:       b8 04 00 00 00          mov    $0x4,%eax
 8049007:       bb 01 00 00 00          mov    $0x1,%ebx
 804900c:       59                      pop    %ecx
 804900d:       ba 0f 00 00 00          mov    $0xf,%edx
 8049012:       cd 80                   int    $0x80
 8049014:       b8 01 00 00 00          mov    $0x1,%eax
 8049019:       bb 00 00 00 00          mov    $0x0,%ebx
 804901e:       cd 80                   int    $0x80
 8049020:       e8 dd ff ff ff          call   0x8049002
 8049025:       48                      dec    %eax
 8049026:       65 6c                   gs insb (%dx),%es:(%edi)
 8049028:       6c                      insb   (%dx),%es:(%edi)
 8049029:       6f                      outsl  %ds:(%esi),(%dx)
 804902a:       2c 20                   sub    $0x20,%al
 804902c:       57                      push   %edi
 804902d:       6f                      outsl  %ds:(%esi),(%dx)
 804902e:       72 6c                   jb     0x804909c
 8049030:       64                      fs
 8049031:       21                      .byte 0x21
 8049032:       0d                      .byte 0xd
 8049033:       0a                      .byte 0xa
```

- compile

```bash
$ gcc -fno-stack-protector -z execstack -m32 helloworld32.c -o helloworld32
```

- execute

```bash
$ ./helloworld32
Hello, World!
```

### 64bit

- compile

```bash
$ nasm -f elf64 shellcode64.s
$ ld -s shellcode64.o -o shellcode64
```

- dump assembly/machine code

```
$ objdump -d shellcode64
```

```
shellcode64:     ファイル形式 elf64-x86-64


セクション .text の逆アセンブル:

0000000000401000 <.text>:
  401000:       eb 1e                   jmp    0x401020
  401002:       b8 01 00 00 00          mov    $0x1,%eax
  401007:       bf 01 00 00 00          mov    $0x1,%edi
  40100c:       5e                      pop    %rsi
  40100d:       ba 0f 00 00 00          mov    $0xf,%edx
  401012:       0f 05                   syscall
  401014:       b8 3c 00 00 00          mov    $0x3c,%eax
  401019:       bf 00 00 00 00          mov    $0x0,%edi
  40101e:       0f 05                   syscall
  401020:       e8 dd ff ff ff          callq  0x401002
  401025:       48                      rex.W
  401026:       65 6c                   gs insb (%dx),%es:(%rdi)
  401028:       6c                      insb   (%dx),%es:(%rdi)
  401029:       6f                      outsl  %ds:(%rsi),(%dx)
  40102a:       2c 20                   sub    $0x20,%al
  40102c:       57                      push   %rdi
  40102d:       6f                      outsl  %ds:(%rsi),(%dx)
  40102e:       72 6c                   jb     0x40109c
  401030:       64                      fs
  401031:       21                      .byte 0x21
  401032:       0d                      .byte 0xd
  401033:       0a                      .byte 0xa
```

- compile

```bash
$ gcc -fno-stack-protector -z execstack helloworld64.c -o helloworld64
```

- execute

```bash
$ ./helloworld64
Hello, World!
```
