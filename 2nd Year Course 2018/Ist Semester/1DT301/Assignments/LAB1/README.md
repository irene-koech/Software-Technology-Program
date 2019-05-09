# 1DT301, Computer Technology I, autumn 2018.
# Lab. 1: How to use the PORTs.Digital input/output. Subroutine call.
## Goal for this lab:
Learn how to write programs in Assembly language, compile them, download to the STK600 and run them on the target CPU and also to use the AVR Studio 4 Simulator.
Development environment: AVR Studio4 or AVR Studio6
The laboratory work has to be done in groups of maximum 2 students per group. Presentation of results:
Present each task for the teacher when you have solved the task. A written report of all assignments should be submitted after each lab, containing the code and a brief description of results. The report must also include flowcharts for all programs.
The report should be sent to the lab teacher within 1 week, thus before next week lab. Use text in the programs (comments) to explain the function. Each program must also have a header like the example below.
Send the lap report to the lab teacher. 

======

* Task 1:
Write a program in Assembly language to light LED 2.
You can use any of the four ports, but start with PORTB.
The program should be very short! How many instructions is minimum number?

* Task 2:
Write a program in Assembly language to read the switches and light the corresponding LED.
Example: When you press SW5, LED5 so should light.
Make an initialization part of the program and after that an infinite loop.

* Task 3:
Write a program in Assembly language to read the switches and light LED0 when you press SW5.
For all other switches there should be no activity.

* Task 4:
Run the program in Task 3 in the simulator.

* Task 5:
Write a program in Assembly language that creates a Ring Counter. The values should be displayed with the LEDs. Use shift instructions, LSL or LSR.ss
Make a delay of approximately 0.5 sec in between each count. Write the delay as a subroutine. For using the subroutine, you must initialize the Stack Pointer, SP. Include the following instructions in beginning of your program:
; Initialize SP, Stack Pointer ldi r20, HIGH(RAMEND) out SPH,R20
ldi R20, low(RAMEND)
out SPL,R20
; R20 = high part of RAMEND address ; SPH = high part of RAMEND address ; R20 = low part of RAMEND address
; SPL = low part of RAMEND address
Function, the 8 LEDs:s
(0000 000X, 0000 00X0, 0000 0X00, 0000 X000, 000X 0000, 00X0 0000, 0X00 0000, X000 0000)

* Task 6:
Write a program in Assembly language that creates a Johnson Counter in an infinite loop.
Function, the 8 LEDs:
(0000 000X, 0000 00XX, 0000 0XXX, 0000 XXXX, 000X XXXX, 00XX XXXX, 0XXX XXXX, XXXX XXXX, 0XXX XXXX, 00XX XXXX, 000X XXXX, 0000 XXXX, 0000 0XXX, 0000 00XX, 0000 000X, 0000 0000)