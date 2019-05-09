;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-09-26
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 2
; Title: Subroutines
;
; Hardware: STK600, CPU ATmega2560
;
; Function: general delay routine that can be called from other 
; 			programs
;
; Input ports:N/A
;
; Output ports: PORTB
;
; Subroutines: wait_milliseconds
;			   
; Included files: m2560def.inc
;
; Other information: 
;
; Changes in program: 2018-09-26: Implementation
;					  
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include "m2560def.inc"
.def dataDir = r16
.def ledOn = r17
.def highTime = r25
.def lowTime = r24
.equ time = 5		;<<<<<<<< edit the time here (in ms)

; Initialize SP, Stack Pointer
ldi r20, HIGH(RAMEND)               ; R20 = high part of RAMEND address
out SPH,R20                         ; SPH = high part of RAMEND address
ldi R20, low(RAMEND)                ; R20 = low part of RAMEND address
out SPL,R20                         ; SPL = low part of RAMEND address

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

start:
;Lights LED0
ldi ledOn, 0b1111_1110 
out PORTB, ledOn

loop:

ldi highTime, HIGH(time)
ldi lowTime, LOW(time)

lsl ledOn 						; shifts last bit to the left
inc ledOn						; add 1 to the current bit
rcall wait_milliseconds
out PORTB, ledOn

cpi ledOn , 0xFF				;compare ledOn with 0xFF
breq start 		 				;IF ledOn = 0xFF jump to start

rjmp loop

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

wait_milliseconds:
;store data in r16 and r17 to the stack
push r21
push r22

;Delay for 1ms at 1 MHz (250us at 4 MHz)
L0:
ldi r21,2
ldi r22, 75

L1:
dec r22
brne L1
dec r21
brne L1
;repeat N times to gain N ms delay 
sbiw highTime:lowTime ,1
brne L0

;return stored data from stack
pop r22
pop r21

ret


	
