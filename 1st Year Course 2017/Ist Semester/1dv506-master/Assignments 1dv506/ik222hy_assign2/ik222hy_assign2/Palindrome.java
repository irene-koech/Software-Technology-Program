package ik222hy_assign2;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a text");

		String line = scn.nextLine();
		String text = line.toLowerCase();

		boolean isPalindrome = true;
		int startLn = 0;
		int endLn = text.length() - 1;
		while (startLn < endLn) {

			String sletter = text.substring(startLn, startLn + 1);
			String eletter = text.substring(endLn, endLn + 1);
			if (sletter.equals(eletter) == false) {

				isPalindrome = false;
				break;
			}
			startLn++;
			endLn--;
		}
		if (isPalindrome == true) {
			System.out.println(text + " is a paladrom ");

		} else
			System.out.println(text + " is not a paladrom");
		scn.close();
	}
}
