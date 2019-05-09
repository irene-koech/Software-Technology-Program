package ik222hy_assign2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ReverseOrder {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		int i = 1;

		do {
			System.out.print("Integer" + i + ":");
			list.add(scn.nextLine());
			i++;
		} while 
			(Integer.parseInt(list.get(i - 2)) > 0);
		
		System.out.println("\nNumber of positive integers: " + (i - 2));
		Collections.reverse(list);
		list.remove(0);
		
		System.out.print("In reverse order: " + list.toString());
		scn.close();
	}
}
