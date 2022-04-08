package Assignment;

import java.io.Serializable;
import java.util.Scanner;

public class IdentityCard implements Serializable{
	
    private String name;
    private String country;
    private String gender;
    private String nationality;
    private String address;
    private String contact;

    IdentityCard(){
    	
        Scanner sc = new Scanner(System.in);
        
        //No error for name - no exception throw but check if no numbers present
        System.out.println("Input name");
        String name_input = sc.nextLine();
        name_input = Check_if_string.get_input(name_input, "name");
        this.name = name_input; 
        
        //No error for country - no exception throw but check if no numbers present
        System.out.println("Input country");
        String country_input = sc.nextLine();
        country_input = Check_country.get_input(country_input);
        this.country = country_input; 
        
        //No error for gender - no exception thrown but check validity
        System.out.println("Input gender");
        String gender = sc.nextLine();
        while(gender.toLowerCase().equals("f") == false && gender.toLowerCase().equals("m") == false && gender.toLowerCase().equals("female") == false && gender.toLowerCase().equals("male") == false) {
            System.out.println("Invalid input. Input gender");
            gender = sc.nextLine();
        }
        
        this.gender = gender;
        
        //No error for nationality - no exception throw but check if no numbers present
        System.out.println("Input nationality");
        String nation_input = sc.nextLine();
        nation_input = Check_if_string.get_input(nation_input, "nationality");
        this.nationality = nation_input; 
       
        //No error for address - no exception throw OK to have numbers and string!
        System.out.println("Input address");
        this.address =  sc.nextLine();
               
        //Convert into integers and throw exception if unable to do so & check length of number
        System.out.println("Input contact");
        String strnum = sc.nextLine();
        Boolean end = true;
        while (end) {
	        try {
	        	//Try Converting to integer + check length of string
	        	int phonenum = Integer.parseInt(strnum);
	        	
	        }
	        
	        //Catch exceptions 
	        catch(NumberFormatException n) {
	            System.out.println("Invalid input. Alphabets should not be in phone number. Input contact:");
	            strnum = sc.nextLine();
	            continue;
	        }
	        
	        finally {
	        	
	        	//Check the length of the phone number if its all numbers
	        	if(strnum.length() != 8) {
	                System.out.println("Invalid input. The length of the phone number should be 8. Input contact:");
	                strnum = sc.nextLine();
	                continue;
	        	}
	        	
	            this.contact = strnum;
	            end = false;
	        }
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getNationality(){
        return nationality;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getContact(){
        return contact;
    }

    public void setContact(String contact){
        this.contact = contact;
    }
}
