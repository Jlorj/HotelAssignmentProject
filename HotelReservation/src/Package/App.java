package Assignment;


import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {

        // Initialise all 48 rooms
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
                rooms.addRoom(Integer.parseInt(row[0]), row[1], Boolean.parseBoolean(row[2]), Boolean.parseBoolean(row[3]), Boolean.parseBoolean(row[4]));
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

        // Initialising the menu
        Menu menu = new Menu();
        Food friedRice = new Food("Fried Rice", "Fried", 6.5);
        Food muffin = new Food("Muffin", "Baked", 2);
        Food coke = new Food("Coke", "Cold",3);
        menu.addFood(friedRice);
        menu.addFood(muffin);
        menu.addFood(coke);
        // end

        ReservationsDB DataBase = new ReservationsDB(); // creating a new DataBase of Reservations
        RoomServiceDB rsDB = new RoomServiceDB(); // creating a new Database for room service
        Scanner sc = new Scanner(System.in);
        boolean on = true;

        // Beginning of the program
        System.out.println("Welcome to ABC Hotel. Please choose one of the following options to proceed: ");
        while (on) {
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
            System.out.println("------------------------");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("------------------------");
                    System.out.println("(1) Update guest details");
                    System.out.println("(2) Display Guest details");
                    System.out.println("(3) Exit");
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
                            DataBase.DisplayGuest();
                            break;

                        case 3:
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Please select an option for Reservations");
                    System.out.println("----------------------------------------------");
                    System.out.println("(1) Check-In (Create New Reservation)");
                    System.out.println("(2) Check-Out (Delete Reservation)"); // printing out the bill invoice as well
                    System.out.println("(3) Update an Existing Reservation");
                    System.out.println("(4) Display a Reservation by Reservation Code");
                    System.out.println("(5) Display Reservations Database");
                    System.out.println("(6) Exit");
                    System.out.println("-----------------------------------------------");

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
                                sc.nextLine();
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
                                break;
                            }
                            int adults = myReservation.getAdults();
                            int children = myReservation.getChildren();
                            if ((adults > 1 || children > 1) && roomType.equals("SINGLE")){
                                System.out.println("SINGLE Room Only Accommodate At Most 1 Adult And 1 Child");
                                break;
                            }
                            else if ((adults > 2 || children > 2) && roomType.equals("DOUBLE")){
                                System.out.println("DOUBLE Room Only Accommodate At Most 2 Adults And 2 Children");
                                break;
                            }
                            else if ((adults > 4 || children > 5) && roomType.equals("DELUXE")){
                                System.out.println("DELUXE Room Only Accommodate At Most 4 Adults And 5 Children");
                                break;
                            }
                            else if ((adults > 6 || children > 7)){
                                System.out.println("VIPSUITE Only Accommodate At Most 6 Adults and 7 Children");
                                break;
                            }

                            ArrayList<String> vacancy = rooms.printVacantRoomWithInfo(Room.RoomType.valueOf(roomType));
                            if (vacancy.size() == 0){
                                System.out.printf("There Are No More %s Rooms Available ", roomType);
                                break;
                            }
                            String formattedRoomNum;
                            while(true){
                                System.out.println("Please choose a room");
                                formattedRoomNum = sc.nextLine();
                                if (vacancy.contains(formattedRoomNum)){
                                    Room room = rooms.getRoom(rooms.deformatRoomNum(formattedRoomNum));
                                    room.setStatus(Room.RoomStatus.RESERVED);
                                    myReservation.setRoom(room);
                                    myReservation.generateCode();
                                    DataBase.appendRow(myReservation.getReservationCode(), myReservation);
                                    System.out.println("Reservation Is Successful");
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
                            	String format = "%-20s%-20s%-20s%n";
                            	DecimalFormat df = new DecimalFormat("0.00");
                            	double totalPayment = DataBase.getReservationFromReservationCode(reservationCode).getPayment();
                                System.out.println("#" + reservationCode + " INVOICE");
                                System.out.println("Description");
                                System.out.println("========================================================");
                                System.out.println("Your Room Service Orders");
                                System.out.println("========================================================");
                                System.out.printf(format, "Date Ordered", "Time Ordered", "Amount", "Remarks");
                                System.out.printf(format, "================", "================", "================", "================");
                                if (rsDB.rsDB.get(reservationCode) != null) {
                                	for (int i=0;i<rsDB.rsDB.get(reservationCode).size();i++) {
                                    	rsDB.rsDB.get(reservationCode).get(i).printBill();
                                    	totalPayment += rsDB.rsDB.get(reservationCode).get(i).getPayment();
                                    }
                                }
                                System.out.println("================================================================");
                                DataBase.getReservationFromReservationCode(reservationCode).printBill();
                                System.out.println("Your Total Bill is: " + df.format(totalPayment)); // maybe include a statement below to indicate paid by credit card or cash?
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
                            DataBase.displayAllReservations();
                            break;
                        case 5:
                            System.out.println(DataBase.getReservationDataBase());
                            break;
                        case 6:
                            break;
                    }
                    break;

                case 3:
                    int userInput;
                    int roomNumber;
                    String statusStr;
                    boolean exit = false;
                    while(true){
                        System.out.println("---------------------------------------");
                        System.out.println("(1) Update Room Status");
                        System.out.println("(2) Print Vacant Rooms By Room Type");
                        System.out.println("(3) Print ALL Rooms BY Status");
                        System.out.println("(4) Exit");
                        System.out.println("---------------------------------------");
                        userInput = sc.nextInt();
                        sc.nextLine();
                        switch(userInput){
                            case 1:
                                System.out.println("Input the Room Number");
                                roomNumber = sc.nextInt();
                                sc.nextLine();
                                if (roomNumber < 1 || roomNumber > 48) {
                                    System.out.println("Invalid Room NUmber");
                                    break;
                                };
                                System.out.println("Enter New Status For Room NUmber " + roomNumber);
                                statusStr = sc.nextLine();
                                if (statusStr.equals("VACANT") || statusStr.equals("OCCUPIED") || statusStr.equals("RESERVED") || statusStr.equals("MAINTENANCE")){
                                    rooms.getRoom(roomNumber).setStatus(Room.RoomStatus.valueOf(statusStr));
                                }
                                else{
                                    System.out.println(statusStr + " Is Not A Valid Room Status");
                                }
                                break;
                            case 2:
                                rooms.printVacantRooms();
                                break;
                            case 3:
                                rooms.printRoomsByStatusMain();
                                break;
                            case 4:
                                exit = true;
                                break;
                        }
                        if (exit){
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("---------------------------------");
                    System.out.println("(1) Display Menu");
                    System.out.println("(2) Add Menu Item");
                    System.out.println("(3) Remove Menu Item");
                    System.out.println("(4) Place New Room Service Order");
                    System.out.println("(5) Check Room Service Status");
                    System.out.println("(6) Exit");
                    System.out.println("---------------------------------");

                    String name;
                    String preparation;
                    double price;
                    boolean success;

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
                                    successfulOrder = newRoomService.order(foodName);
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

                        case 5:
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

                        case 6:
                            break;
                    }
                    break;

                default:
                    System.out.printf("Input of %d Is Not Valid\n", choice);
                    break;

                case 5:
                    on = false; // Shutting down Hotel Reservation System
                    break;
            }
        }
    }
}
