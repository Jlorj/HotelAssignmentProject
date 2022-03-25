package Assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Check_date {

	//check format
	public static Boolean check_format(String date) {
		
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(date);
			return true;
		}
		
		catch(ParseException e) {
			return false;
		}
		
	}
	//Check year
	public static Boolean check_year(String date) {
		
		//Slice the year from string 
		String year = date.substring(0,4);
		
		try {
			int year_input = Integer.parseInt(year);
			
			if(year_input >= 2022)
				return true;
			else
				return false;
		}

		catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	//check month 
	public static Boolean check_month(String date) {
		
		//Slice the month from string
		String month = date.substring(5,7);
		try {
			int month_input = Integer.parseInt(month);
			
			if(month_input<=12 && month_input>=1)
				return true;
			
			else
				return false;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Boolean check_date(String date) {
		
		//Slice the date from string 
		String date_input = date.substring(8,10);
		
		try {
			int datee = Integer.parseInt(date_input);
			
			//Note that we assume the months have dates from 1 to 30 so our test case cannot include months like Feb, Jan etc!
			if(datee>=1 && datee<=30)
				return true;
			else
				return false;
		}
		
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static String get_input(String date) {
		
		Scanner sc = new Scanner(System.in);
		Boolean end = check_format(date) && check_year(date) && check_month(date) && check_date(date);
		String new_input = null;
		
		
		while(!end) {
			
			System.out.println("Invalid Date. Enter Date:");
			new_input = sc.nextLine();
			end = check_format(new_input) && check_year(new_input) && check_month(new_input) && check_date(new_input);

		}
		
		return new_input;
	}
	
	
}
