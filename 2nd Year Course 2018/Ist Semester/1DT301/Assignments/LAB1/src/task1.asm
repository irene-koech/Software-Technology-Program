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
; Function: Lights LED2 on PORTB
;
; Input ports: NONE
;
; Output ports: PORTB.
;
; Subroutines: N/A
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-09-13: Implementation
;		              2018-09-14: Improve the code
;                     2018-09-16: Edit comments
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def dataDir = r16
.def ledOn = r17
	
;set portB to output
ldi dataDir, 0xFF  ;0b1111_1111
out DDRB, dataDir

;light LED2 (0 as on, 1 as off)
ldi dataDir, 0xFB ; 0b1111_10111
out PORTB, dataDir

