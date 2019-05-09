package ik222hy_assign1;

import java.util.Scanner;

public class Time {

	public static void main(String[] args)
	{
	Scanner scn = new Scanner(System.in);
	System.out.println("enter seconds");
	int sec = scn.nextInt();
	
	int hours = sec/3600;
	int min = (sec/60) %60;
	int sc= sec %60;
	
	scn.close();
	System.out.println(hours+"hours"+min+"minutes"+sc+"Second");
				
	}

}
