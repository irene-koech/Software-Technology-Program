;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-14
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 1
; Title: How to use the PORTs. Digital input/output. Subroutine call.
;
; Hardware: STK600, CPU ATmega2560
;
; Function: Program which switch between Ring counter and Johnson counter when the user
;			press SW0
; Input ports: PORTA
;
; Output ports: PORTB.
;
; Subroutines:  delay500ms
;				switch
;				setFlag
;
; Included files: m2560def.inc
;
; Other information: We using SW1 instead of SW0
;
; Changes in program: 2018-09-20: Implementation
;					  2018-09-26: Comment the code + debug
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include "m2560def.inc"
.def dataDir = r16
.def ledRing = r17
.def ledJohnsonOn = r21
.def ledJohnsonOff = r22
.def compare = r24
.def flag = r30


;Initialize SP, Stack Pointer
ldi r20, HIGH(RAMEND)			; R20 = high part of RAMEND address
out SPH,R20						; SPH = high part of RAMEND address
ldi R20, low(RAMEND) 			; R20 = low part of RAMEND address
out SPL,R20						; SPL= low part of RAMEND address

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port A as input
ldi dataDir, 0x00
out DDRA, dataDir
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;RING counter
ring_start:
ldi flag, 0						; set flag to 0 (defualt)
ldi compare, 0xFF				; set compare to 0xFF

;Lights LED0
ldi ledRing, 0b1111_1110
out PORTB, ledRing

ring_loop:

call delay500ms
cpi flag, 1						; check if flag is On (flag on = 1)
breq change						; if flag = 1 -> jump to change

lsl ledRing		; shifts last bit to the left
inc ledRing		; add 1 to the current bit
out PORTB, ledRing

cpi ledRing, 0xFF  ;compare ledOn with 0xFF
breq ring_start 		 ;IF ledOn = 0xFF jump to start

rjmp ring_loop

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Johnson counter

johnson_start:
ldi flag,0				; set flag to 0 (defualt)
ldi compare , 0x00		; set compare to 0x00

call delay500ms		
cpi flag, 1				; check if flag is On (flag on = 1)
breq change				; if flag = 1 -> jump to change

;Lights LED0
ldi ledJohnsonOn, 0b1111_1110
out PORTB, ledJohnsonOn

ldi ledJohnsonOff, 0b0111_1111

forward:

call delay500ms		
cpi flag, 1				; check if flag is On (flag on = 1)
breq change				; if flag = 1 -> jump to change

lsl ledJohnsonOn 		; shifts last bit to the left
out PORTB, ledJohnsonOn
cpi ledJohnsonOn, 0x00  ;compare ledOn with 0xFF
breq backward 	;IF ledOn = 0xFF jump to start

rjmp forward

backward:

call delay500ms		
cpi flag, 1				; check if flag is On (flag on = 1)
breq change				; if flag = 1 -> jump to change


out PORTB, ledJohnsonOff
lsr ledJohnsonOff
cpi ledJohnsonOff, 0x00  ;compare ledOn with 0x00
breq johnson_start 	;IF ledOff = 0x00 jump to start

rjmp backward

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

delay500ms:

; Delay 500 000 cycles
; 500ms at 1 MHz


    ldi  r18, 3
    ldi  r19, 138
    ldi  r20, 86
L1: 
    dec  r20
    brne L1
	call switch
    dec  r19
    brne L1
	call switch
    dec  r18
    brne L1
	call switch
    rjmp PC+1

ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

switch:

in r23, PINA			;load PINA to r23
cpi r23, 0xFE			;check if r23 is equal to 0xFE
breq setFlag			;if true -> call setFlag

ret
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

setFlag:
ldi r30, 1				;set flag on
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

change:
cpi compare, 0xFF		;check compare with 0xFF
;if ture means current state is on ring -> call Johnson
breq johnson_start
;else call ring		
call ring_start
