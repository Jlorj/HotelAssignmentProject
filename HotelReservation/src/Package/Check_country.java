package Assignment;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Check_country {

	public static Boolean check(String input) {
		
		//ArrayList containing list of countries
		ArrayList <String> countrylist = new ArrayList<String>();
		String [] countrycodes = Locale.getISOCountries();
		for(String x: countrycodes) {
			
			Locale country = new Locale(" ",x);
			countrylist.add(country.getDisplayCountry().toLowerCase());

		}
		

		//Check if input in countries
		if (countrylist.contains(input.toLowerCase()) )
				return true;
		else
			return false;

	}
	
	// s is the input of the user when asked for country
	public static String get_input(String s) {
		
		Scanner sc = new Scanner(System.in);
		Boolean end = check(s);
		String new_input = null;
		while(!end) {
			System.out.println("Invalid country. Enter country:");
			new_input = sc.nextLine();
			end = check(new_input);
		}
		
		return new_input;
	}
	
}
