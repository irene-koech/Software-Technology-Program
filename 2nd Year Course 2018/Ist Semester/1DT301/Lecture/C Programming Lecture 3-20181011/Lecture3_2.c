/*
 ============================================================================
 Name        : Example6.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {

	/*
	 * Declare a string capable of storing max 100 characters (including
	 * the \0 character that terminates a string).
	 *
	 */
	char my_string[100];

	/*
	 * Read string from keyboard.
	 * Note that there is no address operator (&) in front of the
	 * variable since an array is already considered to be a (constant) pointer.
	 */
	scanf("%s", my_string);

	int number_of_a = 0; // Number of detected A's
	int i = 0; // Loop counter

	/*
	 * Calculate the number of A's in the user entered string.
	 */
	while(my_string[i] != 0) //Continue as long as we haven't reached the end of the string.
	{

		if(my_string[i]=='A') // Or my_string[i]== 65
		{
			number_of_a++;
		}
		i++;
	}

	// Print the number of detected A's in hex and decimal
	printf("Detected %x (%i) A's.\n", number_of_a, number_of_a);

	// Print the same text as above but to a buffer instead.
	char buffer[200]; // This is the area to which sprintf() will print.
	sprintf(buffer, "Detected %x (%i) A's.\n", number_of_a, number_of_a);
	printf("%s", buffer);

	return EXIT_SUCCESS;
}
