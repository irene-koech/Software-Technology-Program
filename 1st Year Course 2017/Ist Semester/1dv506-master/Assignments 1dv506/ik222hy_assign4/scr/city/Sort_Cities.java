package city;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Sort_Cities {

	public static void main(String[] args) {
		int sz = 0;

		try

		{ // read text from file

			File file = new File("C:\\Temp1\\cities.txt"); // 
			Scanner scan = new Scanner(file);
			System.out.println("Reading cities from file:" + file);
			ArrayList<City> cities = new ArrayList<City>(); 

			while (scan.hasNext()) { // read line

				sz++;
				
				String City1 = scan.nextLine();
				City city = new City(City1);
				cities.add(city);

			}
			System.out.println("Number of cities found: " + sz + "\n");

			Collections.sort(cities); // sort cities

			for (int i = 0; i < cities.size(); i++) { // sorted cities
				System.out.println(cities.get(i));

			}
			scan.close();

		} catch (IOException e) // exception if no file found

		{
			System.out.println(e.getMessage());
			System.exit(0);

		}

		System.out.println();

	}

}