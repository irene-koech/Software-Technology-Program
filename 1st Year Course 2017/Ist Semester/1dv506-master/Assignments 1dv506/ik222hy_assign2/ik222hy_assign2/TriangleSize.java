package ik222hy_assign2;

import java.util.Scanner;

public class TriangleSize {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Triangle size: ");
		int n = sc.nextInt();
		if (n % 2 == 0 || n < 0) {

			System.out.print("Error!");
			System.exit(0);
		}
		System.out.println("Right-Angled Triangle:");

		for (int i = 1; i <= n; i++) {

			for (int k = 0; k < n - i; k++) {

				System.out.print(" ");
			}
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
 
		System.out.println("\nIsosceles Triangle:");

		for (int i = 1; i <= n; i = i + 2) {
			for (int k = 1; k <= (n - i) / 2; k++) {
				System.out.print(" ");
				
			}
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}

}
