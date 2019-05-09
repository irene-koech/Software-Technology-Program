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
;			which change between On and Off in theinterrupt subroutine
;
; Input ports: N/A
;
; Output ports: PORTB.
;
; Subroutines: ledOut, increase and decrease
;
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-10-04: Implementation
;					  2018-10-07: Finished
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"

.def temp = r16
.def led = r17 
.def complement = r18    
.def counter = r19      ; Keeps cycle
.def check = r20

.equ MAX = 20

.CSEG

; Initialize starting point for program
.ORG 0x00
rjmp start

; Initialize timer overflow interrupt vector
.ORG OVF0addr
rjmp timer_int

; Initialize SW1 interrupt vector
.ORG INT1ADDR
rjmp dec_int

; Initialize SW2 interrupt vector
.ORG INT2ADDR
rjmp inc_int

.ORG 0x72

start:
;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;Initialize Stack Pointer

ldi temp, LOW(RAMEND)
out SPL, temp
ldi temp, HIGH(RAMEND)
out SPH, temp

;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; Initialize ports
;PortB, Pin0 = output
ldi temp, 0x01
out DDRB, temp

;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; Initialize timer
; Set prescale to 1024
ldi temp, 0x05
out TCCR0B, temp

; Enable interrupt on timer overflow
ldi temp, (1<<TOIE0)
sts TIMSK0, temp

; Set default value for timer
ldi temp, 206
out TCNT0, temp


;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; Enable interrupts for SW1 and SW2
ldi temp, 0b110
out EIMSK, temp

; Trigger interrupts on falling edge (switch released)
ldi temp, 0b101000
sts EICRA, temp

sei
clr led
ldi check, 10


main:
	rcall ledOut
rjmp main

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
ledOut:
	mov complement, led
	com complement
	out PORTB, complement
ret
	
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
timer_int:
	; Save Status Register on stack
	in temp, SREG
	push temp
	
	; Reset starting value for timer
	ldi temp, 206	
	out TCNT0, temp

	cpi counter,MAX
	brlo compare

	ldi counter, 0	

	compare:
	cp counter, check      ; if counter < dutyCycle
	brlo ledOn             ; then turn LED on
   	ldi led, 0x00           ; else turn LED off
	rjmp end

    ledOn:
        ldi led, 0x01
        
	end:
	    inc counter

	pop temp
	out SREG, temp
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;decrease the dutyCycle
dec_int:
sei	
	cpi check, 0			; if check > 0 call decrease
	brge decrease
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

decrease:
	dec check
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;increase the dutyCycle
inc_int:				
sei	
	cpi check, MAX			
	brlt increase			; if check < Max call increase
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
increase:
	inc check
ret
