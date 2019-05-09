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
; Function: Program that simulates the rear lights on a car. 
;			The 8 LEDs should behave like the rear lights
;
; Input ports: PORTD
;
; Output ports: PORTB.
;
; Subroutines: delay500ms
;
; Included files: m2560def.inc
;
; Other information: SW1 for turning right
;					 SW2 for turning left
;					 SW3 for normal light
;
; Changes in program: 2018-09-28: Implementation
;					  2018-10-01: commented
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def dataDir = r16
.def led = r17

.org 0x00
rjmp start

.org INT1addr
rjmp interrupt_right

.org INT2addr
rjmp interrupt_left

.org INT3addr
rjmp interrupt_normal

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

;default setting (Normal light)
ldi led, 0b0011_1100
out PORTB, led


ldi r18, 0b10101000			; falling edge INT1,INT2 and INT3
sts EICRA, r18

ldi r18, 0b1110				; INT1,INT2 and INT3 enable
out EIMSK, r18

sei							; Global interrupt enable

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

interrupt_normal:
sei
call start					;restart the program	

reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Turning right. 3-2-1-0ring counter
interrupt_right:
sei
right:
ldi led, 0b0011_0111
out PORTB, led
call delay500ms

ldi led, 0b0011_1011
out PORTB, led
call delay500ms

ldi led, 0b0011_1101
out PORTB, led
call delay500ms

ldi led, 0b0011_1110
out PORTB, led
call delay500ms

rcall right
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Turning left. 4-5-6-7ring counter
interrupt_left:
sei
left:

ldi led, 0b1110_1100
out PORTB, led
call delay500ms

ldi led, 0b1101_1100
out PORTB, led
call delay500ms

ldi led, 0b1011_1100
out PORTB, led
call delay500ms

ldi led, 0b0111_1100
out PORTB, led
call delay500ms

rcall left
reti

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
    dec  r19
    brne L1
    dec  r18
    brne L1
    rjmp PC+1

ret
