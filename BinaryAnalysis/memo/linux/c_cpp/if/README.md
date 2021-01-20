
### compile

```
$ g++ -g if.cc -o if
```

### execute

```
$ ./if
a is one
```

### disassemble

```
$ objdump -S -d -M intel if
```

- main

```
0000000000001165 <main>:
#include <iostream>


int main() {
    1165:       55                      push   rbp
    1166:       48 89 e5                mov    rbp,rsp
    1169:       48 83 ec 10             sub    rsp,0x10
  int a = 1;
    116d:       c7 45 fc 01 00 00 00    mov    DWORD PTR [rbp-0x4],0x1

  if (a == 0) {
    1174:       83 7d fc 00             cmp    DWORD PTR [rbp-0x4],0x0
    1178:       75 2a                   jne    11a4 <main+0x3f>
    std::cout << "a is zero" << std::endl;
    117a:       48 8d 35 84 0e 00 00    lea    rsi,[rip+0xe84]        # 2005 <_ZStL19piecewise_construct+0x1>
    1181:       48 8d 3d 98 2e 00 00    lea    rdi,[rip+0x2e98]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    1188:       e8 b3 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    118d:       48 89 c2                mov    rdx,rax
    1190:       48 8b 05 39 2e 00 00    mov    rax,QWORD PTR [rip+0x2e39]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    1197:       48 89 c6                mov    rsi,rax
    119a:       48 89 d7                mov    rdi,rdx
    119d:       e8 ae fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
    11a2:       eb 58                   jmp    11fc <main+0x97>
  } else if (a == 1) {
    11a4:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    11a8:       75 2a                   jne    11d4 <main+0x6f>
    std::cout << "a is one" << std::endl;
    11aa:       48 8d 35 5e 0e 00 00    lea    rsi,[rip+0xe5e]        # 200f <_ZStL19piecewise_construct+0xb>
    11b1:       48 8d 3d 68 2e 00 00    lea    rdi,[rip+0x2e68]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11b8:       e8 83 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    11bd:       48 89 c2                mov    rdx,rax
    11c0:       48 8b 05 09 2e 00 00    mov    rax,QWORD PTR [rip+0x2e09]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11c7:       48 89 c6                mov    rsi,rax
    11ca:       48 89 d7                mov    rdi,rdx
    11cd:       e8 7e fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
    11d2:       eb 28                   jmp    11fc <main+0x97>
  } else {
    std::cout << "other" << std::endl;
    11d4:       48 8d 35 3d 0e 00 00    lea    rsi,[rip+0xe3d]        # 2018 <_ZStL19piecewise_construct+0x14>
    11db:       48 8d 3d 3e 2e 00 00    lea    rdi,[rip+0x2e3e]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11e2:       e8 59 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    11e7:       48 89 c2                mov    rdx,rax
    11ea:       48 8b 05 df 2d 00 00    mov    rax,QWORD PTR [rip+0x2ddf]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11f1:       48 89 c6                mov    rsi,rax
    11f4:       48 89 d7                mov    rdi,rdx
    11f7:       e8 54 fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
  }

  return 0;
    11fc:       b8 00 00 00 00          mov    eax,0x0
}
    1201:       c9                      leave  
    1202:       c3                      ret   
```

<details><summary>whole</summary><div>

```
if:     ファイル形式 elf64-x86-64


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
    108f:       4c 8d 05 3a 02 00 00    lea    r8,[rip+0x23a]        # 12d0 <__libc_csu_fini>
    1096:       48 8d 0d d3 01 00 00    lea    rcx,[rip+0x1d3]        # 1270 <__libc_csu_init>
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
    1169:       48 83 ec 10             sub    rsp,0x10
  int a = 1;
    116d:       c7 45 fc 01 00 00 00    mov    DWORD PTR [rbp-0x4],0x1

  if (a == 0) {
    1174:       83 7d fc 00             cmp    DWORD PTR [rbp-0x4],0x0
    1178:       75 2a                   jne    11a4 <main+0x3f>
    std::cout << "a is zero" << std::endl;
    117a:       48 8d 35 84 0e 00 00    lea    rsi,[rip+0xe84]        # 2005 <_ZStL19piecewise_construct+0x1>
    1181:       48 8d 3d 98 2e 00 00    lea    rdi,[rip+0x2e98]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    1188:       e8 b3 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    118d:       48 89 c2                mov    rdx,rax
    1190:       48 8b 05 39 2e 00 00    mov    rax,QWORD PTR [rip+0x2e39]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    1197:       48 89 c6                mov    rsi,rax
    119a:       48 89 d7                mov    rdi,rdx
    119d:       e8 ae fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
    11a2:       eb 58                   jmp    11fc <main+0x97>
  } else if (a == 1) {
    11a4:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    11a8:       75 2a                   jne    11d4 <main+0x6f>
    std::cout << "a is one" << std::endl;
    11aa:       48 8d 35 5e 0e 00 00    lea    rsi,[rip+0xe5e]        # 200f <_ZStL19piecewise_construct+0xb>
    11b1:       48 8d 3d 68 2e 00 00    lea    rdi,[rip+0x2e68]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11b8:       e8 83 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    11bd:       48 89 c2                mov    rdx,rax
    11c0:       48 8b 05 09 2e 00 00    mov    rax,QWORD PTR [rip+0x2e09]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11c7:       48 89 c6                mov    rsi,rax
    11ca:       48 89 d7                mov    rdi,rdx
    11cd:       e8 7e fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
    11d2:       eb 28                   jmp    11fc <main+0x97>
  } else {
    std::cout << "other" << std::endl;
    11d4:       48 8d 35 3d 0e 00 00    lea    rsi,[rip+0xe3d]        # 2018 <_ZStL19piecewise_construct+0x14>
    11db:       48 8d 3d 3e 2e 00 00    lea    rdi,[rip+0x2e3e]        # 4020 <_ZSt4cout@@GLIBCXX_3.4>
    11e2:       e8 59 fe ff ff          call   1040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
    11e7:       48 89 c2                mov    rdx,rax
    11ea:       48 8b 05 df 2d 00 00    mov    rax,QWORD PTR [rip+0x2ddf]        # 3fd0 <_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_@GLIBCXX_3.4>
    11f1:       48 89 c6                mov    rsi,rax
    11f4:       48 89 d7                mov    rdi,rdx
    11f7:       e8 54 fe ff ff          call   1050 <_ZNSolsEPFRSoS_E@plt>
  }

  return 0;
    11fc:       b8 00 00 00 00          mov    eax,0x0
}
    1201:       c9                      leave  
    1202:       c3                      ret    

0000000000001203 <_Z41__static_initialization_and_destruction_0ii>:
    1203:       55                      push   rbp
    1204:       48 89 e5                mov    rbp,rsp
    1207:       48 83 ec 10             sub    rsp,0x10
    120b:       89 7d fc                mov    DWORD PTR [rbp-0x4],edi
    120e:       89 75 f8                mov    DWORD PTR [rbp-0x8],esi
    1211:       83 7d fc 01             cmp    DWORD PTR [rbp-0x4],0x1
    1215:       75 32                   jne    1249 <_Z41__static_initialization_and_destruction_0ii+0x46>
    1217:       81 7d f8 ff ff 00 00    cmp    DWORD PTR [rbp-0x8],0xffff
    121e:       75 29                   jne    1249 <_Z41__static_initialization_and_destruction_0ii+0x46>
  extern wostream wclog;        /// Linked to standard error (buffered)
#endif
  //@}

  // For construction of filebuffers for cout, cin, cerr, clog et. al.
  static ios_base::Init __ioinit;
    1220:       48 8d 3d 0a 2f 00 00    lea    rdi,[rip+0x2f0a]        # 4131 <_ZStL8__ioinit>
    1227:       e8 34 fe ff ff          call   1060 <_ZNSt8ios_base4InitC1Ev@plt>
    122c:       48 8d 15 d5 2d 00 00    lea    rdx,[rip+0x2dd5]        # 4008 <__dso_handle>
    1233:       48 8d 35 f7 2e 00 00    lea    rsi,[rip+0x2ef7]        # 4131 <_ZStL8__ioinit>
    123a:       48 8b 05 b7 2d 00 00    mov    rax,QWORD PTR [rip+0x2db7]        # 3ff8 <_ZNSt8ios_base4InitD1Ev@GLIBCXX_3.4>
    1241:       48 89 c7                mov    rdi,rax
    1244:       e8 e7 fd ff ff          call   1030 <__cxa_atexit@plt>
    1249:       90                      nop
    124a:       c9                      leave  
    124b:       c3                      ret    

000000000000124c <_GLOBAL__sub_I_main>:
    124c:       55                      push   rbp
    124d:       48 89 e5                mov    rbp,rsp
    1250:       be ff ff 00 00          mov    esi,0xffff
    1255:       bf 01 00 00 00          mov    edi,0x1
    125a:       e8 a4 ff ff ff          call   1203 <_Z41__static_initialization_and_destruction_0ii>
    125f:       5d                      pop    rbp
    1260:       c3                      ret    
    1261:       66 2e 0f 1f 84 00 00    nop    WORD PTR cs:[rax+rax*1+0x0]
    1268:       00 00 00 
    126b:       0f 1f 44 00 00          nop    DWORD PTR [rax+rax*1+0x0]

0000000000001270 <__libc_csu_init>:
    1270:       41 57                   push   r15
    1272:       49 89 d7                mov    r15,rdx
    1275:       41 56                   push   r14
    1277:       49 89 f6                mov    r14,rsi
    127a:       41 55                   push   r13
    127c:       41 89 fd                mov    r13d,edi
    127f:       41 54                   push   r12
    1281:       4c 8d 25 f0 2a 00 00    lea    r12,[rip+0x2af0]        # 3d78 <__frame_dummy_init_array_entry>
    1288:       55                      push   rbp
    1289:       48 8d 2d f8 2a 00 00    lea    rbp,[rip+0x2af8]        # 3d88 <__init_array_end>
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
$ gdb if
```

```
(gdb) break main
(gdb) run
```

#### memory map

```
(gdb) info proc map
process 9078
Mapped address spaces:

          Start Addr           End Addr       Size     Offset objfile
      0x555555554000     0x555555555000     0x1000        0x0 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/if/if
      0x555555555000     0x555555556000     0x1000     0x1000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/if/if
      0x555555556000     0x555555557000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/if/if
      0x555555557000     0x555555558000     0x1000     0x2000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/if/if
      0x555555558000     0x555555559000     0x1000     0x3000 /home/ym/github/BinaryAnalysis/memo/linux/c_cpp/if/if
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
   0x0000555555555165 <+0>:     push   rbp
   0x0000555555555166 <+1>:     mov    rbp,rsp
   0x0000555555555169 <+4>:     sub    rsp,0x10
=> 0x000055555555516d <+8>:     mov    DWORD PTR [rbp-0x4],0x1
   0x0000555555555174 <+15>:    cmp    DWORD PTR [rbp-0x4],0x0
   0x0000555555555178 <+19>:    jne    0x5555555551a4 <main()+63>
   0x000055555555517a <+21>:    lea    rsi,[rip+0xe84]        # 0x555555556005
   0x0000555555555181 <+28>:    lea    rdi,[rip+0x2e98]        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x0000555555555188 <+35>:    call   0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
   0x000055555555518d <+40>:    mov    rdx,rax
   0x0000555555555190 <+43>:    mov    rax,QWORD PTR [rip+0x2e39]        # 0x555555557fd0
   0x0000555555555197 <+50>:    mov    rsi,rax
   0x000055555555519a <+53>:    mov    rdi,rdx
   0x000055555555519d <+56>:    call   0x555555555050 <_ZNSolsEPFRSoS_E@plt>
--Type <RET> for more, q to quit, c to continue without paging--
   0x00005555555551a2 <+61>:    jmp    0x5555555551fc <main()+151>
   0x00005555555551a4 <+63>:    cmp    DWORD PTR [rbp-0x4],0x1
   0x00005555555551a8 <+67>:    jne    0x5555555551d4 <main()+111>
   0x00005555555551aa <+69>:    lea    rsi,[rip+0xe5e]        # 0x55555555600f
   0x00005555555551b1 <+76>:    lea    rdi,[rip+0x2e68]        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x00005555555551b8 <+83>:    call   0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
   0x00005555555551bd <+88>:    mov    rdx,rax
   0x00005555555551c0 <+91>:    mov    rax,QWORD PTR [rip+0x2e09]        # 0x555555557fd0
   0x00005555555551c7 <+98>:    mov    rsi,rax
   0x00005555555551ca <+101>:   mov    rdi,rdx
   0x00005555555551cd <+104>:   call   0x555555555050 <_ZNSolsEPFRSoS_E@plt>
   0x00005555555551d2 <+109>:   jmp    0x5555555551fc <main()+151>
   0x00005555555551d4 <+111>:   lea    rsi,[rip+0xe3d]        # 0x555555556018
   0x00005555555551db <+118>:   lea    rdi,[rip+0x2e3e]        # 0x555555558020 <_ZSt4cout@@GLIBCXX_3.4>
   0x00005555555551e2 <+125>:   call   0x555555555040 <_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc@plt>
--Type <RET> for more, q to quit, c to continue without paging--
   0x00005555555551e7 <+130>:   mov    rdx,rax
   0x00005555555551ea <+133>:   mov    rax,QWORD PTR [rip+0x2ddf]        # 0x555555557fd0
   0x00005555555551f1 <+140>:   mov    rsi,rax
   0x00005555555551f4 <+143>:   mov    rdi,rdx
   0x00005555555551f7 <+146>:   call   0x555555555050 <_ZNSolsEPFRSoS_E@plt>
   0x00005555555551fc <+151>:   mov    eax,0x0
   0x0000555555555201 <+156>:   leave  
   0x0000555555555202 <+157>:   ret    
End of assembler dump.
```

### debug (radare)

```
$ r2 if
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
┌ 158: main ();
│           ; var int64_t var_4h @ rbp-0x4
│           0x00001165      55             push rbp                    ; if.cc:4 int main() {
│           0x00001166      4889e5         mov rbp, rsp
│           0x00001169      4883ec10       sub rsp, 0x10
│           0x0000116d      c745fc010000.  mov dword [var_4h], 1       ; if.cc:5   int a = 1;
│           0x00001174      837dfc00       cmp dword [var_4h], 0       ; if.cc:7   if (a == 0) {
│       ┌─< 0x00001178      752a           jne 0x11a4
│       │   0x0000117a      488d35840e00.  lea rsi, str.a_is_zero      ; if.cc:8     std::cout << "a is zero" << std::endl; ; 0x2005 ; "a is zero"
│       │   0x00001181      488d3d982e00.  lea rdi, obj.std::cout      ; sym..bss
│       │                                                              ; 0x4020
│       │   0x00001188      e8b3feffff     call sym std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*) ; sym.imp.std::basic_ostream_char__std::char_traits_char_____std::operator____std::char_traits_char____std::basic_ostream_char__std::char_traits_char______char_const
│       │   0x0000118d      4889c2         mov rdx, rax
│       │   0x00001190      488b05392e00.  mov rax, qword [method.std::basic_ostream_char__std::char_traits_char_____std::endl_char__std.char_traits_char____std::basic_ostream_char__std::char_traits_char] ; [0x3fd0:8]=0
│       │   0x00001197      4889c6         mov rsi, rax                ; /wctype-wchar.h:69
│       │   0x0000119a      4889d7         mov rdi, rdx
│       │   0x0000119d      e8aefeffff     call sym std::ostream::operator<<(std::ostream& (*)(std::ostream&)) ; sym.imp.std::ostream::operator___std::ostream______std::ostream
│      ┌──< 0x000011a2      eb58           jmp 0x11fc
│      │└─> 0x000011a4      837dfc01       cmp dword [var_4h], 1       ; if.cc:9   } else if (a == 1) {
│      │┌─< 0x000011a8      752a           jne 0x11d4
│      ││   0x000011aa      488d355e0e00.  lea rsi, str.a_is_one       ; if.cc:10     std::cout << "a is one" << std::endl; ; 0x200f ; "a is one"
│      ││   0x000011b1      488d3d682e00.  lea rdi, obj.std::cout      ; sym..bss
│      ││                                                              ; 0x4020
│      ││   0x000011b8      e883feffff     call sym std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*) ; sym.imp.std::basic_ostream_char__std::char_traits_char_____std::operator____std::char_traits_char____std::basic_ostream_char__std::char_traits_char______char_const
│      ││   0x000011bd      4889c2         mov rdx, rax
│      ││   0x000011c0      488b05092e00.  mov rax, qword [method.std::basic_ostream_char__std::char_traits_char_____std::endl_char__std.char_traits_char____std::basic_ostream_char__std::char_traits_char] ; [0x3fd0:8]=0
│      ││   0x000011c7      4889c6         mov rsi, rax
│      ││   0x000011ca      4889d7         mov rdi, rdx                ; /wctype-wchar.h:105
│      ││   0x000011cd      e87efeffff     call sym std::ostream::operator<<(std::ostream& (*)(std::ostream&)) ; /wctype-wchar.h:112 ; sym.imp.std::ostream::operator___std::ostream______std::ostream
│     ┌───< 0x000011d2      eb28           jmp 0x11fc
│     ││└─> 0x000011d4      488d353d0e00.  lea rsi, str.other          ; if.cc:12     std::cout << "other" << std::endl; ; 0x2018 ; "other"
│     ││    0x000011db      488d3d3e2e00.  lea rdi, obj.std::cout      ; sym..bss
│     ││                                                               ; 0x4020
│     ││    0x000011e2      e859feffff     call sym std::basic_ostream<char, std::char_traits<char> >& std::operator<< <std::char_traits<char> >(std::basic_ostream<char, std::char_traits<char> >&, char const*) ; sym.imp.std::basic_ostream_char__std::char_traits_char_____std::operator____std::char_traits_char____std::basic_ostream_char__std::char_traits_char______char_const
│     ││    0x000011e7      4889c2         mov rdx, rax
│     ││    0x000011ea      488b05df2d00.  mov rax, qword [method.std::basic_ostream_char__std::char_traits_char_____std::endl_char__std.char_traits_char____std::basic_ostream_char__std::char_traits_char] ; [0x3fd0:8]=0
│     ││    0x000011f1      4889c6         mov rsi, rax
│     ││    0x000011f4      4889d7         mov rdi, rdx
│     ││    0x000011f7      e854feffff     call sym std::ostream::operator<<(std::ostream& (*)(std::ostream&)) ; sym.imp.std::ostream::operator___std::ostream______std::ostream
│     ││    ; CODE XREFS from main @ 0x11a2, 0x11d2
│     └└──> 0x000011fc      b800000000     mov eax, 0                  ; if.cc:15   return 0;
│           0x00001201      c9             leave                       ; if.cc:16 }
└           0x00001202      c3             ret
```