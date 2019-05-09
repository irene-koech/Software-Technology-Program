package ik222hy_assign2;

public class BirthdayCandles {

	public static void main(String[] args) {
		int candles = 0; 
		int box = 0; 
		int total = 0;

		for (int yr = 1; yr <= 100; yr++) {
			if (candles < yr) {
				if (yr - candles < 24)
					box = 1;
				else {
					if ((yr - candles) % 24 == 0)

						box = (yr - candles) / 24;
					else
						box = (yr - candles) / 24 + 1;
				}
				candles = candles + box * 24 - yr;
			}else {
				box=0;
				candles=candles-yr;
			}
			if (box != 0) {
			}
			total = total + box;
			System.out.println("\n Number of boxes: " + total + ", Remaining candles: " + candles);
		}
	}
}