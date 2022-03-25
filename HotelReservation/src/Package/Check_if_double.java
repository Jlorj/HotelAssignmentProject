package Assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Check_if_double {

public static Boolean check_int(String s) {
		
		try {
			double num = Float.parseFloat(s);
			return true;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	// s is the user input choice can be adults or children (for printing)
	public static double get_input(String s,String choice) {
		
		Scanner sc = new Scanner(System.in);
		Boolean end = check_int(s);
		String new_input = null;
		
		if (end)
			return Float.parseFloat(s);
		
		while(!end) {
			System.out.println("Invalid input. Enter " + choice + ":");
			new_input = sc.nextLine();
			end = check_int (new_input);
		}
		
		return Float.parseFloat(new_input);
		
	}
	
}
