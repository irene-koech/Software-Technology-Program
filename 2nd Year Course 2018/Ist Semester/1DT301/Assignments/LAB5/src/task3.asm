;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; Date: 2018-10-12
; Author:
; Amata Anantaprayoon (aa224iu)
; Adell Tatrous (at222ux)
;
; Lab number: 5
; Title: Display JHD202
;
; Hardware: STK600, CPU ATmega2560, Display JHD202
;
; Function: A program uses the serial communication port0(RS232).
;			The program should receive characters that are sent from the computer, 
;  			and show the code on Display JHD202.
;
; Input ports: RS232
;
; Output ports: PORT E connected to Display JHD202
;
; Subroutines: 
;
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-10-12: Implementation
;					
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include 	"m2560def.inc"
.def	Temp	= r16
.def	Data	= r17
.def	RS		= r18

.equ 	UBRR_val = 12					; osc. = 1MHz, 4800 bps
.equ	BITMODE4	= 0b00000010		; 4-bit operation
.equ	CLEAR	= 0b00000001			; Clear display
.equ	DISPCTRL	= 0b00001111		; Display on, cursor on, blink on.

.cseg
.org	0x0000				; Reset vector
	jmp reset

.org URXC1addr
    jmp getChar

.org	0x0072

reset:	
	; Initialize stack pointer
	ldi Temp, HIGH(RAMEND)	; Temp = high byte of ramend address
	out SPH, Temp			; sph = Temp
	ldi Temp, LOW(RAMEND)	; Temp = low byte of ramend address
	out SPL, Temp			; spl = Temp
	
	;display
	ser Temp				; r16 = 0b11111111
	out DDRE, Temp			; port E = outputs ( Display JHD202A)
	clr Temp				; r16 = 0
	out PORTE, Temp	

	
	rcall init_disp
	
	; Initialize Serial Communication
	ldi temp, UBRR_val				; store PErscaler value in UBRR1L
	sts UBRR1L, temp
	ldi temp,(1<<RXEN1) | (1<<RXCIE1)
	sts UCSR1B, temp				; set RX and interrupt enable flags	

	rcall clr_disp

sei
loop:	nop
	rjmp loop			; loop forever


;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; **
; ** init_display
; **
init_disp:	
	rcall power_up_wait		; wait for display to power up

	ldi Data, BITMODE4		; 4-bit operation
	rcall write_nibble		; (in 8-bit mode)
	rcall short_wait		; wait min. 39 us
	ldi Data, DISPCTRL		; disp. on, blink on, curs. On
	rcall write_cmd			; send command
	rcall short_wait		; wait min. 39 us
clr_disp:	
	ldi Data, CLEAR			; clr display
	rcall write_cmd			; send command
	rcall long_wait			; wait min. 1.53 ms
	ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; **
; ** write char/command
; **

write_char:		
	ldi RS, 0b00100000		; RS = high
	rjmp write
write_cmd: 	
	clr RS					; RS = low
write:	
	mov Temp, Data			; copy Data
	andi Data, 0b11110000	; mask out high nibble
	swap Data				; swap nibbles
	or Data, RS				; add register select
	rcall write_nibble		; send high nibble
	mov Data, Temp			; restore Data
	andi Data, 0b00001111	; mask out low nibble
	or Data, RS				; add register select

write_nibble:
	rcall switch_output		; Modify for display JHD202A, port E
	nop						; wait 542nS
	sbi PORTE, 5			; enable high, JHD202A
	nop
	nop						; wait 542nS
	cbi PORTE, 5			; enable low, JHD202A
	nop
	nop						; wait 542nS
	ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; **
; ** busy_wait loop
; **
short_wait:	
	clr zh					; approx 50 us
	ldi zl, 30
	rjmp wait_loop
long_wait:	
	ldi zh, HIGH(1000)		; approx 2 ms
	ldi zl, LOW(1000)
	rjmp wait_loop
dbnc_wait:	
	ldi zh, HIGH(4600)		; approx 10 ms
	ldi zl, LOW(4600)
	rjmp wait_loop
power_up_wait:
	ldi zh, HIGH(9000)		; approx 20 ms
	ldi zl, LOW(9000)

wait_loop:	
	sbiw z, 1				; 2 cycles
	brne wait_loop			; 2 cycles
	ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; **
; ** modify output signal to fit LCD JHD202A, connected to port E
; **

switch_output:
	push Temp
	clr Temp
	sbrc Data, 0				; D4 = 1?
	ori Temp, 0b00000100		; Set pin 2 
	sbrc Data, 1				; D5 = 1?
	ori Temp, 0b00001000		; Set pin 3 
	sbrc Data, 2				; D6 = 1?
	ori Temp, 0b00000001		; Set pin 0 
	sbrc Data, 3				; D7 = 1?
	ori Temp, 0b00000010		; Set pin 1 
	sbrc Data, 4				; E = 1?
	ori Temp, 0b00100000		; Set pin 5 
	sbrc Data, 5				; RS = 1?
	ori Temp, 0b10000000		; Set pin 7 (wrong in previous version)
	out porte, Temp
	pop Temp
	ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; **
; ** interrupt
; **

getChar:
	lds data, UDR1				; load character to data
	rcall write_char			; write character
reti
