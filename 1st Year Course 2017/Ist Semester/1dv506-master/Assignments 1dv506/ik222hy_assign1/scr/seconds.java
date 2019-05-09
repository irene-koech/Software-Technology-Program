package ik222hy_assign1;

import java.util.Scanner;

public class seconds {

	public static void main(String[] args)
	
	{
		
	Scanner scn = new Scanner(System.in);
	
	System.out.println("hour");
	int h = scn.nextInt();
	
	System.out.println("min");
	int m = scn.nextInt();
	
	System.out.println("sec");
	int s = scn.nextInt();
	scn.close();
	
	h = 60*60;
	m = m*60;
	int sum = h+m+s;
	System.out.println(" Second" + sum);
	
	}

}
