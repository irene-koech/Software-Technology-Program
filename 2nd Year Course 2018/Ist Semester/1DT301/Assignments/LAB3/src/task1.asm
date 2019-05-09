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
; Function: a program that turns ON and OFFa LED with a push button.
;			 The LED will be extinguished when pressing the button
;
; Input ports: PORTD
;
; Output ports: PORTB.
;
; Subroutines: 
;
; Included files: m2560def.inc
;
; Other information:.org say to the compiler where the address is
;
; Changes in program: 2018-09-28: Implementation
;					
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def dataDir = r16
.def led = r17

.org 0x00
rjmp start

.org INT0addr
rjmp interrupt_0

.org 0x72
start:

;Initialize SP, Stack Pointer
ldi r20, HIGH(RAMEND)			; R20 = high part of RAMEND address
out SPH,R20						; SPH = high part of RAMEND address
ldi R20, low(RAMEND) 			; R20 = low part of RAMEND address
out SPL,R20						; SPL= low part of RAMEND address

;set port B as output
ldi dataDir, 0xFF
out DDRB, dataDir

;set port D as input
ldi dataDir, 0x00
out DDRD, dataDir

;default setting
ldi led, 0xFF


ldi r18, 0b10					; falling edge INT0
sts EICRA, r18

ldi r18, 0b1					; INT0 enable
out EIMSK, r18

sei								; Global interrupt enable
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

main:

nop								; do nothing
rjmp main

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

interrupt_0:

com led							;complement the current LED
out PORTB, led
reti
