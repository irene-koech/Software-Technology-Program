package city;
public class City implements Comparable<City> {

	private String city;
	private int postCode;

	public City(String newCity) { 
		String[] cities = newCity.split(";");
		String postNo = cities[0];
		city = cities[cities.length - 1];

		postCode = Integer.parseInt(postNo);
	}

	public int compareTo(City in) { // compare postCode

		return postCode - in.postCode;

	}

	public String toString() { // return postCode and city in correct way


		return postCode + " " + city;

	}

}