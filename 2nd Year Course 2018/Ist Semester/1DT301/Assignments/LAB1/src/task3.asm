;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-13
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 1
; Title: How to use the PORTs. Digital input/output. Subroutine call.
;
; Hardware: STK600, CPU ATmega2560
;
; Function: Lights up LED0 when yoy press SW5
; Input ports: On-board Switches are connected to PORTA
;
; Output ports: PORTB.
;
; Subroutines: N/A
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-09-14: Implementation
;                     2018-09-16: Edit comments
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



.include "m2560def.inc"
.def dataDir = r16
.def ledZero = r17  ; LED0
.def default = r18  

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port A as input
ldi dataDir, 0x00
out DDRA, dataDir


ldi default, 0xFF			    ;turn off all LED
ldi ledZero, 0b1111_1110 	    ;light only LED0

loop:
sbis PINA, PINA5 	            ; skip next line (Line 49) when user dont press SW5 
out PORTB, ledZero	            ; turn on LED0
out PORTB, default 	            ; default setting (LEDX = off)
rjmp loop




