package ik222hy_assign2;

import java.util.Scanner;

public class CountA {

	public static void main(String[] args) {
	
		Scanner count = new Scanner(System.in);

		System.out.print("Provide a line of text: ");

		String str= count.nextLine();

		int a= 0,A= 0;

		for (int i = 0;i<str.length();i++) {

			if (str.charAt(i)=='a')
				a++;
			if (str.charAt(i)=='A')
				A++;
		}
		System.out.println("Number of'a': " + a);
		System.out.println("Number of'A': " + A);
		count.close();
	}

}
