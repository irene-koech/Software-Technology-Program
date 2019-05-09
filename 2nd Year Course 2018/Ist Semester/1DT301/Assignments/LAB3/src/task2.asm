;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-28
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 3
; Title: Interrupt
;
; Hardware: STK600, CPU ATmega2560
;
; Function: Program which switch between Ring counter and Johnson counter when the user
;			press SW0
; Input ports: PORTD
;
; Output ports: PORTB.
;
; Subroutines:  delay500ms
;
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-09-28: Implementation
;				
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include "m2560def.inc"

.def dataDir = r16
.def ledRing = r17
.def ledJohnsonOn = r23
.def ledJohnsonOff = r24
.def currentState = r25

.org 0x00
rjmp start

.org INT0addr
rjmp interrupt_0

.org 0x72
start:

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port D as input
ldi dataDir, 0x00
out DDRD, dataDir

ldi r26, 0b01 				; INT0 enable
out EIMSK, r26

ldi r26, 0b11				; falling edge INT0
sts EICRA, r26

sei

;Initialize SP, Stack Pointer
ldi r20, HIGH(RAMEND)			; R20 = high part of RAMEND address
out SPH,R20						; SPH = high part of RAMEND address
ldi R20, low(RAMEND) 			; R20 = low part of RAMEND address
out SPL,R20						; SPL= low part of RAMEND address

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
;Johnson counter

johnson_start:

ldi currentState, 0x00

call delay500ms	

;Lights LED0
ldi ledJohnsonOn, 0b1111_1110
out PORTB, ledJohnsonOn

ldi ledJohnsonOff, 0b0111_1111 ;1 = off 0 = on

forward:

call delay500ms		

lsl ledJohnsonOn 			; shifts last bit to the left
out PORTB, ledJohnsonOn
cpi ledJohnsonOn, 0x00 		 ;compare ledOn with 0xFF
breq backward 				;IF ledOn = 0xFF jump to start

rjmp forward


backward:

call delay500ms	
com ledJohnsonOff
out PORTB, ledJohnsonOff
com ledJohnsonOff
lsr ledJohnsonOff
cpi ledJohnsonOff, 0x00 	 ;compare ledOn with 0x00
breq johnson_start 			;IF ledOff = 0x00 jump to start

rjmp backward

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;RING counter

ring_start:

ldi currentState, 0xFF

;Lights LED0
ldi ledRing, 0b1111_1110
out PORTB, ledRing

ring_loop:

call delay500ms

lsl ledRing				; shifts last bit to the left
inc ledRing				; add 1 to the current bit
out PORTB, ledRing

cpi ledRing, 0xFF  		;compare ledOn with 0xFF
breq ring_start 		 ;IF ledOn = 0xFF jump to start

rjmp ring_loop

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

delay500ms:

; Delay 500 000 cycles
; 500ms at 1 MHz


    ldi  r18, 3
    ldi  r19, 138
    ldi  r27, 86
L1: 
    dec  r27
    brne L1
    dec  r19
    brne L1
    dec  r18
    brne L1
    rjmp PC+1

ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

interrupt_0:
sei
cpi currentState, 0x00
breq ring_start
brne johnson_start


