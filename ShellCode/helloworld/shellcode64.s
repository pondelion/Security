bits 64
global _start

section .text

_start:
    jmp MAIN

HELLOWORLD:
    mov rax, 0x1
    mov rdi, 0x1
    pop rsi
    mov rdx, 0xF
    syscall
    mov rax, 60
    mov rdi, 0
    syscall

MAIN:
    call HELLOWORLD
    db "Hello, World!", 0dh, 0ah

section .data