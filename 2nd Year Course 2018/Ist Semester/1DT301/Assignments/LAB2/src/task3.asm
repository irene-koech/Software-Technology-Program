;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-21
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 2
; Title: Subroutines
;
; Hardware: STK600, CPU ATmega2560
;
; Function: LEDs should light up in binary form while incresing
;			1 bit everytime we press and release SW1. 
;
; Input ports: On-board SW1 connected to PORTA
;
; Output ports: PORTB.
;
; Subroutines: pressOn/Off
;			   increment
;			   waitPress/Release
;			   lightsOn
;			   
; Included files: m2560def.inc
;
; Other information: board is broken so we tried with SW1 instead of SW0
;
; Changes in program: 2018-09-21: Implementation
;					  
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include "m2560def.inc"
.def dataDir = r16
.def counter = r17
.def switchOn = r18
.def switchOff = r19
.def check = r20

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port A as input
ldi dataDir, 0x00
out DDRA, dataDir

ldi counter, 0
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

main:
	call waitPress
	call PressOn

	call waitRelease
	call pressOff
rjmp main

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


pressOn:
rcall increment
rcall lightsOn

ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

pressOff:
rcall increment
rcall lightsOn

ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

increment:
inc counter							;increse counter by 1
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

lightsOn:
com counter							;One’s Complement= 1->0, 0->1
out PORTB, counter					;lights LEDs
com counter							;back to "normal" state
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

waitPress:
sbis PINA,PINA1 					;skip next line when user dont press sw1 
ret
	
rjmp waitPress

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

waitRelease:
sbic PINA, PINA1					;skip next line when user releases sw1 
ret
	
rjmp waitRelease
