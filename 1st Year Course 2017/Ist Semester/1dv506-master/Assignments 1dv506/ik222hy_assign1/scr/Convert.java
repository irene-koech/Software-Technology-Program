package ik222hy_assign1;

import java.util.Scanner;

public class Convert {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		double Fahrenheit;
		double Celsius;
		
System.out.println(" Enter degree on Fahrenheit:");

       Fahrenheit = scn.nextInt();
       Celsius = (Fahrenheit -32)*5/9;
       
       System.out.print(" convert fahrenheit to celsius");
       System.out.println(+ Celsius);
       scn.close();
       

	}

}
