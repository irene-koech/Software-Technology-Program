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
; Function: Lights up LEDX when you press SWX. For instance, SW2 -> Lights LED2
;
; Input ports: On-board Switches are connected to PORTA
;
; Output ports: PORTB.
;
; Subroutines: N/A
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-09-13: Implementation
;		              2018-09-14: Add loop
;                     2018-09-16: Edit comments
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def dataDir = r16
.def switch = r17

; set portB as output
ldi dataDir, 0xFF 
out DDRB, dataDir

;set portA as input
ldi dataDir, 0x00	
out DDRA, dataDir

loop:

in switch, PINA			;Read the content of PINA (switches) and safe it in "switch" (r17)
out PORTB, switch 		;Lights LEDX

rjmp loop
