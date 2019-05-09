package ik222hy_assign1;

import java.util.Scanner;


public class Sum {

	public static void main(String[] args) {
		Scanner w = new Scanner(System.in);
		System.out.println("Sum of three digits");
		
		int input = w.nextInt();
		
		int a = input % 10;
		int b = input/ 10;
		int c = b % 10;
		int d = b /10;
		
		w.close();
		System.out.println(a+c+d);
				
				
		
		
	}

}
