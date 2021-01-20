### compile

```
$ g++ -g func.cc -o func
```

### execute

```
$ ./func
17
```

### disassemble

```
$ objdump -S -d -M intel if
```

- main & func_add

```
0000000000001155 <_Z8func_addii>:
#include <iostream>


int func_add(int a, int b) {
    1155:       55                      push   rbp
    1156:       48 89 e5                mov    rbp,rsp
    1159:       89 7d ec                mov    DWORD PTR [rbp-0x14],edi
    115c:       89 75 e8                mov    DWORD PTR [rbp-0x18],esi
  int c = 10;
    115f:       c7 45 fc 0a 00 00 00    mov    DWORD PTR [rbp-0x4],0xa
  return a + b + c;
    1166:       8b 55 ec                mov    edx,DWORD PTR [rbp-0x14]
    1169:       8b 45 e8                mov    eax,DWORD PTR [rbp-0x18]
    116c:       01 c2                   add    edx,eax
    116e:       8b 45 fc                mov    eax,DWORD PTR [rbp-0x4]
    1171:       01 d0                   add    eax,edx
}
    1173:       5d                      pop    rbp
    1174:       c3                      ret    

0000000000001175 <main>:


int main() {
    1175:       55                      push   rbp
    1176:       48 89 e5                mov    rbp,rsp
    1179:       48 83 ec 10             sub    rsp,0x10
  int a = 3;
    117d:       c7 45 f4 03 00 00 00    mov    DWORD PTR [rbp-0xc],0x3
  int b = 4;
    1184:       c7 45 f8 04 00 00 00    mov    DWORD PTR [rbp-0x8],0x4
  int res;

  res = func_add(a, b);
    118b:       8b 55 f8                mov    edx,DWORD PTR [rbp-0x8]
    118e:       8b 45 f4                mov    eax,DWORD PTR [rbp-0xc]
    1191:       89 d6                   mov    esi,edx
    1193:       89 c7                   mov    edi,eax
    1195:       e8 bb ff ff ff          call   1155 <_Z8func_addii>
    119a:       89 45 fc                mov    DWORD PTR [rbp-0x4],eax

  std::cout << res;
    119d:       8b 45 fc                mov    eax,DWORD PTR [rbp-0x4]
    11a0:       89 c6                   mov    esi,eax
    11a2:       48 8d 3d 77 2e 00 00    lea    rdi,[rip+0x2e77]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11a9:       e8 a2 fe ff ff          call   1050 <_ZNSolsEi@plt>

  return 0;
    11ae:       b8 00 00 00 00          mov    eax,0x0
    11b3:       c9                      leave  
    11b4:       c3                      ret    
```

<details><summary>whole</summary><div>

```
func:     ファイル形式 elf64-x86-64


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
    1020:       ff 35 82 2f 00 00       push   QWORD PTR [rip+0x2f82]        # 3fa8 <_GLOBAL_OFFSET_TABLE_+0x8>
    1026:       ff 25 84 2f 00 00       jmp    QWORD PTR [rip+0x2f84]        # 3fb0 <_GLOBAL_OFFSET_TABLE_+0x10>
    102c:       0f 1f 40 00             nop    DWORD PTR [rax+0x0]

0000000000001030 <__cxa_atexit@plt>:
    1030:       ff 25 82 2f 00 00       jmp    QWORD PTR [rip+0x2f82]        # 3fb8 <__cxa_atexit@GLIBC_2.2.5>
    1036:       68 00 00 00 00          push   0x0
    103b:       e9 e0 ff ff ff          jmp    1020 <.plt>

0000000000001040 <_ZNSt8ios_base4InitC1Ev@plt>:
    1040:       ff 25 7a 2f 00 00       jmp    QWORD PTR [rip+0x2f7a]        # 3fc0 <_ZNSt8ios_base4InitC1Ev@GLIBCXX_3.4>
    1046:       68 01 00 00 00          push   0x1
    104b:       e9 d0 ff ff ff          jmp    1020 <.plt>

0000000000001050 <_ZNSolsEi@plt>:
    1050:       ff 25 72 2f 00 00       jmp    QWORD PTR [rip+0x2f72]        # 3fc8 <_ZNSolsEi@GLIBCXX_3.4>
    1056:       68 02 00 00 00          push   0x2
    105b:       e9 c0 ff ff ff          jmp    1020 <.plt>

セクション .plt.got の逆アセンブル:

0000000000001060 <__cxa_finalize@plt>:
    1060:       ff 25 6a 2f 00 00       jmp    QWORD PTR [rip+0x2f6a]        # 3fd0 <__cxa_finalize@GLIBC_2.2.5>
    1066:       66 90                   xchg   ax,ax

セクション .text の逆アセンブル:

0000000000001070 <_start>:
    1070:       31 ed                   xor    ebp,ebp
    1072:       49 89 d1                mov    r9,rdx
    1075:       5e                      pop    rsi
    1076:       48 89 e2                mov    rdx,rsp
    1079:       48 83 e4 f0             and    rsp,0xfffffffffffffff0
    107d:       50                      push   rax
    107e:       54                      push   rsp
    107f:       4c 8d 05 fa 01 00 00    lea    r8,[rip+0x1fa]        # 1280 <__libc_csu_fini>
    1086:       48 8d 0d 93 01 00 00    lea    rcx,[rip+0x193]        # 1220 <__libc_csu_init>
    108d:       48 8d 3d e1 00 00 00    lea    rdi,[rip+0xe1]        # 1175 <main>
    1094:       ff 15 46 2f 00 00       call   QWORD PTR [rip+0x2f46]        # 3fe0 <__libc_start_main@GLIBC_2.2.5>
    109a:       f4                      hlt    
    109b:       0f 1f 44 00 00          nop    DWORD PTR [rax+rax*1+0x0]

00000000000010a0 <deregister_tm_clones>:
    10a0:       48 8d 3d 69 2f 00 00    lea    rdi,[rip+0x2f69]        # 4010 <__TMC_END__>
    10a7:       48 8d 05 62 2f 00 00    lea    rax,[rip+0x2f62]        # 4010 <__TMC_END__>
    10ae:       48 39 f8                cmp    rax,rdi
    10b1:       74 15                   je     10c8 <deregister_tm_clones+0x28>
    10b3:       48 8b 05 1e 2f 00 00    mov    rax,QWORD PTR [rip+0x2f1e]        # 3fd8 <_ITM_deregisterTMCloneTable>
    10ba:       48 85 c0                test   rax,rax
    10bd:       74 09                   je     10c8 <deregister_tm_clones+0x28>
    10bf:       ff e0                   jmp    rax
    10c1:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    10c8:       c3                      ret    
    10c9:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

00000000000010d0 <register_tm_clones>:
    10d0:       48 8d 3d 39 2f 00 00    lea    rdi,[rip+0x2f39]        # 4010 <__TMC_END__>
    10d7:       48 8d 35 32 2f 00 00    lea    rsi,[rip+0x2f32]        # 4010 <__TMC_END__>
    10de:       48 29 fe                sub    rsi,rdi
    10e1:       48 c1 fe 03             sar    rsi,0x3
    10e5:       48 89 f0                mov    rax,rsi
    10e8:       48 c1 e8 3f             shr    rax,0x3f
    10ec:       48 01 c6                add    rsi,rax
    10ef:       48 d1 fe                sar    rsi,1
    10f2:       74 14                   je     1108 <register_tm_clones+0x38>
    10f4:       48 8b 05 f5 2e 00 00    mov    rax,QWORD PTR [rip+0x2ef5]        # 3ff0 <_ITM_registerTMCloneTable>
    10fb:       48 85 c0                test   rax,rax
    10fe:       74 08                   je     1108 <register_tm_clones+0x38>
    1100:       ff e0                   jmp    rax
    1102:       66 0f 1f 44 00 00       nop    WORD PTR [rax+rax*1+0x0]
    1108:       c3                      ret    
    1109:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001110 <__do_global_dtors_aux>:
    1110:       80 3d 19 30 00 00 00    cmp    BYTE PTR [rip+0x3019],0x0        # 4130 <completed.7963>
    1117:       75 2f                   jne    1148 <__do_global_dtors_aux+0x38>
    1119:       55                      push   rbp
    111a:       48 83 3d ae 2e 00 00    cmp    QWORD PTR [rip+0x2eae],0x0        # 3fd0 <__cxa_finalize@GLIBC_2.2.5>
    1121:       00 
    1122:       48 89 e5                mov    rbp,rsp
    1125:       74 0c                   je     1133 <__do_global_dtors_aux+0x23>
    1127:       48 8b 3d da 2e 00 00    mov    rdi,QWORD PTR [rip+0x2eda]        # 4008 <__dso_handle>
    112e:       e8 2d ff ff ff          call   1060 <__cxa_finalize@plt>
    1133:       e8 68 ff ff ff          call   10a0 <deregister_tm_clones>
    1138:       c6 05 f1 2f 00 00 01    mov    BYTE PTR [rip+0x2ff1],0x1        # 4130 <completed.7963>
    113f:       5d                      pop    rbp
    1140:       c3                      ret    
    1141:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    1148:       c3                      ret    
    1149:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001150 <frame_dummy>:
    1150:       e9 7b ff ff ff          jmp    10d0 <register_tm_clones>

0000000000001155 <_Z8func_addii>:
#include <iostream>


int func_add(int a, int b) {
    1155:       55                      push   rbp
    1156:       48 89 e5                mov    rbp,rsp
    1159:       89 7d ec                mov    DWORD PTR [rbp-0x14],edi
    115c:       89 75 e8                mov    DWORD PTR [rbp-0x18],esi
  int c = 10;
    115f:       c7 45 fc 0a 00 00 00    mov    DWORD PTR [rbp-0x4],0xa
  return a + b + c;
    1166:       8b 55 ec                mov    edx,DWORD PTR [rbp-0x14]
    1169:       8b 45 e8                mov    eax,DWORD PTR [rbp-0x18]
    116c:       01 c2                   add    edx,eax
    116e:       8b 45 fc                mov    eax,DWORD PTR [rbp-0x4]
    1171:       01 d0                   add    eax,edx
}
    1173:       5d                      pop    rbp
    1174:       c3                      ret    

0000000000001175 <main>:


int main() {
    1175:       55                      push   rbp
    1176:       48 89 e5                mov    rbp,rsp
    1179:       48 83 ec 10             sub    rsp,0x10
  int a = 3;
    117d:       c7 45 f4 03 00 00 00    mov    DWORD PTR [rbp-0xc],0x3
  int b = 4;
    1184:       c7 45 f8 04 00 00 00    mov    DWORD PTR [rbp-0x8],0x4
  int res;

  res = func_add(a, b);
    118b:       8b 55 f8                mov    edx,DWORD PTR [rbp-0x8]
    118e:       8b 45 f4                mov    eax,DWORD PTR [rbp-0xc]
    1191:       89 d6                   mov    esi,edx
    1193:       89 c7                   mov    edi,eax
    1195:       e8 bb ff ff ff          call   1155 <_Z8func_addii>
    119a:       89 45 fc                mov    DWORD PTR [rbp-0x4],eax

  std::cout << res;
    119d:       8b 45 fc                mov    eax,DWORD PTR [rbp-0x4]
    11a0:       89 c6                   mov    esi,eax
    11a2:       48 8d 3d 77 2e 00 00    lea    rdi,[rip+0x2e77]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11a9:       e8 a2 fe ff ff          call   1050 <_ZNSolsEi@plt>

  return 0;
    11ae:       b8 00 00 00 00          mov    eax,0x0
    11b3:       c9                      leave  
    11b4:       c3                      ret    

00000000000011b5 <_Z41__static_initialization_and_destruction_0ii>:
    11b5:       55                      push   rbp
    11b6:       48 89 e5                mov    rbp,rsp
    11b9:       48 83 ec 10             sub    rsp,0x10
    11bd:       89 7d fc                mov    DWORD PTR [rbp-0x4],edi
    11c0:       89 75 f8                mov    DWORD PTR [rbp-0x8],esi
    11c3:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    11c7:       75 32                   jne    11fb <_Z41__static_initialization_and_destruction_0ii+0x46>
    11c9:       81 7d f8 ff ff 00 00    cmp    DWORD PTR [rbp-0x8],0xffff
    11d0:       75 29                   jne    11fb <_Z41__static_initialization_and_destruction_0ii+0x46>
  extern wostream wclog;        /// Linked to standard error (buffered)
#endif
  //@}

  // For construction of filebuffers for cout, cin, cerr, clog et. al.
  static ios_base::Init __ioinit;
    11d2:       48 8d 3d 58 2f 00 00    lea    rdi,[rip+0x2f58]        # 4131 <_ZStL8__ioinit>
    11d9:       e8 62 fe ff ff          call   1040 <_ZNSt8ios_base4InitC1Ev@plt>
    11de:       48 8d 15 23 2e 00 00    lea    rdx,[rip+0x2e23]        # 4008 <__dso_handle>
    11e5:       48 8d 35 45 2f 00 00    lea    rsi,[rip+0x2f45]        # 4131 <_ZStL8__ioinit>
    11ec:       48 8b 05 05 2e 00 00    mov    rax,QWORD PTR [rip+0x2e05]        # 3ff8 <_ZNSt8ios_base4InitD1Ev@GLIBCXX_3.4>
    11f3:       48 89 c7                mov    rdi,rax
    11f6:       e8 35 fe ff ff          call   1030 <__cxa_atexit@plt>
    11fb:       90                      nop
    11fc:       c9                      leave  
    11fd:       c3                      ret    

00000000000011fe <_GLOBAL__sub_I__Z8func_addii>:
    11fe:       55                      push   rbp
    11ff:       48 89 e5                mov    rbp,rsp
    1202:       be ff ff 00 00          mov    esi,0xffff
    1207:       bf 01 00 00 00          mov    edi,0x1
    120c:       e8 a4 ff ff ff          call   11b5 <_Z41__static_initialization_and_destruction_0ii>
    1211:       5d                      pop    rbp
    1212:       c3                      ret    
    1213:       66 2e 0f 1f 84 00 00    nop    WORD PTR cs:[rax+rax*1+0x0]
    121a:       00 00 00 
    121d:       0f 1f 00                nop    DWORD PTR [rax]

0000000000001220 <__libc_csu_init>:
    1220:       41 57                   push   r15
    1222:       49 89 d7                mov    r15,rdx
    1225:       41 56                   push   r14
    1227:       49 89 f6                mov    r14,rsi
    122a:       41 55                   push   r13
    122c:       41 89 fd                mov    r13d,edi
    122f:       41 54                   push   r12
    1231:       4c 8d 25 50 2b 00 00    lea    r12,[rip+0x2b50]        # 3d88 <__frame_dummy_init_array_entry>
    1238:       55                      push   rbp
    1239:       48 8d 2d 58 2b 00 00    lea    rbp,[rip+0x2b58]        # 3d98 <__init_array_end>
    1240:       53                      push   rbx
    1241:       4c 29 e5                sub    rbp,r12
    1244:       48 83 ec 08             sub    rsp,0x8
    1248:       e8 b3 fd ff ff          call   1000 <_init>
    124d:       48 c1 fd 03             sar    rbp,0x3
    1251:       74 1b                   je     126e <__libc_csu_init+0x4e>
    1253:       31 db                   xor    ebx,ebx
    1255:       0f 1f 00                nop    DWORD PTR [rax]
    1258:       4c 89 fa                mov    rdx,r15
    125b:       4c 89 f6                mov    rsi,r14
    125e:       44 89 ef                mov    edi,r13d
    1261:       41 ff 14 dc             call   QWORD PTR [r12+rbx*8]
    1265:       48 83 c3 01             add    rbx,0x1
    1269:       48 39 dd                cmp    rbp,rbx
    126c:       75 ea                   jne    1258 <__libc_csu_init+0x38>
    126e:       48 83 c4 08             add    rsp,0x8
    1272:       5b                      pop    rbx
    1273:       5d                      pop    rbp
    1274:       41 5c                   pop    r12
    1276:       41 5d                   pop    r13
    1278:       41 5e                   pop    r14
    127a:       41 5f                   pop    r15
    127c:       c3                      ret    
    127d:       0f 1f 00                nop    DWORD PTR [rax]

0000000000001280 <__libc_csu_fini>:
    1280:       c3                      ret    

セクション .fini の逆アセンブル:

0000000000001284 <_fini>:
    1284:       48 83 ec 08             sub    rsp,0x8
    1288:       48 83 c4 08             add    rsp,0x8
    128c:       c3                      ret    
```

</details></div>

### debug (gdb)

```
$ gdb func
```

```
(gdb) break main
(gdb) run
```

#### memory map

```
(gdb) info proc map
process 2856
Mapped address spaces:

          Start Addr           End Addr       Size     Offset objfile
      0x555555554000     0x555555555000     0x1000        0x0 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/func/func
      0x555555555000     0x555555556000     0x1000     0x1000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/func/func
      0x555555556000     0x555555557000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/func/func
      0x555555557000     0x555555558000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/func/func
      0x555555558000     0x555555559000     0x1000     0x3000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/func/func
      0x555555559000     0x55555557a000    0x21000        0x0 [heap]
      0x7ffff7a7a000     0x7ffff7a7e000     0x4000        0x0 
      0x7ffff7a7e000     0x7ffff7a81000     0x3000        0x0 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a81000     0x7ffff7a92000    0x11000     0x3000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a92000     0x7ffff7a96000     0x4000    0x14000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
      0x7ffff7a96000     0x7ffff7a97000     0x1000    0x17000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
--Type <RET> for more, q to quit, c to continue without paging--
      0x7ffff7a97000     0x7ffff7a98000     0x1000    0x18000 /usr/lib/x86_64-linux-gnu/libgcc_s.so.1
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
--Type <RET> for more, q to quit, c to continue without paging--
      0x7ffff7fa1000     0x7ffff7fa2000     0x1000   0x1d0000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
      0x7ffff7fa2000     0x7ffff7fad000     0xb000   0x1d0000 /usr/lib/x86_64-linux-gnu/libstdc++.so.6.0.26
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