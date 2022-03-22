package Package;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
				

		
				

		
		
		
		
		
		ReservationsDB DataBase = new ReservationsDB(); // creating a new DataBase of Reservations
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to ABC Hotel. Please choose one of the following options to     proceed: ");

        while (true) {
            // a. Create/Update/Search guests detail (Search by name using keyword/s)
            System.out.println("(1) Guest"); // include Payment under Guest 

            // b. Create/Update/Remove/Print reservation
            System.out.println("(2) Reservations");

            // c. Create/Update rooms details (include setting status like ‘Under Maintenance”)   
            System.out.println("(3) Room Details"); 

            // d. Entering room service orders - list menu items for selection
            // e. Create/Update/Remove room service menu items.
            // RoomService Menu will be under here
            System.out.println("(5) Room Service Orders"); 
        
            // g. Check-in (for walk in or reservation) 
            System.out.println("(6) Check-in");
            
            // h. Check-out - print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount) 
            System.out.println("(7) Check-out");
            
        
            
            int choice = sc.nextInt();
            switch (choice) {
            
                case 1:
               
                	
                              


                System.out.println("(1) Update guest details");
                System.out.println("(2) Display Guest details");
               
                  
                int choice2 = sc.nextInt();
                sc.nextLine();


                //Inner switch 
                switch(choice2) {
                
              //Update guest details
                	case 1:
                		
                		
                		//get by code -> go into database -> then switch statement then change!
                		ArrayList<Object> checkempty = DataBase.getReservationDataBase();
                		
                		//Get reservation code to determine which guest wants to change
                		System.out.println("Enter reservation code: ");
                		String code = sc.nextLine();
                	
                		
                		//Get the reservation from the reservationDB method
                		Reservation guestR =  DataBase.getReservationFromReservationCode(code);
                		
                		//Get guest from reservation
                		Guest guest;
					
                		if(guestR!=null) {
                			guest = guestR.getGuest();
                		}
                		else {
                			if(checkempty.size() == 0)
                				System.out.println("Reservation is Empty!");
                			else
                				System.out.println("Reservation code is not valid");
                			break;
                		}
                		
                	
                		
                		//Get the 
                		//Display switch statement to show guest details
                		System.out.println("Select which do you wish you change: ");
                		System.out.println("(1) Name ");
                		System.out.println("(2) Country ");
                		System.out.println("(3) Gender");
                		System.out.println("(4) Nationality ");
                		System.out.println("(5) Address ");
                		System.out.println("(6) Contact ");
                		System.out.println("(7) Exit ");
                		
                		int change = sc.nextInt();
                		sc.nextLine();

                		
                		
                	while(change!=7) {
                		
                		switch(change) {
                		
                		//Change Name
                		case 1:
                			
                			System.out.println("Enter updated name: ");
                			String updatedname = sc.nextLine();
                			guest.getIc().setName(updatedname);
                			break;
           
                			
                		//Change Country
                		case 2:
                			
                			System.out.println("Enter updated country: ");
                			String updatedcountry = sc.nextLine();
                			guest.getIc().setCountry(updatedcountry);
                			break;
                			
                		//Change Gender
                		case 3:
                			
                			System.out.println("Enter updated gender: ");
                			String updatedgender = sc.nextLine();
                			guest.getIc().setGender(updatedgender);
                			break;
                			
                		//Change  Nationality
                		case 4:
                			
                			System.out.println("Enter updated nationality: ");
                			String updatednationality = sc.nextLine();
                			guest.getIc().setNationality(updatednationality);
                			break;
                			
                			
                		//Change Address
                		case 5:
                			
                			System.out.println("Enter updated address: ");
                			String updatedaddress = sc.nextLine();
                			guest.getIc().setAddress(updatedaddress);
                			break;
                			
                		//Change Contact
                		case 6:
                			
                			System.out.println("Enter updated contact: ");
                			String updatedcontact = sc.nextLine();
                			guest.getIc().setContact(updatedcontact);
                			break;
                			
           
                		
                		}//Switch statement for changing for specific choices breaks
                		
                		//Printing updated details of guest
                		System.out.println("Updated Guest Details: ");
                		System.out.println("Guest Name :           " + guest.getIc().getName());
                		System.out.println("Guest Country:         " + guest.getIc().getCountry());
                		System.out.println("Guest Gender:          " + guest.getIc().getGender());
                		System.out.println("Guest Nationality:     " + guest.getIc().getNationality());
                		System.out.println("Guest Address:         "+ guest.getIc().getAddress());
                		System.out.println("Guest) Contact:        " + guest.getIc().getContact());
                		
                	
                	
                		System.out.println("Select which do you wish you change: ");
                		System.out.println("(1) Name ");
                		System.out.println("(2) Country ");
                		System.out.println("(3) Gender");
                		System.out.println("(4) Nationality ");
                		System.out.println("(5) Address ");
                		System.out.println("(6) Contact ");
                		System.out.println("(7) Exit ");
                		
                	
                		change = sc.nextInt();
                	
                		}
                		
                		break;
                		
        
                	//Print Guest details 
                	case 2:
                		
                		//Calling display guest method from reservationDB!!
                		DataBase.DisplayGuest();
                		
                		break;
                		
                	case 4:
                		continue;
                		
                }
                   break;
                   
                case 2:
                	
            		
            			System.out.println("Please select an option for Reservations");
            			System.out.println("(1) Check-In (Create New Reservation)");
            			System.out.println("(2) Check-Out (Delete Reservation)");
            			System.out.println("(3) Update an Existing Reservation");
            			System.out.println("(4) Display a Reservation by Reservation Code");
            			System.out.println("(5) Display Reservations Database");
            

            			
            			Guest myGuest;
            			Reservation myReservation;
            			String reservationCode;
            			String revervationUpdatedFieldString;
            			int revervationUpdatedFieldInt; 
            			int option = sc.nextInt();
            			
            			sc.nextLine();
            			
            			switch(option) {
            			case 1:
            				myGuest = new Guest();
            				myReservation = new Reservation(myGuest);
            				DataBase.appendRow(myReservation.getReservationCode(), myReservation);
            				System.out.println("getReservationCode is " + myReservation.getReservationCode());
            				break;
            				
            			case 2: 
            				System.out.println("Please enter reservation code: ");
            				reservationCode = sc.nextLine();
            				if(DataBase.getReservationFromReservationCode(reservationCode) == null) {
            					System.out.println("No reservation under this code has been made!");
            				} else {
            					DataBase.checkOut(reservationCode);
            				}
            				break;
            				
            			case 3: 
            				System.out.println("Please enter reservation code: ");
            				reservationCode = sc.nextLine();
            				if(DataBase.getReservationFromReservationCode(reservationCode) == null) {
            					System.out.println("No reservation under this code has been made!");
            					break;
            				} 
            				myReservation = DataBase.getReservationFromReservationCode(reservationCode);
            				int j = 0;
            				while (j != 1) {
            					System.out.println("Please select which reservation detail you would like to update");
            					System.out.println("(1) Room Details");
            					System.out.println("(2) Check-In Date");
            					System.out.println("(3) Check-Out Date");
            					System.out.println("(4) Number of Adults");
            					System.out.println("(5) Number of Children");
            					System.out.println("(6) End Updating");
            					option = sc.nextInt();
            					sc.nextLine();
            					switch(option) {
            					case 1: 
            						//
            						//
            						////
            						//////Enter Code
            						////
            						//
            						//
            						break;
            					case 2:
            						System.out.println("Please Enter New Check-In Date: ");
            						revervationUpdatedFieldString = sc.nextLine();
            						myReservation.setCheckInDate(revervationUpdatedFieldString);
            						System.out.println("Updated Check-In Date Is: " + myReservation.getCheckInDate());
            						break;
            					case 3:
            						System.out.println("Please Enter New Check-Out Date: ");
            						revervationUpdatedFieldString = sc.nextLine();
            						myReservation.setCheckOutDate(revervationUpdatedFieldString);
            						System.out.println("Updated Check-Out Date Is: " + myReservation.getCheckOutDate());

            						break;
            					case 4:
            						System.out.println("Please Enter New Number of Adults: ");
            						revervationUpdatedFieldInt = sc.nextInt();
            						myReservation.setAdults(revervationUpdatedFieldInt);
            						System.out.println("Updated Number of Adults Is: " + myReservation.getAdults());

            						break;
            					case 5:
            						System.out.println("Please Enter New Number of Children: ");
            						revervationUpdatedFieldInt = sc.nextInt();
            						myReservation.setChildren(revervationUpdatedFieldInt);
            						System.out.println("Updated Number of Children Is: " + myReservation.getChildren());
            						break;
            					case 6:
            						j=1;
            						break;
            					}	
            				}
            	
            				break;	
            			case 4: 
            				DataBase.displayAllReservations();
            				break;
            			case 5:
            				System.out.println(DataBase.getReservationDataBase());
            				break;
            			}

            

                    break;
                case 3:
                
                    break;

                case 4: 
                    break;

                case 5: 
                    break;

                case 6: 
                    break;

                case 7: 
                    break;

                case 8:
                    break;

                case 9: 
                    break;

            }
            System.out.println("------------------------");
        }
        
		
		
		
		
	}

}
