includelib  kernel32.lib
GetStdHandle    proto
WriteConsoleA   proto
ReadConsoleA    proto
Console     equ -11     ; 標準出力デバイスコード
Keyboard    equ -10     ; 標準入力デバイスコード
MaxBuf      equ 20
ExitProcess     proto

.code
main    proc
        sub     RSP,40          ; スタックにメモリ領域確保
        mov     RCX,Console
        call    GetStdHandle    ; 標準出力ハンドル取得
        mov     stdout,RAX
        mov     RCX,Keyboard
        call    GetStdHandle    ; 標準入力ハンドル取得
        mov     stdin,RAX
;               入力促しメッセージ出力
nxtlin: mov     RCX,stdout
        lea     RDX,msg
        mov     R8,lengthof msg
        lea     R9,nbwr
        call    WriteConsoleA
;               メッセージ入力
        mov     RCX,stdin
        mov     R8,MaxBuf
        lea     RDX,keymsg
        lea     R9,nbrd
        call    ReadConsoleA
;               入力メッセージをそのまま出力
        mov     RCX,stdout
        lea     RDX,keymsg
        mov     R8,nbrd
        lea     R9,nbwr
        call    WriteConsoleA

        mov     R8,nbrd     ; 入力された文字数
        cmp     R8,2        ; CR+LF(入力なし)以上=何か入力されたら
        jg      nxtlin      ; nxtlinへ
        add     RSP,40      ; 確保したスタックメモリ解放
        mov     RCX,0
        call    ExitProcess

main    endp

.data
msg     byte    "Please exnter text message."
keymsg  byte    MaxBuf DUP (?)
stdout  qword   ?
nbwr    qword   ?
stdin   qword   ?
nbrd    qword   ?

        end