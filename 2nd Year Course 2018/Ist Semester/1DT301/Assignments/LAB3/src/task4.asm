;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-10-01
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 3
; Title: Interrupt
;
; Hardware: STK600, CPU ATmega2560
;
; Function: Rear lights on a car with light for brakes
;
; Input ports: PORTD
;
; Output ports: PORTB.
;
; Subroutines: delay500ms
;
; Included files: m2560def.inc
;
; Other information: WE ARE USING INT0 FOR BRAKE INSTEAD OF INT2!!
;					 SW0 for brake
;					 SW1 for turning right
;					 SW2 for turning left
;					 SW3 for normal light
;					 Turning right and brake, press SW1 then SW0
;					 Turning left and brake, press SW2 then SW0
;					 Normal brake, press SW3 then SW0

;Special case: When you use normal brake and want to turn left or right.
;			   You might need to press SW1 or SW2 2 times.
:
;					
;
; Changes in program: 2018-10-01: Implementation
;					  
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def dataDir = r16
.def led = r17
.def flag = r30					;check state
.org 0x00
rjmp start

.org INT0addr
rjmp interrupt_brake

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

;default setting 
ldi led, 0b0011_1100
out PORTB, led				; Normal light
ldi flag, 0					; Set flag to normal light state
	

ldi r18, 0b10101010			; Falling edge INT0,INT1, INT2 and INT3
sts EICRA, r18

ldi r18, 0b1111				; INT0,INT1,INT2 and INT3 enable
out EIMSK, r18

sei							; Global interrupt enable

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

interrupt_normal:
sei
rcall start
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Turning right. 3-2-1-0ring counter
interrupt_right:
sei
right:
ldi flag, 1 				; set flag to turning right state

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
ldi flag, 2 				;set flag to turning left state

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


interrupt_brake:
sei

cpi flag, 0
breq brake_normal

cpi flag, 1
breq brake_right

cpi flag, 2
breq brake_left

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

brake_normal:
sei
ldi led,0x00
out PORTB, led
rcall brake_normal
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;LED 4-7 on, LED 0-3 blinking as RING counter
brake_right:
sei
bRight:
ldi led, 0b0000_0111
out PORTB, led
call delay500ms

ldi led, 0b0000_1011
out PORTB, led
call delay500ms

ldi led, 0b0000_1101
out PORTB, led
call delay500ms

ldi led, 0b0000_1110
out PORTB, led
call delay500ms

rcall bRight
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;LED 0-3 on, LED 4-7 blinking as RING counter
brake_left:
sei
bLeft:
ldi led, 0b1110_0000
out PORTB, led
call delay500ms

ldi led, 0b1101_0000
out PORTB, led
call delay500ms

ldi led, 0b1011_0000
out PORTB, led
call delay500ms

ldi led, 0b0111_0000
out PORTB, led
call delay500ms

rcall bLeft
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
