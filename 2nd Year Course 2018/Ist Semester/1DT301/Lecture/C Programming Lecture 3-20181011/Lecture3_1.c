/*
 ============================================================================
 Name        : Example7.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>


int main(void) {

	unsigned char UCSR0A = 0x80; // Fake that RXP is set but not UPE

	if ((UCSR0A & 0x84) == 0x84)
	{
		// If we get here then both flags were set.
	}

	return EXIT_SUCCESS;
}
