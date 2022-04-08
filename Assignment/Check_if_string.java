package Assignment;

import java.util.*;
import java.lang.*;


public class Check_if_string {
	
	public static Boolean is_it_string(String str) {
		
		//Check if it is all string using string wrapper class
				for (int k =0; k<str.length();k++) {
					if (Character.isDigit(str.charAt(k))) {
						return false;
					}
				}
				return true;
	}
	
	//The string s is the previous input and the string choice is the TYPE of input - contact, address etc
	public static String get_input(String s, String choice) {
		
		Scanner sc = new Scanner(System.in);
		Boolean end = is_it_string(s);
		
		while(!end) {
			
			System.out.println("Invalid input. Enter " + choice +":");
			s = sc.nextLine();
			end = is_it_string(s);
		
		}
		return s;
	}
}
