package Assignment;
	


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Assignment.Room.RoomStatus;

import java.io.*;
import java.time.*;

public class App4 {
    public static void main(String[] args) throws ParseException {

        // Initialize all 48 rooms
        Rooms rooms = new Rooms();
        String file = "src/RoomsInformation.csv";
        BufferedReader reader = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(file));
            // room number is from 1-48 but array is zero indexed
            line = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                rooms.addRoom(Integer.parseInt(row[0]), row[1], row[2], Boolean.parseBoolean(row[3]), Boolean.parseBoolean(row[4]), Boolean.parseBoolean(row[5]));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //end

        // Initializing the menu
        Menu menu = new Menu();
        Food friedRice = new Food("Fried Rice", "Stir-Fry", 8.5);
        Food muffin = new Food("Muffin", "Baked", 2);
        Food coke = new Food("Coke", "Cold", 3);
        Food salad = new Food("Salad", "Cold", 7);
        Food grilledChicken = new Food("Grilled Chicken", "Grilled", 13);
        menu.addFood(friedRice);
        menu.addFood(muffin);
        menu.addFood(coke);
        menu.addFood(salad);
        menu.addFood(grilledChicken);
        // end
        
        //Initializing the Reservations Database
        ReservationsDB DataBase = new ReservationsDB(); // creating a new DataBase of Reservations
        RoomServiceDB rsDB = new RoomServiceDB(); // creating a new Database for room service
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        //end
        
        // Intializing the Discount Codes List
        ArrayList<String> discountList = new ArrayList<String>();
        discountList.add("HAPPYHOLS");
        discountList.add("USECREDITCARD");
        //end
        
        // Create Fixed Date
        Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date myDate = myCalendar.getTime();
        Date dt = new GregorianCalendar(2022, Calendar.APRIL, 1).getTime();
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        // Beginning of the program
        System.out.println("Welcome to ABC Hotel. Please choose one of the following options to proceed: ");
        while (on) {
        	
        	// Check for reservations that are due for checkout:
        	Date tempCheckOutDate;
        	Date tempCurDate = dt;
        	int dateMargin;
        	Boolean anyReservationCheckedOut = false;
        	Reservation tempReservation;
        	RoomService tempRS;
        	
        	
        	// Set Confirmation Time at Current Time
        	LocalDateTime curTime = tempCurDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        	
        	// Set Pending Time at 15 Minutes After Confirmation Time
        	Calendar c1 = Calendar.getInstance();
        	c1.setTime(tempCurDate);
            c1.add(Calendar.MINUTE, 15);
            tempCurDate = c1.getTime();
            LocalDateTime pendingDateandTime = tempCurDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            
            // Set Delivery Time at 60 Minutes After Confirmation Time
        	c1 = Calendar.getInstance();
        	c1.setTime(tempCurDate);
            c1.add(Calendar.MINUTE, 45);
            tempCurDate = c1.getTime();
            LocalDateTime deliveredDateandTime = tempCurDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        	
            
            
        	//Check for empty DataBase
        	if (DataBase.getReservationDataBase().size() == 0) {
                System.out.println("========================");
        		System.out.println("Reservation Database is Empty");
                System.out.println("========================");
        	}
        	
        	else {
        		for(int i = 0; i < DataBase.getReservationDataBase().size(); i++) {
        			tempReservation = (Reservation)((ArrayList<Object>) DataBase.getReservationDataBase().get(i)).get(1);
        			tempCheckOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempReservation.getCheckOutDate());
        			dateMargin = DateCheck.getZeroTimeDate(dt).compareTo(DateCheck.getZeroTimeDate(tempCheckOutDate));
        			
        			//If current day is the checkout date
        			if(dateMargin == 0) {
        				System.out.println("========================");
        				System.out.println("This reservation code has exceeded the check out time: " + tempReservation.getReservationCode());
        				System.out.println("Guest Name is: " + tempReservation.getGuest().getIc().getName());
        				System.out.println("========================");
        				DataBase.checkOut(tempReservation.getReservationCode());
        				anyReservationCheckedOut = true;
        			}
        			
        			if (rsDB.rsDB.size() != 0) {
                		for(int j = 0; j < rsDB.rsDB.size(); j++) {
                			System.out.println("Reservation code " + tempReservation.getReservationCode());
                			
                			// Update Room Service Status
                        	LocalDateTime tempLocalDateTime = dt.toInstant()
                        	        .atZone(ZoneId.systemDefault())
                        	        .toLocalDateTime();
                        	
                        	tempRS = rsDB.getRoomService(tempReservation.getReservationCode());
                        	System.out.println("This is the Room Service" + tempRS);
                        	
                        	

        					
        					if(curTime.isAfter(tempRS.getOrderDateandTime()) ) {
                        		tempRS.setRoomServiceStatus(tempRS.roomServiceStatus.CONFIRMED);
                        	}

        					if(curTime.isAfter(tempRS.getPendingDateandTime()) ) {
                        		tempRS.setRoomServiceStatus(tempRS.roomServiceStatus.PREPARING);
        					}
        					
        					if(curTime.isAfter(tempRS.getDeliveredDateandTime()) ) {
                        		tempRS.setRoomServiceStatus(tempRS.roomServiceStatus.DELIVERED);
                        	}

                		}
                    }

        		}
        		
        	
            	
        		        		
        		
        		if(anyReservationCheckedOut == true) {
        			System.out.println("========================");
        			System.out.println("Reservations Have Been Removed");
        			System.out.println("========================");
        		} 
        		else{
        			System.out.println("========================");
        			System.out.println("No Reservations Have Been Removed");
        			System.out.println("========================");
        		}
        	}   	
        	
        	
        	
        	
        	// Print Current Time
        	System.out.println("Date & Time is = " + dt);

            // a. Create/Update/Search guests detail (Search by name using keyword/s)
            System.out.println("------------------------");
            System.out.println("(1) Guest"); // include Payment under Guest

            // b. Create/Update/Remove/Print reservation
            System.out.println("(2) Reservations");

            // c. Create/Update rooms details (include setting status like ‘Under Maintenance”)
            System.out.println("(3) Room Details");

            // d. Entering room service orders - list menu items for selection
            // e. Create/Update/Remove room service menu items.
            // RoomService Menu will be under here
            System.out.println("(4) Room Service Orders");

            System.out.println("(5) Shut Down");
            System.out.println("(6) Time Elapse");
            System.out.println("------------------------");

            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
          //--------------------------------------(1) Guest----------------------------------------------------//
                case 1:
                    System.out.println("------------------------");
                    System.out.println("(1) Update guest details");
                    System.out.println("(2) Search Guest details");
                    System.out.println("(3) Display all Guest details");
                    System.out.println("(4) Exit");
                    System.out.println("------------------------");
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
                            Reservation guestR = DataBase.getReservationFromReservationCode(code);

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

                            //Display switch statement to show guest details
                            System.out.println("Select which do you wish you change: ");
                            System.out.println("------------------------");
                            System.out.println("(1) Name ");
                            System.out.println("(2) Country ");
                            System.out.println("(3) Gender");
                            System.out.println("(4) Nationality ");
                            System.out.println("(5) Address ");
                            System.out.println("(6) Contact ");
                            System.out.println("(7) Exit ");
                            System.out.println("------------------------");

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

                                String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
                                System.out.printf(format, "Guest name:", "Guest country", "Guest gender", "Guest nationality", "Guest address", "Address contact" );
                                System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");
                                System.out.printf(format, guest.getIc().getName(), guest.getIc().getCountry(), guest.getIc().getGender(), guest.getIc().getNationality(), guest.getIc().getAddress(),guest.getIc().getContact());

                                System.out.println("Select which do you wish to change: ");
                                System.out.println("------------------------");
                                System.out.println("(1) Name ");
                                System.out.println("(2) Country ");
                                System.out.println("(3) Gender");
                                System.out.println("(4) Nationality ");
                                System.out.println("(5) Address ");
                                System.out.println("(6) Contact ");
                                System.out.println("(7) Exit ");
                                System.out.println("------------------------");

                                change = sc.nextInt();
                                sc.nextLine();
                            }
                            break;

                        //Print Guest details
                        case 2:
                            //Calling display guest method from reservationDB!!
                        	System.out.println("Please enter the Guest Name:");
                        	String name = sc.nextLine();                       	
                            DataBase.displayGuest(name);                           
                            break;

                        case 3:
                        	DataBase.displayAllGuests();
                            break;
                        
                        case 4:
                        	break;
                    }
                    break;
                //--------------------------------------(2) Reservations----------------------------------------------------//
                case 2:
                	Guest myGuest;
                    Reservation myReservation;
                    String reservationCode;
                    String revervationUpdatedFieldString;
                    int revervationUpdatedFieldInt;                  
                    boolean on2 = true;
                                    
                    while(on2) {
                    	System.out.println("Please select an option for Reservations");
                        System.out.println("----------------------------------------------");
                        System.out.println("(1) Check-In (Walk-in or Reservation)");
                        System.out.println("(2) Check-Out (Delete Reservation)"); // printing out the bill invoice as well
                        System.out.println("(3) Update an Existing Reservation");
                        System.out.println("(4) Display a Reservation by Reservation Code");
                        System.out.println("(5) Display Reservations Database");
                        System.out.println("(6) Exit");
                        System.out.println("-----------------------------------------------");
                        int option = sc.nextInt();
                        sc.nextLine();
                    	switch(option) {
	                        case 1:
	                        	System.out.println("Walk-in or Reservation?");
	                            System.out.println("(1) Walk-in");
	                            System.out.println("(2) Reservation");
	                            String choice_stay = sc.nextLine();
	     	                   //================= Added Exception Handling =====================//
	     	                            int stay = Check_if_int.get_input(choice_stay, "1 or 2");
	     	                            while(stay!=1 && stay!=2)
	     	                            	stay = Check_if_int.get_input(choice_stay, "1 or 2");
	     	                            myGuest = new Guest();
	     	                            myReservation = new Reservation(myGuest);
	     	                            int roomOption = 0;
	     	                            roomOption = 0;
	     	                            String roomType;
	                            
	                            while(true){
	                                System.out.println("Input Room Type:");
	                                System.out.println("(1) Single Room");
	                                System.out.println("(2) Double Room");
	                                System.out.println("(3) Deluxe");
	                                System.out.println("(4) VIP Suite");
	                                roomOption = sc.nextInt();
	                                if (roomOption < 1 || roomOption > 4){
	                                    System.out.println("Input Is Invalid");
	                                    continue;
	                                }
	                                if (roomOption == 1){
	                                    roomType = "SINGLE";
	                                }
	                                else if (roomOption == 2){
	                                    roomType = "DOUBLE";
	                                }
	                                else if (roomOption == 3){
	                                    roomType = "DELUXE";
	                                }
	                                else{
	                                    roomType = "VIPSUITE";
	                                }
	                                int adults = myReservation.getAdults();
	                                int children = myReservation.getChildren();
	                                if ((adults > 1 || children > 1) && roomType.equals("SINGLE")){
	                                    System.out.println("SINGLE Room Only Accommodate At Most 1 Adult And 1 Child");
	                                    continue;
	                                }
	                                else if ((adults > 2 || children > 2) && roomType.equals("DOUBLE")){
	                                    System.out.println("DOUBLE Room Only Accommodate At Most 2 Adults And 2 Children");
	                                    continue;
	                                }
	                                else if ((adults > 4 || children > 5) && roomType.equals("DELUXE")){
	                                    System.out.println("DELUXE Room Only Accommodate At Most 4 Adults And 5 Children");
	                                    continue;
	                                }
	                                else if ((adults > 6 || children > 7)){
	                                    System.out.println("VIPSUITE Only Accommodate At Most 6 Adults and 7 Children");
	                                    continue;
	                                }
	                                break;
	                            }
	                            
	                            ArrayList<String> vacancy = rooms.printVacantRoomWithInfo(Room.RoomType.valueOf(roomType));
	                            if (vacancy.size() == 0){
	                                System.out.printf("There Are No More %s Rooms Available ", roomType);
	                                break;
	                            }
	                            sc.nextLine();
	                            String formattedRoomNum;
	                            while(true){
	                                System.out.println("Please choose a room");
	                                formattedRoomNum = sc.nextLine();
	                                if (vacancy.contains(formattedRoomNum)){
	                                    Room room = rooms.getRoom(rooms.deformatRoomNum(formattedRoomNum));
	                                    if (stay == 1) {
	                                    	room.setStatus(Room.RoomStatus.OCCUPIED);
	                                    }
	                                    else room.setStatus(Room.RoomStatus.RESERVED);
	                                    myReservation.setRoom(room);
	                                    myReservation.generateCode();
	                                    DataBase.appendRow(myReservation.getReservationCode(), myReservation);
	                                    System.out.println("Reservation Is Successful");
	                                    
	                  // =================================== Adding in the printing of the reservation details if reservation is successful =============================//
	                                    
	                                    //Just to print the header nicely
	                                    String pattern = "=";
	                                    System.out.println(pattern.repeat(49)+ " Reservation Details " + pattern.repeat(49));
	                                    System.out.println();
	                                    
	                                    //First print out guest details
	                                    
	                                    //Getting identity card from current reservation (aka myreservation)
	                                    IdentityCard guest_info = myReservation.getGuest().getIc();
	                                    String format1 = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
	                                    String format2 = "%-20s%-20s%-20s%-20s%-20s%n";
	                                    System.out.printf(format1, "Guest name:", "Guest country", "Guest gender", "Guest nationality", "Guest address", "Address contact" );
	                                    System.out.printf(format1, "================", "==================", "==================", "==================", "==================",  "==================");
	                                    System.out.printf(format1, guest_info.getName(), guest_info.getCountry(), guest_info.getGender(), guest_info.getNationality(), guest_info.getAddress(),guest_info.getContact());
	                                    
	                                    System.out.println();
	                                    
	                                    //Secondly print out this particular room details
	                                    
	                                    System.out.printf(format2, "Room Number","Room Type","Wifi","Smoking","View");
	                                    System.out.printf(format2, "================", "==================", "==================", "==================", "==================");
	                                    System.out.printf(format2,room.getRoomNum(),room.getRoomType(),room.getWifi(),room.getWifi(),room.getSmoking(),room.getView());
	                                    
	                                    //Finally print out reservation code
	                                    System.out.println("The Reservation Code Is " + myReservation.getReservationCode());
	                                    break;
	                                }
	                                int input = 0;
	                                boolean exitStatus = false;
	                                while(true){
	                                    System.out.println("Room Selected Is Not Available");
	                                    System.out.println("(1) Reselect A Room");
	                                    System.out.println("(2) Exit");
	                                    input = sc.nextInt();
	                                    sc.nextLine();
	                                    if (input < 1 || input > 2){
	                                        continue;
	                                    }
	                                    if (input == 1){
	                                        rooms.printVacantRoomWithInfo(Room.RoomType.valueOf(roomType));
	                                    }
	                                    else{
	                                        exitStatus = true;
	                                    }
                                    break;
	                                }
	                                if (exitStatus){
	                                    break;
	                                }
	                            }
	                            break;
	
	                        case 2: // printing guest's hotel bill invoice
	                            System.out.println("Please enter reservation code: ");
	                            reservationCode = sc.nextLine();
	                            if(DataBase.getReservationFromReservationCode(reservationCode) == null) {
	                                System.out.println("No reservation under this code has been made!");
	                            } else {
	                            	String format = "%-20s%-20s%-20s%-20s%-20s%n";
	                            	DecimalFormat df = new DecimalFormat("0.00");
	                            	double totalPayment = DataBase.getReservationFromReservationCode(reservationCode).getPayment();
	                                System.out.println("#" + reservationCode + " INVOICE");
	                                System.out.println("Description");
	                                System.out.println("Check-in Date:  " + DataBase.getReservationFromReservationCode(reservationCode).getCheckInDate());
	                                System.out.println("Check-out Date: " + DataBase.getReservationFromReservationCode(reservationCode).getCheckOutDate());
	                                System.out.println("========================================================");
	                                System.out.println("Your Room Service Orders");
	                                System.out.println("========================================================");
	                                System.out.printf(format, "Date Ordered", "Food Ordered", "Time Ordered", "Amount", "Remarks");
	                                System.out.printf(format, "================", "================", "================", "================", "================");
	                                if (rsDB.rsDB.get(reservationCode) != null) {
	                                	for (int i=0;i<rsDB.rsDB.get(reservationCode).size();i++) {
	                                    	rsDB.rsDB.get(reservationCode).get(i).printBill();
	                                    	totalPayment += rsDB.rsDB.get(reservationCode).get(i).getPayment();
	                                    }
	                                }
	                                System.out.println("========================================================");
	                                DataBase.getReservationFromReservationCode(reservationCode).printBill();
	                                System.out.println("Subtotal: SGD"  + df.format(totalPayment));
	                                System.out.println("Enter Discount Code:");
	                                String discountCode = sc.next();
	                                for (int i=0;i<discountList.size();i++) {
	                                	if (discountCode.equals(discountList.get(i))) {
	                                		totalPayment *= (1-DataBase.getReservationFromReservationCode(reservationCode).getDiscount());
	                                	}
	                                }
	                                System.out.println("GST:" + DataBase.getReservationFromReservationCode(reservationCode).stringGST);
	                                System.out.println("Service Charge: " + DataBase.getReservationFromReservationCode(reservationCode).stringServiceTax);
	                                totalPayment += DataBase.getReservationFromReservationCode(reservationCode).totalTax * totalPayment;
	                                System.out.println("Your Total Bill is: SGD" + df.format(totalPayment)); 
	                                // maybe include a statement below to indicate paid by credit card or cash?
	                                DataBase.getReservationFromReservationCode(reservationCode).useCreditCard();
	                                System.out.println("========================================================");	                                
	                                DataBase.checkOut(reservationCode);
	                            }
	                            break;
	                            
	                        case 3:
	                            System.out.println("Please enter reservation code: ");
	                            reservationCode = sc.nextLine();
	                            
	                            //Exception handling - for null pointers
	                            
	                            if(DataBase.getReservationFromReservationCode(reservationCode) == null) {
	                                System.out.println("No reservation under this code has been made!");
	                                break;
	                            }
	                            
	                            
	                            myReservation = DataBase.getReservationFromReservationCode(reservationCode);
	                            int j = 0;
	                            while (j != 1) {
	                                System.out.println("Please select which reservation detail you would like to update");
	                                System.out.println("------------------------");
	                                System.out.println("(1) Check-In Date");
	                                System.out.println("(2) Check-Out Date");
	                                System.out.println("(3) Number of Adults");
	                                System.out.println("(4) Number of Children");
	                                System.out.println("(5) Exit");
	                                System.out.println("------------------------");
	                                option = sc.nextInt();
	                                sc.nextLine();
	                                switch(option) {
	                                    case 1:
	                                    	//Exception checking for check in date - calling the same class and methods as when you first made the reservation
	                                        System.out.println("Please Enter New Check-In Date: ");
	                                        String update_checkin = sc.nextLine();
	                                        revervationUpdatedFieldString = Check_date.get_input(update_checkin);
	                                        myReservation.setCheckInDate(revervationUpdatedFieldString);
	                                        System.out.println("Updated Check-In Date Is: " + myReservation.getCheckInDate());
	                                        break;
	                                    case 2:
	                                    	//Exception handling for check out date - calling the same class and methods as when you first made the reservation and check in on top
	                                        System.out.println("Please Enter New Check-Out Date: ");
	                                        String update_checkout = sc.nextLine();
	                                        revervationUpdatedFieldString = Check_date.get_input(update_checkout);
	                                        myReservation.setCheckOutDate(revervationUpdatedFieldString);
	                                        System.out.println("Updated Check-Out Date Is: " + myReservation.getCheckOutDate());
	                                        break;
	                                    case 3:
	                                    	//Exception handling for number of adults - same as previous 
	                                        System.out.println("Please Enter New Number of Adults: ");
	                                        String update_adults = sc.nextLine();
	                                        revervationUpdatedFieldInt = Check_if_int.get_input(update_adults, "Number of adults");
	                                        myReservation.setAdults(revervationUpdatedFieldInt);
	                                        System.out.println("Updated Number of Adults Is: " + myReservation.getAdults());
	                                        break;
	                                    case 4:
	                                    	//Exception handling for number of children - same as previous
	                                        System.out.println("Please Enter New Number of Children: ");
	                                        String update_children = sc.nextLine();
	                                        revervationUpdatedFieldInt = Check_if_int.get_input(update_children, "Number of children");
	                                        myReservation.setChildren(revervationUpdatedFieldInt);
	                                        System.out.println("Updated Number of Children Is: " + myReservation.getChildren());
	                                        break;
	                                    case 5:
	                                        j=1;
	                                        break;
	                                }
	                            }
	                            break;
	                        case 4:
	                        	System.out.println("Please enter reservation code: ");
	                            reservationCode = sc.nextLine();
	                            DataBase.printReservationFromReservationCode(reservationCode);                           
	                            break;
	                        case 5:
	                        	DataBase.displayAllReservations();                            
	                            break;
	                        case 6:
	                        	on2 = false;
	                            break;
	                    	}                        
                    }
                    break;
                //--------------------------------------(3) Room Details----------------------------------------------------//
                case 3:
                    int userInput;
                    String roomNumber;
                    String statusStr;
                    boolean exit = true;
                    while(exit){
                        System.out.println("---------------------------------------");
                        System.out.println("(1) Check room availability");
                        System.out.println("(2) Update Room Status");
                        System.out.println("(3) Print Vacant Rooms By Room Type");
                        System.out.println("(4) Print ALL Rooms BY Status");
                        System.out.println("(5) Exit");
                        System.out.println("---------------------------------------");
                        userInput = sc.nextInt();
                        sc.nextLine();
                        
                        switch(userInput){
                        
                      //Adding case 1 - Checking of room availability details by entering room number 
                    	case 1:
                        	int roomnum_or_guest = 0;
                        	boolean roomcheck = true;
                        	boolean found_room = false;
                        	while(roomcheck) {
                        		found_room = false;
	                        	System.out.println("Check room availability via:");
	                        	System.out.println("(1) Room Number");
	                        	System.out.println("(2) Guest Name");
	                        	System.out.println("(3) Exit");
	                        	int check_choice = sc.nextInt();
	                        	System.out.println();
	                        	//Inner switch statement for  either check room by room number or guest details
	                        	switch(check_choice) {
	                        	case 1:
	                        		sc.nextLine();
	                                System.out.println("Input the Room Number");
	                                roomNumber = sc.nextLine();
	                                for (int i = 0; i<48; i++) {
	                                	if (roomNumber.equals(rooms.rooms[i].getRoomNum())) {
	                                	//Find the room status first
	                                	RoomStatus room_status = rooms.rooms[i].getStatus();
	                                	String available;
	                                	if(room_status.equals(RoomStatus.OCCUPIED) || room_status.equals(RoomStatus.RESERVED) || room_status.equals(RoomStatus.MAINTENANCE)) {
	                                		available = "Not available!";
	                                	}
	                                	else {
	                                		available = "available!";
	                                	}
	                                	//Printing details of the room
	                                	String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
	                                	System.out.printf(format,"Room availability", "Room Number","Room Type","Wifi","Smoking","View");
	                                    System.out.printf(format, "================", "==================", "==================", "==================", "==================","==================");
	                                    System.out.printf(format,available,rooms.rooms[i].getRoomNum(),rooms.rooms[i].getRoomType(),rooms.rooms[i].getWifi(),rooms.rooms[i].getSmoking(),rooms.rooms[i].getView());
	                                	
	                                    roomcheck = true;
	                                    found_room = true;
	                                } //for if specific room is found
				                           
		                       } // for for loop
		                                roomnum_or_guest = 1;
		                                break;
                        
	                        	case 2:
	                        		sc.nextLine();
	                                System.out.println("Enter guest name: ");
	                                //Extract guest from reservation database
	                                String guest_check = sc.nextLine();
                                
	                                ArrayList<Object> reserv = DataBase.getReservationDataBase();
	                                for(int i = 0; i < reserv.size(); i++) {
	                                    if (((Reservation) ((ArrayList<Object>) DataBase.getReservationDataBase().get(i)).get(1)).getGuest().getIc().getName().equals(guest_check)) {
	                                    	Reservation target_reserv = ((Reservation) ((ArrayList<Object>) DataBase.getReservationDataBase().get(i)).get(1));
	                                    	
	                                    	RoomStatus room_status = target_reserv.getRoom().getStatus();
	                                    	String available;
	                                    	if(room_status.equals(RoomStatus.OCCUPIED) || room_status.equals(RoomStatus.RESERVED) || room_status.equals(RoomStatus.MAINTENANCE)) {
	                                    		available = "Not available!";
	                                    	}
	                                    	else {
	                                    		available = "available!";
	                                    	}
	                                    	String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
	                                    	System.out.printf(format,"Room availability", "Room Number","Room Type","Wifi","Smoking","View");
	                                        System.out.printf(format, "================", "==================", "==================", "==================", "==================","==================");
	                                        System.out.printf(format,available,target_reserv.getRoom().getRoomNum(),target_reserv.getRoom().getRoomType(),target_reserv.getRoom().getWifi(),target_reserv.getRoom().getSmoking(),target_reserv.getRoom().getView());
	                                    	
	                                    	roomcheck = true;
	                                    	found_room = true;
	                                    	continue;
	                                    }
	                                }
	                                roomnum_or_guest = 2;
	                                break;
	                                
	                        	case 3:
	                        		roomcheck = false;
	                        		continue;
	                        		
	                            default: roomcheck = true;
	                        	}
	                        	
	                        	if (!(found_room)) {
	                        		switch(roomnum_or_guest) {
	                        		case 1:
	                        			System.out.println("Room Number is not Valid!");
	                        			break;
	                        		case 2:
	                        			System.out.println("Guest Name is not Valid!");
	                        			break;
	                        		}
	                        		roomcheck = true;
	                        	}
	                        }
	                        break;  
                            case 2:
                            	boolean found = false;
                                System.out.println("Input the Room Number");
                                roomNumber = sc.nextLine();
                                for (int i=0; i<48;i++) {
                                	if (roomNumber.equals(rooms.rooms[i].getRoomNum())) {
                                		found = true;
                                		boolean update = true;
                                		while (update) {
	                                		System.out.println("What would you like to update?");
	                                		System.out.println("(1) Change Weekday Rate");
	                                        System.out.println("(2) Change Weekend Rate");
	                                        System.out.println("(3) Change Room Status");
	                                        System.out.println("(4) Exit");
	                                        int choice1 = sc.nextInt();
	                                        switch(choice1) {
		                                        case 1:
		                                        	System.out.println("Current room's Weekday rate: " + rooms.rooms[i].weekdayRate);
		                                        	System.out.println("Set Weekday Rate:");
		                                        	double weekdayRate = sc.nextDouble();		                                      	
		                                        	rooms.rooms[i].setWeekdayRate(weekdayRate);
		                                        	System.out.println("Updated room's Weekday rate: " + rooms.rooms[i].weekdayRate);
		                                        	break;
		                                        	
		                                        case 2:
		                                        	System.out.println("Current room's Weekend rate: " + rooms.rooms[i].weekendRate);
		                                        	System.out.println("Set Weekend Rate:");
		                                        	double weekendRate = sc.nextDouble();
		                                        	rooms.rooms[i].setWeekendRate(weekendRate);
		                                        	System.out.println("Updated room's Weekend rate: " + rooms.rooms[i].weekendRate);
		                                        	break;
		                                        	
		                                        case 3:
		                                        	System.out.println("Enter New Status For Room Number " + roomNumber);
		                                        	sc.nextLine();
		                                            statusStr = sc.nextLine();		                                            
		                                            if (statusStr.equals("VACANT") || statusStr.equals("OCCUPIED") || statusStr.equals("RESERVED") || statusStr.equals("MAINTENANCE")){
		                                                rooms.getRoom(i+1).setStatus(Room.RoomStatus.valueOf(statusStr));
		                                            }
		                                            else{
		                                                System.out.println(statusStr + " Is Not A Valid Room Status");
		                                            }
		                                        	break;
		                                        case 4:
		                                        	update = false;
		                                        	break;
		                                        	
		                                        default: update = true;
	                                        }
                                		}
                                	}
                                }
                                if (!found) System.out.println("Invalid Room Number");
                                break;
                                
                            case 3:
                                rooms.printVacantRooms();
                                break;
                                
                            case 4:
                                rooms.printRoomsByStatusMain();
                                break;
                                
                            case 5:                           	
                                exit = false;
                                break;
                        }

                    }
                    break;
                  //------------------------------------(4) Room Service Orders----------------------------------------------------//
                case 4:                   
                    String name;
                    String preparation;
                    double price;
                    boolean success;
                    boolean on1 = true;
                    
                    while(on1) {
	                	System.out.println("---------------------------------");
	                    System.out.println("(1) Display Menu");
	                    System.out.println("(2) Add Menu Item");
	                    System.out.println("(3) Remove Menu Item");
	                    System.out.println("(4) Update Menu Item");
	                    System.out.println("(5) Place New Room Service Order");
	                    System.out.println("(6) Check Room Service Status");
	                    System.out.println("(7) Exit");
	                    System.out.println("---------------------------------");
	                    choice2 = sc.nextInt();                   
	                    sc.nextLine();
	                    
	                	switch(choice2){
	
	                        case 1:
	                            menu.printMenuItems();
	                            break;
	
	                        case 2:
	                            System.out.println("Enter Name Of New Food");
	                            name = sc.nextLine();
	                            System.out.println("Enter Preparation Method Of New Food");
	                            preparation = sc.nextLine();
	                            
	                            //Exception checking here using check_if_int to check if price entered is a double!
	                            System.out.println("Enter the Price Of New Food");
	                            String price_input = sc.nextLine();
	                            price = Check_if_double.get_input(price_input, "Price of New Food");
	                        
	                            Food newFood = new Food(name, preparation, price);
	                            success = menu.addFood(newFood);
	                            if (success){
	                                System.out.println("Food Item Added Successfully!");
	                            }
	                            else{
	                                System.out.println("Food Item already in the Menu");
	                            }
	                            break;
	
	                        case 3:
	                            System.out.println("Enter Name of the Food To Be Removed");
	                            name = sc.nextLine();
	                            success = menu.removeFood(name);
	                            if (success){
	                                System.out.println("Food Item Removed Successfully");
	                            }
	                            else{
	                                System.out.println("Food Item Not In Menu");
	                            }
	                            break;
	                        
	                        case 4:
	                        	menu.printMenuItems();
	                        	System.out.println("Enter Name of the Food");
	                        	name = sc.nextLine();
	                        	success = menu.updateMenu(name);
	                        	if (success){
	                                System.out.println("Food Item Updated Successfully");
	                            }
	                            else{
	                                System.out.println("Food Item Not In Menu");
	                            }
	                        	break;
	                        	
	                        case 5:
	                            String remarks;
	                            int continueOrder;
	                            boolean successfulOrder;
	                            String foodName;
	                            String code;
	                            
	                            System.out.println("Enter The Reservation Code");
	                            code = sc.nextLine();
	                            if (!DataBase.isValidCode(code)){
	                                System.out.println("Reservation Code Is Not Valid");
	                                System.out.println("Exiting...");
	                                break;
	                            }
	
	                            while(true){
	                            	menu.printMenuItems();
	                                System.out.println("(1) Order Food");
	                                System.out.println("(2) Exit");
	                                
	                                //Handle exception here! - check if input is integer!
	                                String continueOrder_input = sc.nextLine();
	                                continueOrder = Check_if_int.get_input(continueOrder_input, "(1) or (2)");
	                                
	                                if (continueOrder == 1){
	                                	RoomService newRoomService = new RoomService(menu);
	                                    System.out.println("Name Of The Food");
	                                    foodName = sc.nextLine();
	                                    successfulOrder = newRoomService.order(foodName, dt);
	                                    if (successfulOrder){
	                                        System.out.println("You have ordered " + foodName);
	                                        System.out.println("Any remarks?");
	                                        remarks = sc.nextLine();
	                                        newRoomService.setRemarks(remarks);
	                                        rsDB.append(code, newRoomService);
	                                    }
	                                    else{
	                                        System.out.println("Food Item Not Available");
	                                    }
	                                }
	                                else{
	                                    break;
	                                }
	                            }
	                            
	                            break;
	
	                        case 6:
	                            System.out.println("Enter The Reservation Code");
	                            code = sc.nextLine();
	                            RoomService.RoomServiceStatus rsStatus = rsDB.checkStatus(code);
	                            if (rsStatus == null){
	                                System.out.println("The Guest Did Not Order Any Food");
	                            }
	                            else{
	                                System.out.println("The Current Status Is " + rsStatus);
	                            }
	                            continue;
	                            	
	                        case 7:
	                        	on1 = false;
	                            break;
	                    }
                    }
                    break;
                    
                default:
                    System.out.printf("Input of %d Is Not Valid\n", choice);
                    break;
             //----------------------------------(5) Shut Down-------------------------------------//
            case 5:
                on = false; // Shutting down Hotel Reservation System
                break;
             //----------------------------------(6) Time Lapse-------------------------------------//
            case 6: 
            	System.out.println("Please select an option for Time Elapsed");
                System.out.println("----------------------------------------------");
                System.out.println("(1) 5 Minutes");
                System.out.println("(2) 15 Minutes"); // printing out the bill invoice as well
                System.out.println("(3) 30 Minutes");
                System.out.println("(4) 1 Hour");
                System.out.println("(5) 2 Hours");
                System.out.println("(6) 6 Hours");
                System.out.println("(7) 1 Day");
                System.out.println("(8) 2 Days");
                System.out.println("(9) 3 Days");
                System.out.println("(10) 1 Week");
                System.out.println("-----------------------------------------------");
                int option = sc.nextInt();
                sc.nextLine();
                Calendar c = Calendar.getInstance();

            	switch(option) {
            	case 1: 
            		c.setTime(dt);
                    c.add(Calendar.MINUTE, 5);
                    dt = c.getTime();
                	break;
            	case 2: 
            		c.setTime(dt);
                    c.add(Calendar.MINUTE, 15);
                    dt = c.getTime();
                	break;
            	case 3: 
            		c.setTime(dt);
                    c.add(Calendar.MINUTE, 30);
                    dt = c.getTime();
                	break;
            	case 4: 
            		c.setTime(dt);
                    c.add(Calendar.HOUR, 1);
                    dt = c.getTime();
                	break;
            	case 5: 
            		c.setTime(dt);
                    c.add(Calendar.HOUR, 2);
                    dt = c.getTime();
                	break;
            	case 6: 
            		c.setTime(dt);
                    c.add(Calendar.HOUR, 6);
                    dt = c.getTime();
                	break;
            	case 7: 
            		c.setTime(dt);
                    c.add(Calendar.DATE, 1);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    dt = c.getTime();
                	break;
            	case 8: 
            		c.setTime(dt);
                    c.add(Calendar.DATE, 2);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    dt = c.getTime();
                	break;
            	case 9: 
            		c.setTime(dt);
                    c.add(Calendar.DATE, 3);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    dt = c.getTime();
                	break;
            	case 10: 
            		c.setTime(dt);
                    c.add(Calendar.DATE, 7);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    dt = c.getTime();
                	break;            
                	
            	}
            
            //Adds an additional Hour At The End Of The While Loop
//            Calendar c = Calendar.getInstance();
//            c.setTime(dt);
//            c.add(Calendar.HOUR, 1);
//            dt = c.getTime();
            break;
            }
        }//End of While Loop
    }
}
