package ik222hy_assign2;

import java.util.Scanner;

public class CountDigits {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Provide Positive integer: ");
		String num = scanner.nextLine();
		
		int zero = 0, odd = 0;
		int even = 0;
		
		for (int i = 0; i < num.length(); i++) {
			int ch = Character.getNumericValue(num.charAt(i));

			if (num.charAt(i) != '0') {
				if (ch % 2 == 0)
					even++;
				else
					odd++;

			} else
				zero++;
		}
		System.out.println("zero number:" + zero);
		System.out.println("Odd number:" + odd);
		System.out.println("even number:" + even);
		scanner.close();

	}

}
