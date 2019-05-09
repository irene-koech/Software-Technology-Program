package DrunkenWalk;
import java.util.Random;

public class RandomWalk {
	private int size = 0;
	private int stepsTaken = 0;
	private int max = 0;
	private int x, y;

	public RandomWalk(int maxSteps, int sizeOfPlane) {
		stepsTaken = sizeOfPlane;
		max = maxSteps;

	}

	public String toString() {

		return "Stepstaken: " + size + " Position; " + x + "," + y; // return

	}

	public void takeStep() {

		size++;

		Random rand = new Random();
		int steg = rand.nextInt(4);

		if (steg == 0) {

			x = x + 1;

		}

		else if (steg == 1) {// x-1
			x = x - 1;

		} else if (steg == 2) {// y+1
			y = y + 1;
		}

		else {// y-1

			y = y - 1;
		}

	}

	public boolean moreSteps() { // steps taken

		if (size < max) {

			return true;
		}

		return false;

	}

	public boolean inBounds() { // if steps taken without fall

		if (stepsTaken > x && stepsTaken > y && stepsTaken * -1 < x && stepsTaken * -1 < y) {

			return true;
		}

		return false;
	}

	public void walk() {
		while (inBounds() && moreSteps()) {
		takeStep();

		}
	}

}
