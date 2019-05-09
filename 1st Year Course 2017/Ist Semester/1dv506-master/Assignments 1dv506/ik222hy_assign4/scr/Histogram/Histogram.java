package Histogram;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Histogram {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		File file = new File("C:\\Temp\\integers.txt"); // name of the file source
		String[] fileArr;
		Scanner in = null;
		String line;
		int others = 0;
		int start, end, temp;
		try {
			in = new Scanner(file);
		} catch (IOException e) { // exception and sending error message
			System.out.println(e.getMessage());

		}
		while (in.hasNextLine()) { // reading the file
			line = in.nextLine(); // reading the line
			fileArr = line.split(",");
			for (int i = 0; i < fileArr.length; i++) {
				try {
					temp = Integer.parseInt(fileArr[i]);
					if (temp > 0 && temp <= 100) {
						list.add(temp);
					} else {
						others++;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println("Reading integers from the file: " + file);
		System.out.println("Number of integers in the interval [1,100]: " + list.size());
		System.out.println("Others: " + others);
		System.out.println();
		System.out.println("Histogram ");
		for (int i = 0; i < 10; i++) {
			start = i * 10 + 1;
			end = i * 10 + 10;
			System.out.print("\n" + start + "-" + end + "\t");
			System.out.print("|");
			for (int index = 0; index < list.size(); index++) {
				if (list.get(index) <= end && list.get(index) >= start) {
					System.out.print("*");

				}
			}
		}
	}
}
