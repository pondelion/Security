.code
GetPeb  proc
        mov rax,gs:[60h]
        ret
GetPeb  endp
end