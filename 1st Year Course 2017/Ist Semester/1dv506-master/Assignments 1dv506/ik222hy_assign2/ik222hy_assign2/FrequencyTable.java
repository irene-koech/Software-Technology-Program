package ik222hy_assign2;

import java.util.*;

public class FrequencyTable {

	public static void main(String[] args) {
		System.out.println("Frequencies when rolling a dice 6000 times.");

		Random rand = new Random();
		int[] freq = new int[7];
		
		for (int f = 1; f < 6000; f++) {
			++freq[1 + rand.nextInt(6)];
		}
		System.out.println();
		{

			for (int i = 1; i < freq.length; i++)
				System.out.println(i + "\t" + freq[i]);
		}
	}
}
