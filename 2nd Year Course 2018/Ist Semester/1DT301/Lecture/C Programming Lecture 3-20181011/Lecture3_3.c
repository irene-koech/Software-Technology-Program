/*
 ============================================================================
 Name        : Lecture3.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
char buffer[10];

	/*
	 * Converting an decimal integer to a hexadecimal string.
	 */
	sprintf(buffer, "%X", 15);
	printf("Hex value is %s\n", buffer);

	/*
	 * Computing the sum of an array.
	 */
int arr[3] = {10,20,30};

int index = 0;
int sum = 0;

	while( index < 3)
	{
		sum = sum + arr[index];
		index++;
	}

	printf("The sum is %i 0x%X", sum, sum);


	/*
	 * Detect if the flags RXC0 and UPE0 are set in the
	 * UCSR0A - USART Control and Status Register A
	 */
	// if((UCSR0A & 0x84) == 0x84) <-- The hexdecimal version!
	if((UCSR0A & 0b10000100) == 0b10000100)
	{
		// They are both set!
	}
	else
	{
		// No, they are not both set!
	}

	return EXIT_SUCCESS;
}
