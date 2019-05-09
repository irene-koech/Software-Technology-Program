package ik222hy_assign1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class WindChill {

	public static void main(String[] args) {

		Scanner input= new Scanner(System.in);
		
		System.out.println("Enter the temperature is Celsius");
		double Celsius =input.nextDouble();
		
		System.out.println("Enter the temperature is windspeed");
		double windspeed =input.nextDouble();
		
		windspeed = windspeed*3.6;
		
		double results= 13.12+0.6215*Celsius-11.37*Math.pow(windspeed, 0.16)+ 0.3965*Celsius* Math.pow(windspeed, 0.16);
		System.out.println("wind chill temperature"+results);
		input.close();
				
		DecimalFormat wtc = new DecimalFormat("0.#");
		String one=wtc.format(results);
		System.out.println(("wind chill temp") + one);
	}

	}

