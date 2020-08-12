    includelib  kernel32.lib
GetStdHandle    proto
WriteConsoleA   proto
Console         equ -11
ExitProcess     proto

.code
main    proc

        sub     RSP,40

        mov     RCX,Console
        call    GetStdHandle
        mov     stdout,RAX

        mov     RCX,stdout
        lea     RDX,msg
        mov     R8,lengthof msg
        lea     R9,nbwr
        call    WriteConsoleA

        add     RSP,40
        mov     RCX,0
        call    ExitProcess

main    endp

.data
msg     byte    "Hello World"
stdout  qword   ?
nbwr    qword   ?

end