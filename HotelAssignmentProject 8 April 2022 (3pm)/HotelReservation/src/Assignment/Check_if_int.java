package Assignment;

import java.util.Scanner;

public class Check_if_int {

	
	public static Boolean check_int(String s) {
		
		try {
			int num = Integer.parseInt(s);
			return true;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	// s is the user input choice can be adults or children (for printing)
	public static int get_input(String s,String choice) {
		
		Scanner sc = new Scanner(System.in);
		Boolean end = check_int(s);
		String new_input = null;
		
		if (end)
			return Integer.parseInt(s);
		
		while(!end) {
			System.out.println("Invalid input. Enter " + choice + ":");
			new_input = sc.nextLine();
			end = check_int (new_input);
		}
		
		return Integer.parseInt(new_input);
		
	}
}
