package ik222hy_assignment3;

import java.util.Scanner;

public class SweIDMain {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.print("Enter SweID in format YYMMDD-NNNN: ");
		String Id = scn.nextLine();
		scn.close();

		if (SweID.isCorrect(Id)) {
			System.out.println("ID Number is correct");
			System.out.println("FirstPart: " + SweID.getFirstPart(Id));
			System.out.println("SecondPart: " + SweID.getSecondPart(Id));

			if (SweID.isFemale(Id))
				System.out.println("Female");
			if (SweID.isMale(Id))
				System.out.println("Male");

			String secondId = "860707-0556";
			System.out.println("The second ID: " + secondId);
			if (SweID.areEqual(Id, secondId)) {
				
				System.out.println("The two ID numbers are coresponded");
			} else {
				
				System.out.println("The two ID numbers are not coresponded");
			}
		} else {
			System.out.println("ID Number is not correct");
		}

	}

}
