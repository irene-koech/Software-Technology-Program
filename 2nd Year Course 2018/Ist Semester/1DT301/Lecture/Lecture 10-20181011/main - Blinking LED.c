// ATmega328p
#define F_CPU 16000000UL

#include <avr/io.h>
#include <util/delay.h>

int main(void)
{
	DDRB = 1 << DDB5;
		
    while (1) 
    {
		PORTB = PORTB | (1 << PORTB5);
		_delay_ms(200);
		PORTB = PORTB & ~(1 << PORTB5);
		_delay_ms(200);		
    }
}

