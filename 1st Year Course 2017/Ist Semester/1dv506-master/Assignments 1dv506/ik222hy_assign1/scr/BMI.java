package ik222hy_assign1;

import java.util.Scanner;


public class BMI {
	public static void main(String[] args) {
		
    Scanner scn = new Scanner(System.in);
    
    System.out.println("Weight");
    double Weight = scn.nextDouble();
    
    System.out.println("lenght");
    double length = scn.nextDouble();
    
    double BMI = Weight/(length*length);
    
    System.out.println(Math.round(BMI));
    
    scn.close();
    	
	

	}

}
