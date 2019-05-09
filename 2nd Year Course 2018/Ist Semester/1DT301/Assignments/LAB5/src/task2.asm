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
; Function: The program generate random number between 1-75. When user press SW2, 
;			the number shows up on display
;
; Input ports: PORT D (SW2)
;
; Output ports: PORT E connected to Display JHD202
;
; Included files: m2560def.inc
;
; Other information:
;
; Changes in program: 2018-10-12: Implementation
;					
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

.include "m2560def.inc"
.def Temp = r16
.def Data = r17
.def RS = r18
.def Value = r19
.def firstDigit = r20
.def secondDigit = r21

.equ	BITMODE4	= 0b00000010		; 4-bit operation
.equ	CLEAR		= 0b00000001		; Clear display
.equ	DISPCTRL	= 0b00001111		; Display on, cursor on, blink on.



.cseg
.org 0x00
    jmp reset

.org int2addr
    jmp int_bingo

.org 0x72

reset:

    ldi Temp, HIGH(RAMEND)	; Temp = high byte of ramend address
	out SPH, Temp			; sph = Temp
	ldi Temp, LOW(RAMEND)	; Temp = low byte of ramend address
	out SPL, Temp			; spl = Temp
    
    ser Temp				; r16 = 0b11111111
	out DDRE, Temp			; port E = outputs ( Display JHD202A)
	clr Temp				; r16 = 0
	out DDRD, Temp	

    rcall init_disp

    ldi Temp, (3<<int2)
    out EIMSK, Temp
    
    ldi Temp, 0b100
    sts EICRA, Temp
        
    sei
    
    rjmp resetNum

ranNumGen:
    cpi Value, 75			
    brge resetNum			; if Value bigger than 75 then reset it
    inc Value				; inc value
    rjmp ranNumGen

resetNum:
    ldi Value, 1			; reset value to 1
    rjmp ranNumGen

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

int_bingo:	
	in temp, SREG
	push temp

    mov secondDigit, Value		; Move Random number to secoundDigit

    ldi firstDigit, 0			; fistDigit should be 0 as defualt 

	display:
	
    cpi secondDigit, 10			; check if second digit is more or equal than 10
    brge inc_firstDigit			; call inc_firstDigit until 2nd-digit is less than 10
	
	rcall clr_disp				; clear display
	
    mov Data, firstDigit		; load 1st digit
    ori Data, 0b0011_0000		; logical OR between data and  0b0011_0000
    rcall write_char			; write number
	rcall long_wait				

    mov Data, secondDigit		
    ori Data, 0b0011_0000		; logical OR between data and  0b0011_0000
    rcall write_char			; write number


	pop temp
	out SREG, temp
    reti

inc_firstDigit:
    subi secondDigit, 10		; subtract 10 from 2nd-digit
    inc firstDigit				; increment 1st-digit by 1 every time we subtract 2nd-digit
    rjmp display	

