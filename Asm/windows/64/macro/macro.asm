includelib      kernel32.lib
GetStdHandle    proto
WriteConsoleA   proto
ReadConsoleA    proto
Console     equ -11
Keyboard    equ -10
MaxBuf      equ 20
ExitProcess     proto

msgOut  macro   msg
        mov     RCX,stdout
        lea     RDX,msg
        mov     R8,lengthof msg
        lea     R9,nbwr
        call    WriteConsoleA
        endm

.code
main    proc
        sub     RSP,40

        mov     RCX,Console
        call    GetStdHandle
        mov     stdout,RAX
        mov     RCX,Keyborad
        call    GetStdHandle
        mov     stdin,RAX

nx