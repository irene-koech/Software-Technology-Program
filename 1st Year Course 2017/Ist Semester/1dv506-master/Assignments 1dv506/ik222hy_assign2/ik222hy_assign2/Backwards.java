package ik222hy_assign2;

import java.util.Scanner;

public class Backwards {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Provide a line of text: ");
		String original, reverse = "";
		original = scn.nextLine();
		System.out.println("");

		for (int i = original.length()- 1;i >= 0;i--) {

			reverse = reverse + original.charAt(i);
		}
		scn.close();
		System.out.println("Reversed text is= " + reverse);
	}

}
