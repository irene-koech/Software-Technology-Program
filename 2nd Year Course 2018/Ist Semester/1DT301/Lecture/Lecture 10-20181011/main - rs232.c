#include <avr/io.h>

/*
	Initialize to : baudrate 4800, 8 bit data, 2 stop bits, no parity.
	Assuming fosc=16 mhz. (ATmega328p on a Arduino Uno board).
*/
void rs232_init()
{
	UBRR0L = 207;	// Baudrate 4800 
	UBRR0H = 0;
	UCSR0B = (1<<TXEN0) | (1<<RXEN0); // TX/RX enabled
	UCSR0C = (1<<USBS0) | (3<<UCSZ00); // 8 data bits, 2 stop bits, No parity, Asynch UASRT
}

void rs232_send(unsigned char uc)
{
	// Wait for empty transmit buffer.
	while ( !( UCSR0A & (1<<UDRE0)) );
	
	// Put byte in buffer and send.
	UDR0 = uc;
}

unsigned char rs232_recv()
{
	/* Wait for data to be received */
	while ( !( UCSR0A & (1<<RXC0)));
	
	/* Get and return received data from buffer */
	return UDR0;		
}

int main(void)
{
char *some_text = "C is fun and so is Assembler!\r\n";
int i = 0;

	rs232_init();
  
    while (1) 
    {
		i = 0;
		/*
			Send string content character-by-character (as bytes).
			A string is ended when 0 is detected.
		*/
		while(some_text[i] != 0)
		{
			rs232_send(some_text[i]);
			i++;
		}
    }
}

