includelib      kernel32.lib
GetStdHandle    proto
WriteConsoleA   proto
ReadConsoleA    proto
Console         equ     -11
Keyboard        equ     -10
ExitProcess     proto

.code
main    proc
        sub     RSP,40
        mov     RCX,Console
        call    GetStdHandle
        mov     stdout,RAX
        mov     RCX,Keyboard
        call    GetStdHandle
        mov     stdin,RAX

        mov     RCX,stdout
        lea     RDX,pmsg
        mov     R8,lengthof pmsg
        lea     R9,nbwr
        call    WriteConsoleA

        mov     RCX,stdin
        mov     R8,20
        lea     RDX,keymsg
        lea     R9,nbrd
        call    ReadConsoleA

        mov     RCX,stdout
        lea     RDX,keymsg
        mov     R8,nbrd
        lea     R9,nbwr
        call    WriteConsoleA

        add     RSP,40
        mov     RCX,0
        call    ExitProcess

main    endp

.data
pmsg    byte    "Pelase enter text message:"
keymsg  byte    20 DUP (?)
stdout  qword   ?
nbwr    qword   ?
stdin   qword   ?
nbrd    qword   ?

end