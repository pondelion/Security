
### compile

```
$ g++ -g hello_world.cc -o hello_world
```

### run

```
$ ./hello_world 
Hello World!
```

### disassemble

```
$ objdump -S -d -M intel hello_world
```

- main

```
0000000000001165 <main>:
#include <iostream>


int main() {
    1165:       55                      push   rbp
    1166:       48 89 e5                mov    rbp,rsp
  std::cout << "Hello World!" << std::endl;
    1169:       48 8d 35 95 0e 00 00    lea    rsi,[rip+0xe95]        # 2005 <_ZStL19piecewise_construct+0x1>
    1170:       48 8d 3d a9 2e 00 00    lea    rdi,[rip+0x2ea9]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    1177:       e8 c4 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    117c:       48 89 c2                mov    rdx,rax
    117f:       48 8b 05 4a 2e 00 00    mov    rax,QWORD PTR [rip+0x2e4a]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    1186:       48 89 c6                mov    rsi,rax
    1189:       48 89 d7                mov    rdi,rdx
    118c:       e8 bf fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
  return 0;
    1191:       b8 00 00 00 00          mov    eax,0x0
}
    1196:       5d                      pop    rbp
    1197:       c3                      ret    
```

<details><summary>whole</summary><div>

```

hello_world:     ファイル形式 elf64-x86-64


セクション .init の逆アセンブル:

0000000000001000 <_init>:
    1000:       48 83 ec 08             sub    rsp,0x8
    1004:       48 8b 05 dd 2f 00 00    mov    rax,QWORD PTR [rip+0x2fdd]        # 3fe8 <__gmon_start__>
    100b:       48 85 c0                test   rax,rax
    100e:       74 02                   je     1012 <_init+0x12>
    1010:       ff d0                   call   rax
    1012:       48 83 c4 08             add    rsp,0x8
    1016:       c3                      ret    

セクション .plt の逆アセンブル:

0000000000001020 <.plt>:
    1020:       ff 35 72 2f 00 00       push   QWORD PTR [rip+0x2f72]        # 3f98 <_GLOBAL_OFFSET_TABLE_+0x8>
    1026:       ff 25 74 2f 00 00       jmp    QWORD PTR [rip+0x2f74]        # 3fa0 <_GLOBAL_OFFSET_TABLE_+0x10>
    102c:       0f 1f 40 00             nop    DWORD PTR [rax+0x0]

0000000000001030 <__cxa_atexit@plt>:
    1030:       ff 25 72 2f 00 00       jmp    QWORD PTR [rip+0x2f72]        # 3fa8 <__cxa_atexit@GLIBC_2.2.5>
    1036:       68 00 00 00 00          push   0x0
    103b:       e9 e0 ff ff ff          jmp    1020 <.plt>

0000000000001040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>:
    1040:       ff 25 6a 2f 00 00       jmp    QWORD PTR [rip+0x2f6a]        # 3fb0 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@GLIBCXX_3.4>
    1046:       68 01 00 00 00          push   0x1
    104b:       e9 d0 ff ff ff          jmp    1020 <.plt>

0000000000001050 <_ZNSolsEPFRSoS_E@plt>:
    1050:       ff 25 62 2f 00 00       jmp    QWORD PTR [rip+0x2f62]        # 3fb8 <_ZNSolsEPFRSoS_E@GLIBCXX_3.4>
    1056:       68 02 00 00 00          push   0x2
    105b:       e9 c0 ff ff ff          jmp    1020 <.plt>

0000000000001060 <_ZNSt8ios_base4InitC1Ev@plt>:
    1060:       ff 25 5a 2f 00 00       jmp    QWORD PTR [rip+0x2f5a]        # 3fc0 <_ZNSt8ios_base4InitC1Ev@GLIBCXX_3.4>
    1066:       68 03 00 00 00          push   0x3
    106b:       e9 b0 ff ff ff          jmp    1020 <.plt>

セクション .plt.got の逆アセンブル:

0000000000001070 <__cxa_finalize@plt>:
    1070:       ff 25 52 2f 00 00       jmp    QWORD PTR [rip+0x2f52]        # 3fc8 <__cxa_finalize@GLIBC_2.2.5>
    1076:       66 90                   xchg   ax,ax

セクション .text の逆アセンブル:

0000000000001080 <_start>:
    1080:       31 ed                   xor    ebp,ebp
    1082:       49 89 d1                mov    r9,rdx
    1085:       5e                      pop    rsi
    1086:       48 89 e2                mov    rdx,rsp
    1089:       48 83 e4 f0             and    rsp,0xfffffffffffffff0
    108d:       50                      push   rax
    108e:       54                      push   rsp
    108f:       4c 8d 05 ca 01 00 00    lea    r8,[rip+0x1ca]        # 1260 <__libc_csu_fini>
    1096:       48 8d 0d 63 01 00 00    lea    rcx,[rip+0x163]        # 1200 <__libc_csu_init>
    109d:       48 8d 3d c1 00 00 00    lea    rdi,[rip+0xc1]        # 1165 <main>
    10a4:       ff 15 36 2f 00 00       call   QWORD PTR [rip+0x2f36]        # 3fe0 <__libc_start_main@GLIBC_2.2.5>
    10aa:       f4                      hlt    
    10ab:       0f 1f 44 00 00          nop    DWORD PTR [rax+rax*1+0x0]

00000000000010b0 <deregister_tm_clones>:
    10b0:       48 8d 3d 59 2f 00 00    lea    rdi,[rip+0x2f59]        # 4010 <__TMC_END__>
    10b7:       48 8d 05 52 2f 00 00    lea    rax,[rip+0x2f52]        # 4010 <__TMC_END__>
    10be:       48 39 f8                cmp    rax,rdi
    10c1:       74 15                   je     10d8 <deregister_tm_clones+0x28>
    10c3:       48 8b 05 0e 2f 00 00    mov    rax,QWORD PTR [rip+0x2f0e]        # 3fd8 <_ITM_deregisterTMCloneTable>
    10ca:       48 85 c0                test   rax,rax
    10cd:       74 09                   je     10d8 <deregister_tm_clones+0x28>
    10cf:       ff e0                   jmp    rax
    10d1:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    10d8:       c3                      ret    
    10d9:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

00000000000010e0 <register_tm_clones>:
    10e0:       48 8d 3d 29 2f 00 00    lea    rdi,[rip+0x2f29]        # 4010 <__TMC_END__>
    10e7:       48 8d 35 22 2f 00 00    lea    rsi,[rip+0x2f22]        # 4010 <__TMC_END__>
    10ee:       48 29 fe                sub    rsi,rdi
    10f1:       48 c1 fe 03             sar    rsi,0x3
    10f5:       48 89 f0                mov    rax,rsi
    10f8:       48 c1 e8 3f             shr    rax,0x3f
    10fc:       48 01 c6                add    rsi,rax
    10ff:       48 d1 fe                sar    rsi,1
    1102:       74 14                   je     1118 <register_tm_clones+0x38>
    1104:       48 8b 05 e5 2e 00 00    mov    rax,QWORD PTR [rip+0x2ee5]        # 3ff0 <_ITM_registerTMCloneTable>
    110b:       48 85 c0                test   rax,rax
    110e:       74 08                   je     1118 <register_tm_clones+0x38>
    1110:       ff e0                   jmp    rax
    1112:       66 0f 1f 44 00 00       nop    WORD PTR [rax+rax*1+0x0]
    1118:       c3                      ret    
    1119:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001120 <__do_global_dtors_aux>:
    1120:       80 3d 09 30 00 00 00    cmp    BYTE PTR [rip+0x3009],0x0        # 4130 <completed.7963>
    1127:       75 2f                   jne    1158 <__do_global_dtors_aux+0x38>
    1129:       55                      push   rbp
    112a:       48 83 3d 96 2e 00 00    cmp    QWORD PTR [rip+0x2e96],0x0        # 3fc8 <__cxa_finalize@GLIBC_2.2.5>
    1131:       00 
    1132:       48 89 e5                mov    rbp,rsp
    1135:       74 0c                   je     1143 <__do_global_dtors_aux+0x23>
    1137:       48 8b 3d ca 2e 00 00    mov    rdi,QWORD PTR [rip+0x2eca]        # 4008 <__dso_handle>
    113e:       e8 2d ff ff ff          call   1070 <__cxa_finalize@plt>
    1143:       e8 68 ff ff ff          call   10b0 <deregister_tm_clones>
    1148:       c6 05 e1 2f 00 00 01    mov    BYTE PTR [rip+0x2fe1],0x1        # 4130 <completed.7963>
    114f:       5d                      pop    rbp
    1150:       c3                      ret    
    1151:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    1158:       c3                      ret    
    1159:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001160 <frame_dummy>:
    1160:       e9 7b ff ff ff          jmp    10e0 <register_tm_clones>

0000000000001165 <main>:
#include <iostream>


int main() {
    1165:       55                      push   rbp
    1166:       48 89 e5                mov    rbp,rsp
  std::cout << "Hello World!" << std::endl;
    1169:       48 8d 35 95 0e 00 00    lea    rsi,[rip+0xe95]        # 2005 <_ZStL19piecewise_construct+0x1>
    1170:       48 8d 3d a9 2e 00 00    lea    rdi,[rip+0x2ea9]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    1177:       e8 c4 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    117c:       48 89 c2                mov    rdx,rax
    117f:       48 8b 05 4a 2e 00 00    mov    rax,QWORD PTR [rip+0x2e4a]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    1186:       48 89 c6                mov    rsi,rax
    1189:       48 89 d7                mov    rdi,rdx
    118c:       e8 bf fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
  return 0;
    1191:       b8 00 00 00 00          mov    eax,0x0
}
    1196:       5d                      pop    rbp
    1197:       c3                      ret    

0000000000001198 <_Z41__static_initialization_and_destruction_0ii>:
    1198:       55                      push   rbp
    1199:       48 89 e5                mov    rbp,rsp
    119c:       48 83 ec 10             sub    rsp,0x10
    11a0:       89 7d fc                mov    DWORD PTR [rbp-0x4],edi
    11a3:       89 75 f8                mov    DWORD PTR [rbp-0x8],esi
    11a6:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    11aa:       75 32                   jne    11de <_Z41__static_initialization_and_destruction_0ii+0x46>
    11ac:       81 7d f8 ff ff 00 00    cmp    DWORD PTR [rbp-0x8],0xffff
    11b3:       75 29                   jne    11de <_Z41__static_initialization_and_destruction_0ii+0x46>
  extern wostream wclog;        /// Linked to standard error (buffered)
#endif
  //@}

  // For construction of filebuffers for cout, cin, cerr, clog et. al.
  static ios_base::Init __ioinit;
    11b5:       48 8d 3d 75 2f 00 00    lea    rdi,[rip+0x2f75]        # 4131 <_ZStL8__ioinit>
    11bc:       e8 9f fe ff ff          call   1060 <_ZNSt8ios_base4InitC1Ev@plt>
    11c1:       48 8d 15 40 2e 00 00    lea    rdx,[rip+0x2e40]        # 4008 <__dso_handle>
    11c8:       48 8d 35 62 2f 00 00    lea    rsi,[rip+0x2f62]        # 4131 <_ZStL8__ioinit>
    11cf:       48 8b 05 22 2e 00 00    mov    rax,QWORD PTR [rip+0x2e22]        # 3ff8 <_ZNSt8ios_base4InitD1Ev@GLIBCXX_3.4>
    11d6:       48 89 c7                mov    rdi,rax
    11d9:       e8 52 fe ff ff          call   1030 <__cxa_atexit@plt>
    11de:       90                      nop
    11df:       c9                      leave  
    11e0:       c3                      ret    

00000000000011e1 <_GLOBAL__sub_I_main>:
    11e1:       55                      push   rbp
    11e2:       48 89 e5                mov    rbp,rsp
    11e5:       be ff ff 00 00          mov    esi,0xffff
    11ea:       bf 01 00 00 00          mov    edi,0x1
    11ef:       e8 a4 ff ff ff          call   1198 <_Z41__static_initialization_and_destruction_0ii>
    11f4:       5d                      pop    rbp
    11f5:       c3                      ret    
    11f6:       66 2e 0f 1f 84 00 00    nop    WORD PTR cs:[rax+rax*1+0x0]
    11fd:       00 00 00 

0000000000001200 <__libc_csu_init>:
    1200:       41 57                   push   r15
    1202:       49 89 d7                mov    r15,rdx
    1205:       41 56                   push   r14
    1207:       49 89 f6                mov    r14,rsi
    120a:       41 55                   push   r13
    120c:       41 89 fd                mov    r13d,edi
    120f:       41 54                   push   r12
    1211:       4c 8d 25 60 2b 00 00    lea    r12,[rip+0x2b60]        # 3d78 <__frame_dummy_init_array_entry>
    1218:       55                      push   rbp
    1219:       48 8d 2d 68 2b 00 00    lea    rbp,[rip+0x2b68]        # 3d88 <__init_array_end>
    1220:       53                      push   rbx
    1221:       4c 29 e5                sub    rbp,r12
    1224:       48 83 ec 08             sub    rsp,0x8
    1228:       e8 d3 fd ff ff          call   1000 <_init>
    122d:       48 c1 fd 03             sar    rbp,0x3
    1231:       74 1b                   je     124e <__libc_csu_init+0x4e>
    1233:       31 db                   xor    ebx,ebx
    1235:       0f 1f 00                nop    DWORD PTR [rax]
    1238:       4c 89 fa                mov    rdx,r15
    123b:       4c 89 f6                mov    rsi,r14
    123e:       44 89 ef                mov    edi,r13d
    1241:       41 ff 14 dc             call   QWORD PTR [r12+rbx*8]
    1245:       48 83 c3 01             add    rbx,0x1
    1249:       48 39 dd                cmp    rbp,rbx
    124c:       75 ea                   jne    1238 <__libc_csu_init+0x38>
    124e:       48 83 c4 08             add    rsp,0x8
    1252:       5b                      pop    rbx
    1253:       5d                      pop    rbp
    1254:       41 5c                   pop    r12
    1256:       41 5d                   pop    r13
    1258:       41 5e                   pop    r14
    125a:       41 5f                   pop    r15
    125c:       c3                      ret    
    125d:       0f 1f 00                nop    DWORD PTR [rax]

0000000000001260 <__libc_csu_fini>:
    1260:       c3                      ret    

セクション .fini の逆アセンブル:

0000000000001264 <_fini>:
    1264:       48 83 ec 08             sub    rsp,0x8
    1268:       48 83 c4 08             add    rsp,0x8
    126c:       c3                      ret    
```

</div></details>

### debug (gdb)

```
$ gdb hello_world
```

```
(gdb) break main
(gdb) run
```

#### memory map

```
(gdb) info proc map
process 15021
Mapped address spaces:

          Start Addr           End Addr       Size     Offset objfile
      0x555555554000     0x555555555000     0x1000        0x0 /home/ym/github/Disasm/memo/linux/c_cpp/hello_world/hello_world
      0x555555555000     0x555555556000     0x1000     0x1000 /home/ym/github/Disasm/memo/linux/c_cpp/hello_world/hello_world
      0x555555556000     0x555555557000     0x1000     0x2000 /home/ym/github/Disasm/memo/linux/c_cpp/hello_world/hello_world
      0x555555557000     0x555555558000     0x1000     0x2000 /home/ym/github/Disasm/memo/linux/c_cpp/hello_world/hello_world
      0x555555558000     0x555555559000     0x1000     0x3000 /home/ym/github/Disasm/memo/linux/c_cpp/hello_world/hello_world
      0x555555559000     0x55555557a000    0x21000        0x0 [heap]
      0x7ffff7a7a000     0x7ffff7a7e000     0x4000        0x0 
      0x7ffff7a7e000     0x7ffff7a81000     0x3000        0x0 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a81000     0x7ffff7a92000    0x11000     0x3000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a92000     0x7ffff7a96000     0x4000    0x14000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a96000     0x7ffff7a97000     0x1000    0x17000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a97000     0x7ffff7a98000     0x1000    0x18000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
--Type <RET> for more, q to quit, c to continue without paging--
      0x7ffff7a98000     0x7ffff7aa7000     0xf000        0x0 /usr/lib/x86_64-linux-gnu/libm-2.29.so
      0x7ffff7aa7000     0x7ffff7b4d000    0xa6000     0xf000 /usr/lib/x86_64-linux-gnu/libm-2.29.so
      0x7ffff7b4d000     0x7ffff7be4000    0x97000    0xb5000 /usr/lib/x86_64-linux-gnu/libm-2.29.so
      0x7ffff7be4000     0x7ffff7be5000     0x1000   0x14b000 /usr/lib/x86_64-linux-gnu/libm-2.29.so
      0x7ffff7be5000     0x7ffff7be6000     0x1000   0x14c000 /usr/lib/x86_64-linux-gnu/libm-2.29.so
      0x7ffff7be6000     0x7ffff7c0b000    0x25000        0x0 /usr/lib/x86_64-linux-gnu/libc-2.29.so
      0x7ffff7c0b000     0x7ffff7d7e000   0x173000    0x25000 /usr/lib/x86_64-linux-gnu/libc-2.29.so
      0x7ffff7d7e000     0x7ffff7dc7000    0x49000   0x198000 /usr/lib/x86_64-linux-gnu/libc-2.29.so
      0x7ffff7dc7000     0x7ffff7dca000     0x3000   0x1e0000 /usr/lib/x86_64-linux-gnu/libc-2.29.so
      0x7ffff7dca000     0x7ffff7dcd000     0x3000   0x1e3000 /usr/lib/x86_64-linux-gnu/libc-2.29.so
      0x7ffff7dcd000     0x7ffff7dd1000     0x4000        0x0 
      0x7ffff7dd1000     0x7ffff7e66000    0x95000        0x0 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7e66000     0x7ffff7f58000    0xf2000    0x95000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7f58000     0x7ffff7fa1000    0x49000   0x187000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7fa1000     0x7ffff7fa2000     0x1000   0x1d0000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7fa2000     0x7ffff7fad000     0xb000   0x1d0000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
--Type <RET> for more, q to quit, c to continue without paging--
      0x7ffff7fad000     0x7ffff7fb0000     0x3000   0x1db000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7fb0000     0x7ffff7fb5000     0x5000        0x0 
      0x7ffff7fce000     0x7ffff7fd1000     0x3000        0x0 [vvar]
      0x7ffff7fd1000     0x7ffff7fd2000     0x1000        0x0 [vdso]
      0x7ffff7fd2000     0x7ffff7fd3000     0x1000        0x0 /usr/lib/x86_64-linux-gnu/ld-2.29.so
      0x7ffff7fd3000     0x7ffff7ff4000    0x21000     0x1000 /usr/lib/x86_64-linux-gnu/ld-2.29.so
      0x7ffff7ff4000     0x7ffff7ffc000     0x8000    0x22000 /usr/lib/x86_64-linux-gnu/ld-2.29.so
      0x7ffff7ffc000     0x7ffff7ffd000     0x1000    0x29000 /usr/lib/x86_64-linux-gnu/ld-2.29.so
      0x7ffff7ffd000     0x7ffff7ffe000     0x1000    0x2a000 /usr/lib/x86_64-linux-gnu/ld-2.29.so
      0x7ffff7ffe000     0x7ffff7fff000     0x1000        0x0 
      0x7ffffffde000     0x7ffffffff000    0x21000        0x0 [stack]
  0xffffffffff600000 0xffffffffff601000     0x1000        0x0 [vsyscall]
```

- find address of string "Hello World!"

```
(gdb) find 0x555555554000,0x555555559000,"Hello World!"
0x555555556005
0x555555557005
2 patterns found.
```

```
(gdb) x /s 0x555555557005
0x555555557005: "Hello World!"
(gdb) x /s 0x555555557005
0x555555557005: "Hello World!"
```

- step execution


<details><summary>open</summary><div>

```
(gdb) disassemble main
Dump of assembler code for function main():
   0x0000555555555165 <+0>:     push   %rbp
   0x0000555555555166 <+1>:     mov    %rsp,%rbp
=> 0x0000555555555169 <+4>:     lea    0xe95(%rip),%rsi        # 0x555555556005
   0x0000555555555170 <+11>:    lea    0x2ea9(%rip),%rdi        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x0000555555555177 <+18>:    callq  0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
   0x000055555555517c <+23>:    mov    %rax,%rdx
   0x000055555555517f <+26>:    mov    0x2e4a(%rip),%rax        # 0x555555557fd0
   0x0000555555555186 <+33>:    mov    %rax,%rsi
   0x0000555555555189 <+36>:    mov    %rdx,%rdi
   0x000055555555518c <+39>:    callq  0x555555555050 <_ZNSolsEPFRSoS_E@plt>
   0x0000555555555191 <+44>:    mov    $0x0,%eax
   0x0000555555555196 <+49>:    pop    %rbp
   0x0000555555555197 <+50>:    retq   
End of assembler dump.

(gdb) ni
0x0000555555555170      5         std::cout << "Hello World!" << std::endl;

(gdb) disassemble main
Dump of assembler code for function main():
   0x0000555555555165 <+0>:     push   %rbp
   0x0000555555555166 <+1>:     mov    %rsp,%rbp
   0x0000555555555169 <+4>:     lea    0xe95(%rip),%rsi        # 0x555555556005
=> 0x0000555555555170 <+11>:    lea    0x2ea9(%rip),%rdi        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x0000555555555177 <+18>:    callq  0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
   0x000055555555517c <+23>:    mov    %rax,%rdx
   0x000055555555517f <+26>:    mov    0x2e4a(%rip),%rax        # 0x555555557fd0
   0x0000555555555186 <+33>:    mov    %rax,%rsi
   0x0000555555555189 <+36>:    mov    %rdx,%rdi
   0x000055555555518c <+39>:    callq  0x555555555050 <_ZNSolsEPFRSoS_E@plt>
   0x0000555555555191 <+44>:    mov    $0x0,%eax
   0x0000555555555196 <+49>:    pop    %rbp
   0x0000555555555197 <+50>:    retq   
End of assembler dump.

(gdb) ni
0x0000555555555177      5         std::cout << "Hello World!" << std::endl;

(gdb) disassemble main
Dump of assembler code for function main():
   0x0000555555555165 <+0>:     push   %rbp
   0x0000555555555166 <+1>:     mov    %rsp,%rbp
   0x0000555555555169 <+4>:     lea    0xe95(%rip),%rsi        # 0x555555556005
   0x0000555555555170 <+11>:    lea    0x2ea9(%rip),%rdi        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
=> 0x0000555555555177 <+18>:    callq  0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
   0x000055555555517c <+23>:    mov    %rax,%rdx
   0x000055555555517f <+26>:    mov    0x2e4a(%rip),%rax        # 0x555555557fd0
   0x0000555555555186 <+33>:    mov    %rax,%rsi
   0x0000555555555189 <+36>:    mov    %rdx,%rdi
   0x000055555555518c <+39>:    callq  0x555555555050 <_ZNSolsEPFRSoS_E@plt>
   0x0000555555555191 <+44>:    mov    $0x0,%eax
   0x0000555555555196 <+49>:    pop    %rbp
   0x0000555555555197 <+50>:    retq   
End of assembler dump.
```

</details></div>

### debug (radare)

```
$ r2 hello_world
```

analyze
```
[0x00001080]> aa
[x] Analyze all flags starting with sym. and entry0 (aa)
[0x00001080]> aac
```

- disassemble main

```
[0x00001080]> pdf @ main
            ; DATA XREF from entry0 @ 0x109d
┌ 51: main ();
│           0x00001165      55             push rbp                    ; hello_world.cc:4 int main() {
│           0x00001166      4889e5         mov rbp, rsp
│           0x00001169      488d35950e00.  lea rsi, str.Hello_World    ; hello_world.cc:5   std::cout << "Hello World!" << std::endl; ; 0x2005 ; "Hello World!"
│           0x00001170      488d3da92e00.  lea rdi, obj.std::cout      ; sym..bss
│                                                                      ; 0x4020
│           0x00001177      e8c4feffff     call sym std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*) ; sym.imp.std::basic_ostream_char__std::char_traits_char_____std::operator____std::char_traits_char____std::basic_ostream_char__std::char_traits_char______char_const
│           0x0000117c      4889c2         mov rdx, rax
│           0x0000117f      488b054a2e00.  mov rax, qword [method.std::basic_ostream_char__std::char_traits_char_____std::endl_char__std.char_traits_char____std::basic_ostream_char__std::char_traits_char] ; [0x3fd0:8]=0
│           0x00001186      4889c6         mov rsi, rax
│           0x00001189      4889d7         mov rdi, rdx
│           0x0000118c      e8bffeffff     call sym std::ostream::operator<<(std::ostream& (*)(std::ostream&)) ; sym.imp.std::ostream::operator___std::ostream______std::ostream
│           0x00001191      b800000000     mov eax, 0                  ; hello_world.cc:6   return 0;
│           0x00001196      5d             pop rbp                     ; hello_world.cc:7 }
└           0x00001197      c3             ret
```

- check strings

```
$ rabin2 -z hello_world
[Strings]
nth paddr      vaddr      len size section type  string
―――――――――――――――――――――――――――――――――――――――――――――――――――――――
0   0x00002005 0x00002005 12  13   .rodata ascii Hello World!
```

- check reference to string "Hello World!"

```
[0x00001080]> axt 0x00002005
main 0x1169 [DATA] lea rsi, str.Hello_World
```

```
[0x00001080]> s 0x1169
[0x00001169]> pd
│           0x00001169      488d35950e00.  lea rsi, str.Hello_World    ; hello_world.cc:5   std::cout << "Hello World!" << std::endl; ; 0x2005 ; "Hello World!"
│           0x00001170      488d3da92e00.  lea rdi, obj.std::cout      ; sym..bss
│                                                                      ; 0x4020
│           0x00001177      e8c4feffff     call sym std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*) ; sym.imp.std::basic_ostream_char__std::char_traits_char_____std::operator____std::char_traits_char____std::basic_ostream_char__std::char_traits_char______char_const
│           0x0000117c      4889c2         mov rdx, rax
│           0x0000117f      488b054a2e00.  mov rax, qword [method.std::basic_ostream_char__std::char_traits_char_____std::endl_char__std.char_traits_char____std::basic_ostream_char__std::char_traits_char] ; [0x3fd0:8]=0
│           0x00001186      4889c6         mov rsi, rax
│           0x00001189      4889d7         mov rdi, rdx
│           0x0000118c      e8bffeffff     call sym std::ostream::operator<<(std::ostream& (*)(std::ostream&)) ; sym.imp.std::ostream::operator___std::ostream______std::ostream
│           0x00001191      b800000000     mov eax, 0                  ; hello_world.cc:6   return 0;
│           0x00001196      5d             pop rbp                     ; hello_world.cc:7 }
└           0x00001197      c3             ret
            ; CALL XREF from entry.init1 @ 0x11ef
...
```