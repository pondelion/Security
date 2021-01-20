
### compile

```
$ g++ -g for.cc -o for
```

### execute

```
$ ./for
0
2
4
5
8
```

### disassemble

```
$ objdump -S -d -M intel if
```

- main

```
0000000000001175 <main>:
#include <iostream>


int main() {
    1175:       55                      push   rbp
    1176:       48 89 e5                mov    rbp,rsp
    1179:       48 83 ec 30             sub    rsp,0x30
    117d:       64 48 8b 04 25 28 00    mov    rax,QWORD PTR fs:0x28  # スタックカナリア
    1184:       00 00 
    1186:       48 89 45 f8             mov    QWORD PTR [rbp-0x8],rax
    118a:       31 c0                   xor    eax,eax

  int arr[5] = {0, 2, 4, 5, 8};
    118c:       c7 45 e0 00 00 00 00    mov    DWORD PTR [rbp-0x20],0x0
    1193:       c7 45 e4 02 00 00 00    mov    DWORD PTR [rbp-0x1c],0x2
    119a:       c7 45 e8 04 00 00 00    mov    DWORD PTR [rbp-0x18],0x4
    11a1:       c7 45 ec 05 00 00 00    mov    DWORD PTR [rbp-0x14],0x5
    11a8:       c7 45 f0 08 00 00 00    mov    DWORD PTR [rbp-0x10],0x8

  for (size_t i=0; i < sizeof(arr) / sizeof(arr[0]); ++i) {
    11af:       48 c7 45 d8 00 00 00    mov    QWORD PTR [rbp-0x28],0x0
    11b6:       00 
    11b7:       48 83 7d d8 04          cmp    QWORD PTR [rbp-0x28],0x4
    11bc:       77 32                   ja     11f0 <main+0x7b>
    std::cout << arr[i] << std::endl;
    11be:       48 8b 45 d8             mov    rax,QWORD PTR [rbp-0x28]
    11c2:       8b 44 85 e0             mov    eax,DWORD PTR [rbp+rax*4-0x20]
    11c6:       89 c6                   mov    esi,eax
    11c8:       48 8d 3d 51 2e 00 00    lea    rdi,[rip+0x2e51]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11cf:       e8 9c fe ff ff          call   1070 <_ZNSolsEi@plt>
    11d4:       48 89 c2                mov    rdx,rax
    11d7:       48 8b 05 f2 2d 00 00    mov    rax,QWORD PTR [rip+0x2df2]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11de:       48 89 c6                mov    rsi,rax
    11e1:       48 89 d7                mov    rdi,rdx
    11e4:       e8 57 fe ff ff          call   1040 <_ZNSolsEPFRSoS_E@plt>
  for (size_t i=0; i < sizeof(arr) / sizeof(arr[0]); ++i) {
    11e9:       48 83 45 d8 01          add    QWORD PTR [rbp-0x28],0x1
    11ee:       eb c7                   jmp    11b7 <main+0x42>
  }

  return 0;
    11f0:       b8 00 00 00 00          mov    eax,0x0
    11f5:       48 8b 4d f8             mov    rcx,QWORD PTR [rbp-0x8]
    11f9:       64 48 33 0c 25 28 00    xor    rcx,QWORD PTR fs:0x28
    1200:       00 00 
    1202:       74 05                   je     1209 <main+0x94>
    1204:       e8 47 fe ff ff          call   1050 <__stack_chk_fail@plt>
    1209:       c9                      leave  
    120a:       c3                      ret    

```

<details><summary>whole</summary><div>

```
$ objdump -S -d -M intel for

for:     ファイル形式 elf64-x86-64


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
    1020:       ff 35 6a 2f 00 00       push   QWORD PTR [rip+0x2f6a]        # 3f90 <_GLOBAL_OFFSET_TABLE_+0x8>
    1026:       ff 25 6c 2f 00 00       jmp    QWORD PTR [rip+0x2f6c]        # 3f98 <_GLOBAL_OFFSET_TABLE_+0x10>
    102c:       0f 1f 40 00             nop    DWORD PTR [rax+0x0]

0000000000001030 <__cxa_atexit@plt>:
    1030:       ff 25 6a 2f 00 00       jmp    QWORD PTR [rip+0x2f6a]        # 3fa0 <__cxa_atexit@GLIBC_2.2.5>
    1036:       68 00 00 00 00          push   0x0
    103b:       e9 e0 ff ff ff          jmp    1020 <.plt>

0000000000001040 <_ZNSolsEPFRSoS_E@plt>:
    1040:       ff 25 62 2f 00 00       jmp    QWORD PTR [rip+0x2f62]        # 3fa8 <_ZNSolsEPFRSoS_E@GLIBCXX_3.4>
    1046:       68 01 00 00 00          push   0x1
    104b:       e9 d0 ff ff ff          jmp    1020 <.plt>

0000000000001050 <__stack_chk_fail@plt>:
    1050:       ff 25 5a 2f 00 00       jmp    QWORD PTR [rip+0x2f5a]        # 3fb0 <__stack_chk_fail@GLIBC_2.4>
    1056:       68 02 00 00 00          push   0x2
    105b:       e9 c0 ff ff ff          jmp    1020 <.plt>

0000000000001060 <_ZNSt8ios_base4InitC1Ev@plt>:
    1060:       ff 25 52 2f 00 00       jmp    QWORD PTR [rip+0x2f52]        # 3fb8 <_ZNSt8ios_base4InitC1Ev@GLIBCXX_3.4>
    1066:       68 03 00 00 00          push   0x3
    106b:       e9 b0 ff ff ff          jmp    1020 <.plt>

0000000000001070 <_ZNSolsEi@plt>:
    1070:       ff 25 4a 2f 00 00       jmp    QWORD PTR [rip+0x2f4a]        # 3fc0 <_ZNSolsEi@GLIBCXX_3.4>
    1076:       68 04 00 00 00          push   0x4
    107b:       e9 a0 ff ff ff          jmp    1020 <.plt>

セクション .plt.got の逆アセンブル:

0000000000001080 <__cxa_finalize@plt>:
    1080:       ff 25 42 2f 00 00       jmp    QWORD PTR [rip+0x2f42]        # 3fc8 <__cxa_finalize@GLIBC_2.2.5>
    1086:       66 90                   xchg   ax,ax

セクション .text の逆アセンブル:

0000000000001090 <_start>:
    1090:       31 ed                   xor    ebp,ebp
    1092:       49 89 d1                mov    r9,rdx
    1095:       5e                      pop    rsi
    1096:       48 89 e2                mov    rdx,rsp
    1099:       48 83 e4 f0             and    rsp,0xfffffffffffffff0
    109d:       50                      push   rax
    109e:       54                      push   rsp
    109f:       4c 8d 05 2a 02 00 00    lea    r8,[rip+0x22a]        # 12d0 <__libc_csu_fini>
    10a6:       48 8d 0d c3 01 00 00    lea    rcx,[rip+0x1c3]        # 1270 <__libc_csu_init>
    10ad:       48 8d 3d c1 00 00 00    lea    rdi,[rip+0xc1]        # 1175 <main>
    10b4:       ff 15 26 2f 00 00       call   QWORD PTR [rip+0x2f26]        # 3fe0 <__libc_start_main@GLIBC_2.2.5>
    10ba:       f4                      hlt    
    10bb:       0f 1f 44 00 00          nop    DWORD PTR [rax+rax*1+0x0]

00000000000010c0 <deregister_tm_clones>:
    10c0:       48 8d 3d 49 2f 00 00    lea    rdi,[rip+0x2f49]        # 4010 <__TMC_END__>
    10c7:       48 8d 05 42 2f 00 00    lea    rax,[rip+0x2f42]        # 4010 <__TMC_END__>
    10ce:       48 39 f8                cmp    rax,rdi
    10d1:       74 15                   je     10e8 <deregister_tm_clones+0x28>
    10d3:       48 8b 05 fe 2e 00 00    mov    rax,QWORD PTR [rip+0x2efe]        # 3fd8 <_ITM_deregisterTMCloneTable>
    10da:       48 85 c0                test   rax,rax
    10dd:       74 09                   je     10e8 <deregister_tm_clones+0x28>
    10df:       ff e0                   jmp    rax
    10e1:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    10e8:       c3                      ret    
    10e9:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

00000000000010f0 <register_tm_clones>:
    10f0:       48 8d 3d 19 2f 00 00    lea    rdi,[rip+0x2f19]        # 4010 <__TMC_END__>
    10f7:       48 8d 35 12 2f 00 00    lea    rsi,[rip+0x2f12]        # 4010 <__TMC_END__>
    10fe:       48 29 fe                sub    rsi,rdi
    1101:       48 c1 fe 03             sar    rsi,0x3
    1105:       48 89 f0                mov    rax,rsi
    1108:       48 c1 e8 3f             shr    rax,0x3f
    110c:       48 01 c6                add    rsi,rax
    110f:       48 d1 fe                sar    rsi,1
    1112:       74 14                   je     1128 <register_tm_clones+0x38>
    1114:       48 8b 05 d5 2e 00 00    mov    rax,QWORD PTR [rip+0x2ed5]        # 3ff0 <_ITM_registerTMCloneTable>
    111b:       48 85 c0                test   rax,rax
    111e:       74 08                   je     1128 <register_tm_clones+0x38>
    1120:       ff e0                   jmp    rax
    1122:       66 0f 1f 44 00 00       nop    WORD PTR [rax+rax*1+0x0]
    1128:       c3                      ret    
    1129:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001130 <__do_global_dtors_aux>:
    1130:       80 3d f9 2f 00 00 00    cmp    BYTE PTR [rip+0x2ff9],0x0        # 4130 <completed.7963>
    1137:       75 2f                   jne    1168 <__do_global_dtors_aux+0x38>
    1139:       55                      push   rbp
    113a:       48 83 3d 86 2e 00 00    cmp    QWORD PTR [rip+0x2e86],0x0        # 3fc8 <__cxa_finalize@GLIBC_2.2.5>
    1141:       00 
    1142:       48 89 e5                mov    rbp,rsp
    1145:       74 0c                   je     1153 <__do_global_dtors_aux+0x23>
    1147:       48 8b 3d ba 2e 00 00    mov    rdi,QWORD PTR [rip+0x2eba]        # 4008 <__dso_handle>
    114e:       e8 2d ff ff ff          call   1080 <__cxa_finalize@plt>
    1153:       e8 68 ff ff ff          call   10c0 <deregister_tm_clones>
    1158:       c6 05 d1 2f 00 00 01    mov    BYTE PTR [rip+0x2fd1],0x1        # 4130 <completed.7963>
    115f:       5d                      pop    rbp
    1160:       c3                      ret    
    1161:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]
    1168:       c3                      ret    
    1169:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001170 <frame_dummy>:
    1170:       e9 7b ff ff ff          jmp    10f0 <register_tm_clones>

0000000000001175 <main>:
#include <iostream>


int main() {
    1175:       55                      push   rbp
    1176:       48 89 e5                mov    rbp,rsp
    1179:       48 83 ec 30             sub    rsp,0x30
    117d:       64 48 8b 04 25 28 00    mov    rax,QWORD PTR fs:0x28
    1184:       00 00 
    1186:       48 89 45 f8             mov    QWORD PTR [rbp-0x8],rax
    118a:       31 c0                   xor    eax,eax

  int arr[5] = {0, 2, 4, 5, 8};
    118c:       c7 45 e0 00 00 00 00    mov    DWORD PTR [rbp-0x20],0x0
    1193:       c7 45 e4 02 00 00 00    mov    DWORD PTR [rbp-0x1c],0x2
    119a:       c7 45 e8 04 00 00 00    mov    DWORD PTR [rbp-0x18],0x4
    11a1:       c7 45 ec 05 00 00 00    mov    DWORD PTR [rbp-0x14],0x5
    11a8:       c7 45 f0 08 00 00 00    mov    DWORD PTR [rbp-0x10],0x8

  for (size_t i=0; i < sizeof(arr) / sizeof(arr[0]); ++i) {
    11af:       48 c7 45 d8 00 00 00    mov    QWORD PTR [rbp-0x28],0x0
    11b6:       00 
    11b7:       48 83 7d d8 04          cmp    QWORD PTR [rbp-0x28],0x4
    11bc:       77 32                   ja     11f0 <main+0x7b>
    std::cout << arr[i] << std::endl;
    11be:       48 8b 45 d8             mov    rax,QWORD PTR [rbp-0x28]
    11c2:       8b 44 85 e0             mov    eax,DWORD PTR [rbp+rax*4-0x20]
    11c6:       89 c6                   mov    esi,eax
    11c8:       48 8d 3d 51 2e 00 00    lea    rdi,[rip+0x2e51]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11cf:       e8 9c fe ff ff          call   1070 <_ZNSolsEi@plt>
    11d4:       48 89 c2                mov    rdx,rax
    11d7:       48 8b 05 f2 2d 00 00    mov    rax,QWORD PTR [rip+0x2df2]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11de:       48 89 c6                mov    rsi,rax
    11e1:       48 89 d7                mov    rdi,rdx
    11e4:       e8 57 fe ff ff          call   1040 <_ZNSolsEPFRSoS_E@plt>
  for (size_t i=0; i < sizeof(arr) / sizeof(arr[0]); ++i) {
    11e9:       48 83 45 d8 01          add    QWORD PTR [rbp-0x28],0x1
    11ee:       eb c7                   jmp    11b7 <main+0x42>
  }

  return 0;
    11f0:       b8 00 00 00 00          mov    eax,0x0
    11f5:       48 8b 4d f8             mov    rcx,QWORD PTR [rbp-0x8]
    11f9:       64 48 33 0c 25 28 00    xor    rcx,QWORD PTR fs:0x28
    1200:       00 00 
    1202:       74 05                   je     1209 <main+0x94>
    1204:       e8 47 fe ff ff          call   1050 <__stack_chk_fail@plt>
    1209:       c9                      leave  
    120a:       c3                      ret    

000000000000120b <_Z41__static_initialization_and_destruction_0ii>:
    120b:       55                      push   rbp
    120c:       48 89 e5                mov    rbp,rsp
    120f:       48 83 ec 10             sub    rsp,0x10
    1213:       89 7d fc                mov    DWORD PTR [rbp-0x4],edi
    1216:       89 75 f8                mov    DWORD PTR [rbp-0x8],esi
    1219:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    121d:       75 32                   jne    1251 <_Z41__static_initialization_and_destruction_0ii+0x46>
    121f:       81 7d f8 ff ff 00 00    cmp    DWORD PTR [rbp-0x8],0xffff
    1226:       75 29                   jne    1251 <_Z41__static_initialization_and_destruction_0ii+0x46>
  extern wostream wclog;        /// Linked to standard error (buffered)
#endif
  //@}

  // For construction of filebuffers for cout, cin, cerr, clog et. al.
  static ios_base::Init __ioinit;
    1228:       48 8d 3d 02 2f 00 00    lea    rdi,[rip+0x2f02]        # 4131 <_ZStL8__ioinit>
    122f:       e8 2c fe ff ff          call   1060 <_ZNSt8ios_base4InitC1Ev@plt>
    1234:       48 8d 15 cd 2d 00 00    lea    rdx,[rip+0x2dcd]        # 4008 <__dso_handle>
    123b:       48 8d 35 ef 2e 00 00    lea    rsi,[rip+0x2eef]        # 4131 <_ZStL8__ioinit>
    1242:       48 8b 05 af 2d 00 00    mov    rax,QWORD PTR [rip+0x2daf]        # 3ff8 <_ZNSt8ios_base4InitD1Ev@GLIBCXX_3.4>
    1249:       48 89 c7                mov    rdi,rax
    124c:       e8 df fd ff ff          call   1030 <__cxa_atexit@plt>
    1251:       90                      nop
    1252:       c9                      leave  
    1253:       c3                      ret    

0000000000001254 <_GLOBAL__sub_I_main>:
    1254:       55                      push   rbp
    1255:       48 89 e5                mov    rbp,rsp
    1258:       be ff ff 00 00          mov    esi,0xffff
    125d:       bf 01 00 00 00          mov    edi,0x1
    1262:       e8 a4 ff ff ff          call   120b <_Z41__static_initialization_and_destruction_0ii>
    1267:       5d                      pop    rbp
    1268:       c3                      ret    
    1269:       0f 1f 80 00 00 00 00    nop    DWORD PTR [rax+0x0]

0000000000001270 <__libc_csu_init>:
    1270:       41 57                   push   r15
    1272:       49 89 d7                mov    r15,rdx
    1275:       41 56                   push   r14
    1277:       49 89 f6                mov    r14,rsi
    127a:       41 55                   push   r13
    127c:       41 89 fd                mov    r13d,edi
    127f:       41 54                   push   r12
    1281:       4c 8d 25 e8 2a 00 00    lea    r12,[rip+0x2ae8]        # 3d70 <__frame_dummy_init_array_entry>
    1288:       55                      push   rbp
    1289:       48 8d 2d f0 2a 00 00    lea    rbp,[rip+0x2af0]        # 3d80 <__init_array_end>
    1290:       53                      push   rbx
    1291:       4c 29 e5                sub    rbp,r12
    1294:       48 83 ec 08             sub    rsp,0x8
    1298:       e8 63 fd ff ff          call   1000 <_init>
    129d:       48 c1 fd 03             sar    rbp,0x3
    12a1:       74 1b                   je     12be <__libc_csu_init+0x4e>
    12a3:       31 db                   xor    ebx,ebx
    12a5:       0f 1f 00                nop    DWORD PTR [rax]
    12a8:       4c 89 fa                mov    rdx,r15
    12ab:       4c 89 f6                mov    rsi,r14
    12ae:       44 89 ef                mov    edi,r13d
    12b1:       41 ff 14 dc             call   QWORD PTR [r12+rbx*8]
    12b5:       48 83 c3 01             add    rbx,0x1
    12b9:       48 39 dd                cmp    rbp,rbx
    12bc:       75 ea                   jne    12a8 <__libc_csu_init+0x38>
    12be:       48 83 c4 08             add    rsp,0x8
    12c2:       5b                      pop    rbx
    12c3:       5d                      pop    rbp
    12c4:       41 5c                   pop    r12
    12c6:       41 5d                   pop    r13
    12c8:       41 5e                   pop    r14
    12ca:       41 5f                   pop    r15
    12cc:       c3                      ret    
    12cd:       0f 1f 00                nop    DWORD PTR [rax]

00000000000012d0 <__libc_csu_fini>:
    12d0:       c3                      ret    

セクション .fini の逆アセンブル:

00000000000012d4 <_fini>:
    12d4:       48 83 ec 08             sub    rsp,0x8
    12d8:       48 83 c4 08             add    rsp,0x8
    12dc:       c3                      ret
```

</details></div>

### debug (gdb)

```
$ gdb ./for
```

```
(gdb) break main
(gdb) run
```

#### memory map

```
(gdb) info proc map
process 23424
Mapped address spaces:

          Start Addr           End Addr       Size     Offset objfile
      0x555555554000     0x555555555000     0x1000        0x0 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/for_loop/for
      0x555555555000     0x555555556000     0x1000     0x1000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/for_loop/for
      0x555555556000     0x555555557000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/for_loop/for
      0x555555557000     0x555555558000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/for_loop/for
      0x555555558000     0x555555559000     0x1000     0x3000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/for_loop/for
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

- disassemble main 

```
(gdb) set disassembly-flavor intel
```

```
(gdb) disassemble main
Dump of assembler code for function main():
   0x0000555555555175 <+0>:     push   rbp
   0x0000555555555176 <+1>:     mov    rbp,rsp
   0x0000555555555179 <+4>:     sub    rsp,0x30
=> 0x000055555555517d <+8>:     mov    rax,QWORD PTR fs:0x28
   0x0000555555555186 <+17>:    mov    QWORD PTR [rbp-0x8],rax
   0x000055555555518a <+21>:    xor    eax,eax
   0x000055555555518c <+23>:    mov    DWORD PTR [rbp-0x20],0x0
   0x0000555555555193 <+30>:    mov    DWORD PTR [rbp-0x1c],0x2
   0x000055555555519a <+37>:    mov    DWORD PTR [rbp-0x18],0x4
   0x00005555555551a1 <+44>:    mov    DWORD PTR [rbp-0x14],0x5
   0x00005555555551a8 <+51>:    mov    DWORD PTR [rbp-0x10],0x8
   0x00005555555551af <+58>:    mov    QWORD PTR [rbp-0x28],0x0
   0x00005555555551b7 <+66>:    cmp    QWORD PTR [rbp-0x28],0x4
   0x00005555555551bc <+71>:    ja     0x5555555551f0 <main()+123>
--Type <RET> for more, q to quit, c to continue without paging--
   0x00005555555551be <+73>:    mov    rax,QWORD PTR [rbp-0x28]
   0x00005555555551c2 <+77>:    mov    eax,DWORD PTR [rbp+rax*4-0x20]
   0x00005555555551c6 <+81>:    mov    esi,eax
   0x00005555555551c8 <+83>:    lea    rdi,[rip+0x2e51]        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x00005555555551cf <+90>:    call   0x555555555070 <_ZNSolsEi@plt>
   0x00005555555551d4 <+95>:    mov    rdx,rax
   0x00005555555551d7 <+98>:    mov    rax,QWORD PTR [rip+0x2df2]        # 0x555555557fd0
   0x00005555555551de <+105>:   mov    rsi,rax
   0x00005555555551e1 <+108>:   mov    rdi,rdx
   0x00005555555551e4 <+111>:   call   0x555555555040 <_ZNSolsEPFRSoS_E@plt>
   0x00005555555551e9 <+116>:   add    QWORD PTR [rbp-0x28],0x1
   0x00005555555551ee <+121>:   jmp    0x5555555551b7 <main()+66>
   0x00005555555551f0 <+123>:   mov    eax,0x0
   0x00005555555551f5 <+128>:   mov    rcx,QWORD PTR [rbp-0x8]
   0x00005555555551f9 <+132>:   xor    rcx,QWORD PTR fs:0x28
--Type <RET> for more, q to quit, c to continue without paging--
   0x0000555555555202 <+141>:   je     0x555555555209 <main()+148>
   0x0000555555555204 <+143>:   call   0x555555555050 <__stack_chk_fail@plt>
   0x0000555555555209 <+148>:   leave  
   0x000055555555520a <+149>:   ret    
End of assembler dump.
```