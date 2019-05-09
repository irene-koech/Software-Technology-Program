package Histogram;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CountChar {
public static void main(String[] args) throws IOException {

			StringBuilder text = new StringBuilder();
			text = add();
			count(text);

		}

		public static StringBuilder add() throws IOException {

			StringBuilder text = new StringBuilder();

			try { // read text from file

				File file = new File("C:\\Temp\\HistoryOfProgramming.txt");
				Scanner scan = new Scanner(file);

				while (scan.hasNext()) { 
					String line = scan.nextLine();
					text.append(line);

				}

				scan.close();

			} catch (IOException e) { // exception no file found
				System.out.println(e.getMessage());
				System.exit(0);

			}

			return text;

		}

		public static void count(StringBuilder in) { 
			int upperCase = 0, lowerCase = 0, whiteSpace = 0, others = 0;

			for (int i = 0; i < in.length(); i++) {
				if (Character.isUpperCase(in.charAt(i))) {
					upperCase++;
				}

				else if (Character.isLowerCase(in.charAt(i))) {
					lowerCase++;
				}

				else if (Character.isWhitespace(in.charAt(i))) {
					whiteSpace++;
				}

				else {
					others++;
				}

			}

			System.out.println("Amount of Uppercase: " + upperCase);
			System.out.println("Amount of Lowercase: " + lowerCase);
			System.out.println("Amount of WhiteSpase: " + whiteSpace);
			System.out.println("Amount: " + others);

		}

	}

