package ik222hy_assign2;

import java.util.Random;
import java.util.Scanner;

public class HighLow {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner sc = new Scanner(System.in);
		int Number = 1 + ran.nextInt(100);

		for (int i = 1; i < 11; i++) {
			if (i == 11) {
				System.out.println("\nSorry!\nYou have tried 10 times!\nThe correct number is: " + Number);
				System.exit(0);
			}
			int Guess = 0, counter = 0;
			do {
				System.out.println("Guess:");
				Guess = sc.nextInt();

				if (Guess > Number)
					System.out.println("high");
				else if (Guess < Number)
					System.out.println("low");
				counter++;
			} while (Guess != Number && counter < 13);

			{
				if (counter < 13)
					System.out.println("Excellent");
				sc.close();
			}
		}
	}
}
