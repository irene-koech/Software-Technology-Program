package ik222hy_assign1;

import java.util.Scanner;

public class ShortName {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("First Name: ");
		String first = scanner.next();
		 
		System.out.print("Last Name: ");
		String last = scanner.next();
		
		last=last+" "+" "+" ";
		String initials = first.substring(0,1)+"."+ last.substring(0,4);
		System.out.println(initials);
		
		scanner.close();
		
	}

}
