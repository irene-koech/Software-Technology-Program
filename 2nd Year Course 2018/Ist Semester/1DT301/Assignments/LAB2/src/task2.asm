;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-20
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 2
; Title: Subroutines
;
; Hardware: STK600, CPU ATmega2560
;
; Function: The program lights up "random" nummber of LED from 1-6 when
;			press SW1
;
; Input ports: On-board SW1 connected to PORTA
;
; Output ports: PORTB.
;
; Subroutines: N/A
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-09-20: Implementation
;					  2018-09-21: Edit + comment 
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



.include "m2560def.inc"
.def dataDir = r16
.def switch = r17
.def dice = r18
.def ledOn = r19

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port A as input
ldi dataDir, 0x00
out DDRA, dataDir

;default setting 
ldi ledOn, 0xFF
out PORTB, ledOn

;dice
ldi dice, 0

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

random:

inc dice

in switch, PINA				;load PINA in switch
cpi switch, 0xFD			;compare switch with 0xFD
breq stop					;if SW1 = press -> stop

rjmp random

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Reset dice to 0
reset:
ldi dice, 0
rjmp random

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Stop the "random"

stop:
cpi dice, 1
breq case_1

cpi dice, 2
breq case_2

cpi dice, 3
breq case_3

cpi dice, 4
breq case_4

cpi dice, 5
breq case_5

cpi dice, 6
breq case_6

rjmp reset 			

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;lights the correct LED

case_1:
ldi ledOn, 0b1110_1111
out PORTB, ledOn
rjmp random

case_2:
ldi ledOn, 0b0111_1101
out PORTB, ledOn
rjmp random

case_3:
ldi ledOn, 0b0110_1101
out PORTB, ledOn
rjmp random

case_4:
ldi ledOn, 0b0011_1001
out PORTB, ledOn
rjmp random

case_5:
ldi ledOn, 0b0010_1001
out PORTB, ledOn
rjmp random

case_6:
ldi ledOn, 0b0001_0001
out PORTB, ledOn
rjmp random
