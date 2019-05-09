;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
; 1DT301, Computer Technology I
; date: 2018-10-18
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
;			The program should receive 4 lines of characters that are sent from the computer, 
;  			and show the code on Display JHD202. Each text line should be displayed during 5 seconds,
;			after that the text on line 1 should be moved to line 2 and so on.
;
; Input ports: RS232, PORTD(sw1)
;
; Output ports: PORT E connected to Display JHD202
;
; Subroutines: 
;
; Included files: m2560def.inc
;				  defualt.inc (defualt setting for display JHD202)
;
; Other information:	
;
; Changes in program: 2018-10-18: Implementation
;					  2018-10-19: finished implementation and commnent the code
;					
;
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
.include "m2560def.inc"

.def temp = r16
.def data = r17
.def rs = r18 			; RS = register select
.def flag = r19			; flag = 0 -> write mode, flag = 1 -> display mode
.def counter = r20		; Counter

.equ BITMODE4= 0b00000010			; 4-bit operation
.equ DISPCTRL	= 0b00001111		; Display on, cursor on, blink on.

.cseg
.org 0x00
jmp reset

.org 0x04 				; address for external interrupt 1
jmp switch

.org URXC1addr			; URXC1addr - receive complete for USART1
jmp load_store

.org 0x72

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

reset:

; Initialize stack pointer
ldi temp, low(RAMEND)
out SPL, temp
ldi temp, high(RAMEND)
out SPH, temp

; Initialize X pointer
ldi xl, low(0x200)
ldi xh, high(0x200)

; Initialize Y pointer
ldi yl, low(0x200)
ldi yh, high(0x200)

; Initialize Z pointer
ldi zl, low(0x300)
ldi zh, high(0x300)

ser temp

; Enable INT1, set as rising edge
ldi temp, 0b0000_0010
out EIMSK, temp
ldi temp, 0b0000_1100
sts EICRA, temp

; Set Baud rate
ldi temp, 12
sts UBRR1L, temp

; Enable receive flag and receive interrupt for URAT0
ldi temp, (1<<RXEN1 | 1<<RXCIE1)
sts UCSR1B, temp

sei ; Enable interrupts

call init_disp ; Initialize display

clr temp
clr counter

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

main: 
	sbrs flag, 0 ; WRITE mode
	rjmp main	

	push counter
	clr counter

	L3:
	; Print first line
	call clr_disp
	rcall restore_x	; reset or move X pointer to last saved Y pointer
	call print_line

	; Print new line
	ldi data, 0xA8
	call write_cmd
	call short_wait
	
	; Increment or reset counter
	inc counter
	cpi counter, 4
	brlo C1
		clr counter
	C1:

	; Print second line
	rcall restore_x
	call print_line

	; Delay, currently set to 2,5 seconds
	ldi r24, low(2500)
	ldi r25, high(2500)
	call delay
		
	; Exit the loop if we've switched to WRITE mode
	sbrs flag, 0
	rjmp L3

	pop counter
rjmp main

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;Switches between READ and WRITE mode
switch:
	com flag
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

;
; Load single character from serial input, save it in memory
; and puts it on display
;
load_store:
	; Don't do anything if we are not in WRITE mode
	sbrc flag, 0
	reti

	push temp

	lds temp, UDR1			; Get input from data register
	st y+, temp				; Store input in memory
	
	; Store location of Y in table
	rcall restore_z 		; Get location to the Z pointer
	st z+, yl
	st z+, yh
	; Check if input is \n (Character no.13 in ASCII)
	; in another word, user press ENTER on keyboard 
	cpi temp, 0b0000_1101	
	brne not_nextline
		; If \n, inc counter,
		inc counter
	not_nextline:

	call clr_disp
	rcall restore_x ; Reset x pointer to start or last saved location of Y pointer
	rcall print_line

	pop temp
reti

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; Restore the Z pointer to start of table, and offset depending on which
; line is active
;
restore_z:
	; load default setting to Z
	ldi zl, low(0x300)
	ldi zh, high(0x300)

	; Move forward 2 bytes for every N-1 lines
	push temp
	cpi counter, 0
	breq E1
	mov temp, counter
	L1:	; while(temp > 0)
		adiw z, 2
		dec temp
		brne L1
	E1:
	pop temp
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; Restore the X pointer to start of memory, or last saved position of Y pointer
;
restore_x:
	cpi counter, 0
	breq default
	
	; We are not at the first line, start off
	; from the last saved Y pointer
	rcall restore_z
	ld xh, -z
	ld xl, -z
	adiw z, 2
	ret
	
	default:			; move X pointer to start of memory
	ldi xl, low(0x200)
	ldi xh, high(0x200)
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
;	Print selected line to display
;
print_line:
	; Checks to prevent out off of memory boundary errors
	cp xh, yh
	brne print
	cp xl, yl
	brge E0

	; Load single character to data register
	print:
	ld data, x+
	cpi data, 0b0000_1101 ; Stop when the loaded character is a '\n' (enter)
	breq E0

	; Display the character
	call write_char
	rjmp print_line		; Continue until X == Y or X is new line

	E0:
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
; 
; Purpose: Initializes the display
;
init_disp:
	ser temp
	out DDRE, temp
	clr temp
	out PORTE, temp

	ldi r24, low(20)
	ldi r25, high(20)
	call delay	; power up wait

	ldi data, BITMODE4		; 4-bit operation
	call write_nibble		; (in 8-bit mode)
	call short_wait			; wait ~40 us

	; Enable display, blinking on, and cursor on
	ldi data, DISPCTRL
	call write_cmd			; send command
	call short_wait			; wait ~40 us
	
	call clr_disp			; clr display
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; clear screen
;
clr_disp:
	ldi data, 0b00_0001
	call write_cmd

	ldi r24, low(20)
	ldi r25, high(20)
	call delay
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; Send output to the display as data
;
write_char:
	ldi rs, 0b0010_0000			; RS = high
	jmp write

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; Send output to the display as command
;
write_cmd:
	ldi rs, 0b0000_0000

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; writing 

write:
	push temp

	mov temp, data				; copy data	
	andi data, 0b1111_0000		; mask out high nibble
	swap data					; mask out high nibble
	or data, rs				; add register select
	call write_nibble			; send high nibble
	mov data, temp				; restore Data
	andi data, 0b0000_1111 		; mask out low nibble
	or data, rs				; add register select
	call write_nibble

	pop temp
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
;Transmit data to the JHD202 display and then update it

write_nibble:
	call switch_output		; Modify for display JHD202A, port E
	nop						; wait 542nS
	sbi PORTE, 5			; enable high, JHD202A
	nop
	nop						; wait 542nS
	cbi PORTE, 5			; enable low, JHD202A
	nop
	nop						; wait 542nS
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
;  modify output signal to fit LCD JHD202A, connected to port E

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
;
; delay
; Parameters: integer (r25:r24)
; waitmilliseconds from LAB2 TASK 4

delay:
	push r16		
	push r17

	; These instructions take approx 1 ms to complete on ATMEGA2560
	_L0:
	ldi r16, low(500)
	ldi r17, high(500)

	_L1:
	dec r16
	nop
	brne _L1				
	dec r17
	nop
	brne _L1

	;repeat N times to gain N ms delay 
	sbiw r25:r24, 1			
	brne _L0			

	pop r16				
	pop r17
ret

;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
; ~40 us delay
;
short_wait:
	push zl
	push zh

	clr zh
	ldi zl, 30
	
	wait_loop:
	sbiw z, 1
	brne wait_loop
	
	pop zh
	pop zl
ret
