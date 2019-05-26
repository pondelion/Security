bits 32
global _start

section .text

_start:
    jmp MAIN

HELLOWORLD:
    mov eax, 0x4
    mov ebx, 0x1
    pop ecx
    mov edx, 0xF
    int 0x80
    mov eax, 0x1
    mov ebx, 0x0
    int 0x80

MAIN:
    call HELLOWORLD
    db "Hello, World!", 0dh, 0ah

section .data