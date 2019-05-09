#include<stdio.h>

int main(void){
	int i = 12;
	int *p = &i;
	int **pp = &p;
	
	printf("%i %i %i\n", i,*p,**pp);	
	
	int j = 15;
	int *k = &j;
	pp = &k;
	
	printf("%i %i %i\n", j,*k,**pp);
	**pp = 100;
	printf("%i\n", j);

	int *x = 100;
}
