package hash341;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		CityTable example = new CityTable("US_Cities_LL.txt", 1600);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the city you would like to search for: ");
		String search = scan.nextLine();
		System.out.println("Found: " + example.find(search));
		City a = example.find(search);
		System.out.println("http://www.google.com/maps?z=10&q=" + a.getLongitude() + "," + a.getLatitude());
		example.writeToFile("US_Cities_LL.ser");
	}

}
