    includelib kernel32.lib

ExitProcess proto
    .code
main    proc
        mov RCX,1
        add RCX,100
        mov RDX,10
        sub RCX,RDX
        call ExitProcess
main    endp
        end
