.model	flat, stdcall

extern	MessageBoxA@16 : proc
extern	ExitProcess@4 : proc

public Start

.code
Start:
	push 40h
	push offset strCapt
	push offset strMsg
	push 0
	call MessageBoxA@16

	push eax
	call ExitProcess@4

.data
strCapt	db 'msgbx', 0
strMsg	db 'Hello World !', 0

end