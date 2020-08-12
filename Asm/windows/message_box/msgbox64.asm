extern	MessageBoxA : proc
extern	ExitProcess : proc

public	Start

.code
Start:
	sub rsp, 8h
	
	mov rcx, 0
	lea rdx, strMsg
	lea r8, strCapt
	mov r9, 40h
	call MessageBoxA

	mov ecx, 0
	call ExitProcess

.data
strCapt	db 'msgbx64', 0
strMsg	db 'Hello World !', 0

end