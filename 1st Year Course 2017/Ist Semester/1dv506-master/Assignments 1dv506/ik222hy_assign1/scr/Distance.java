package ik222hy_assign1;

import java.lang.Math;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Distance {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("declare x1");
		double x1= sc.nextDouble();
		
		System.out.println("declare x2");
		double x2= sc.nextDouble();
		
		System.out.println("declare y1");
		double y1= sc.nextDouble();
		
		System.out.println("declare y2");
		double y2= sc.nextDouble();
		
		sc.close();

		System.out.println(".");
	
		
		double X = (x1-x2);
		double Y= (y1-y2);
		
		double result= Math.sqrt(X)+Math.sqrt(Y);
	
		
		DecimalFormat d = new DecimalFormat("0.###");
		String Three=d.format(result);
		System.out.println(("distance") + Three);
		
	}

}
