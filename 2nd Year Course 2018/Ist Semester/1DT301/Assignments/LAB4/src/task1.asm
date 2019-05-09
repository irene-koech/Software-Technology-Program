;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-10-04
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 4
; Title: Timer and UART
;
; Hardware: STK600, CPU ATmega2560
;
; Function: a timer function create an interrupt with 2 Hz,
;			which change between On and Off in the interrupt subroutine
;
; Input ports: N/A
;
; Output ports: PORTB.
;
; Subroutines: 
;
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-10-04: Implementation
;					
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def led = r17
.def temp = r18
.def counter = r19

.cseg							; Assembly directive Code Segment
.org 0x00						; place this code in PM adress 0x00
rjmp start						; jump to label start

.org OVF0addr
rjmp timer_int

.org 0x72
start:

;Initialize SP, Stack Pointer
ldi r20, HIGH(RAMEND)			; R20 = high part of RAMEND address
out SPH,R20						; SPH = high part of RAMEND address
ldi R20, low(RAMEND) 			; R20 = low part of RAMEND address
out SPL,R20						; SPL= low part of RAMEND address

;set port B as output
ldi temp, 0x01
out DDRB, temp

ldi temp, 0b101					; perscaler value to TCCR0/1024
out TCCR0B, temp				; CS02-CS00 = 101, osc.clock/1024

ldi temp, (1<<TOIE0)			; Timer 0 enable flag, TOIE0
sts TIMSK0, temp				; to register TIMSK0

ldi temp, 245					; starting value for counter
out TCNT0, temp 				; couter register

ldi	counter, 0
sei

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

loop:
out PORTB, led					; light LED0
rjmp loop

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

timer_int:

in temp, SREG					; save SREG on stack
push temp						; timer interrupt routing
	
ldi temp, 245					; set time couter back to 245
out TCNT0, temp 				

inc counter						; increment counter

cpi counter, 34					; if counter = 34 -> 0.5sec passed (50%duty cy)
breq change_led		

pop temp						; restore SREG
out SREG, temp

reti							; return from interrupt

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;change LED0 state
change_led:			
com led
clr counter 
