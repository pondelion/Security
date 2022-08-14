from pwn import *


context(os='linux', arch='amd64')

elf = ELF('./link_map_got')

print(elf.got)

main_addr = elf.functions['main'].address
print(f"main address : {main_addr:x}")

print(elf.libc.functions['printf'])