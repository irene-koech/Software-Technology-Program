package DrunkenWalk;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DrunkenWalk {
	public static void main(String[] args) {
		int max, size, stepsTaken, drunker = 0;

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size: ");
		size = scan.nextInt();
		
		System.out.println("Enter the number of steps: ");
		max = scan.nextInt();

		System.out.println("Enter the number of walks: ");
		stepsTaken = scan.nextInt();
		scan.close();

		for (int i = 0; i <= stepsTaken - 1; i++) {

			RandomWalk rand = new RandomWalk(max, size);
			rand.walk();

			if (!(rand.inBounds())) {
				drunker++;

			}

		}
		double procent = (double) (drunker * 100.0f / stepsTaken); // percentage of fall
		DecimalFormat df = new DecimalFormat("#.00");

		System.out.println("Out of" + stepsTaken + " drunk people, " + drunker + " (" + df.format(procent) + "%" + ") "
				+ "fell into water");

	}

}
