package ik222hy_assign1;

import java.util.Random;

public class TelephoneNumber {

	public static void main(String[] args) 
	{
		Random r = new Random();
	
		int random = 0;
		int y = r.nextInt(1000);
		int n = r.nextInt(1000000);
		
		System.out.println(random+""+y+"-"+n);    
		
		
	}
	

}
