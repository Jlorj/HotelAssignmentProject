package Assignment;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class App {
    public static void main(String[] args) {

        // Initialise all 48 rooms
    	//Rooms rooms = new Rooms();
    	//String file = "src\\RoomsInformation.csv";
    	// BufferedReader reader = null;
    	// String line = "";
    	// try{
    	// reader = new BufferedReader(new FileReader(file));
            // room number is from 1-48 but array is zero indexed
    	// line = reader.readLine();
    	// while((line = reader.readLine()) != null){
    	//     String[] row = line.split(",");
    	//       rooms.addRoom(Integer.parseInt(row[0]), row[1], Boolean.parseBoolean(row[2]), Boolean.parseBoolean(row[3]), Boolean.parseBoolean(row[4]));
                //  }
    	//   rooms.displayRooms();
    	//}
    // catch(Exception e){
    	//    e.printStackTrace();
    	// }
    	// finally{
    	//    try{
    	//       reader.close();
    	//   }catch(IOException e){
    	//       e.printStackTrace();
    	//     }
    //   }
        
        
        
        
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
               
                	
                Scanner sc1 = new Scanner(System.in);
                


                System.out.println("(1) Update guest details");
                System.out.println("(2) Display Guest details");
               
                  
                int choice2 = sc1.nextInt();

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
                		
                		Scanner scan = new Scanner(System.in);
                		int change = scan.nextInt();
                		
                	while(change!=7) {
                		
                		switch(change) {
                		
                		//Change Name
                		case 1:
                			
                			System.out.println("Enter updated name: ");
                			String updatedname = scan.nextLine();
                			guest.getIc().setName(updatedname);
                			break;
           
                			
                		//Change Country
                		case 2:
                			
                			System.out.println("Enter updated country: ");
                			String updatedcountry = scan.nextLine();
                			guest.getIc().setName(updatedcountry);
                			break;
                			
                		//Change Gender
                		case 3:
                			
                			System.out.println("Enter updated gender: ");
                			String updatedgender = scan.nextLine();
                			guest.getIc().setName(updatedgender);
                			break;
                			
                		//Change  Nationality
                		case 4:
                			
                			System.out.println("Enter updated nationality: ");
                			String updatednationality = scan.nextLine();
                			guest.getIc().setName(updatednationality);
                			break;
                			
                			
                		//Change Address
                		case 5:
                			
                			System.out.println("Enter updated address: ");
                			String updatedaddress = scan.nextLine();
                			guest.getIc().setName(updatedaddress);
                			break;
                			
                		//Change Contact
                		case 6:
                			
                			System.out.println("Enter updated contact: ");
                			String updatedcontact = scan.nextLine();
                			guest.getIc().setName(updatedcontact);
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
                		
                	
                		change = scan.nextInt();
                	
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
        
        
        
        
        
        
        
        
        
        
        
        

       // ReservationDB DataBase = new ReservationDB();
        //Guest myGuest = new Guest();
        //Reservation myReservation = new Reservation(myGuest);

        //DataBase.appendRow(myGuest, myReservation);

        //Guest myGuest2 = new Guest();
        //Reservation myReservation2 = new Reservation(myGuest);

        // DataBase.appendRow(myGuest2, myReservation2);

        //System.out.println(DataBase.getReservationDataBase());


        //ArrayList<Object> thisRow = (ArrayList<Object>) DataBase.getReservationDataBase().get(0);
        //Guest mimicGuest = (Guest) thisRow.get(0);
        //System.out.println("GUEST NAME IS " + mimicGuest.getIc().getName());


    }

}
