package Assignment;


import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    public static void main(String[] args) {

        // Initialise all 48 rooms
        Rooms rooms = new Rooms();
        String file = "src\\RoomsInformation.csv";
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

        System.out.println("Welcome to ABC Hotel. Please choose one of the following options to proceed: ");

        while (true) {
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

            // g. Check-in (for walk in or reservation)
            System.out.println("(5) Check-in");

            // h. Check-out - print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
            System.out.println("(6) Check-out");
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
                    System.out.println("(2) Check-Out (Delete Reservation)");
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
                            DataBase.appendRow(myReservation.getReservationCode(), myReservation);
                            System.out.println("The reservation code is " + myReservation.getReservationCode());
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
                                System.out.println("------------------------");
                                System.out.println("(1) Room Details");
                                System.out.println("(2) Check-In Date");
                                System.out.println("(3) Check-Out Date");
                                System.out.println("(4) Number of Adults");
                                System.out.println("(5) Number of Children");
                                System.out.println("(6) Exit");
                                System.out.println("------------------------");
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
                                        sc.nextLine();
                                        myReservation.setAdults(revervationUpdatedFieldInt);
                                        System.out.println("Updated Number of Adults Is: " + myReservation.getAdults());

                                        break;
                                    case 5:
                                        System.out.println("Please Enter New Number of Children: ");
                                        revervationUpdatedFieldInt = sc.nextInt();
                                        sc.nextLine();
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
                            System.out.println("Enter the Price Of New Food");
                            price = sc.nextDouble();
                            sc.nextLine();
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

                            String time;
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

                            // Below code has to change to get current time instead of manual input
                            System.out.println("Enter The Time Of Room Service");
                            time = sc.nextLine();
                            RoomService newRoomService = new RoomService(time, menu);
                            while(true){
                                System.out.println("(1) Order Food");
                                System.out.println("(2) Exit");
                                continueOrder = sc.nextInt();
                                sc.nextLine();
                                if (continueOrder == 1){
                                    System.out.println("Name Of The Food");
                                    foodName = sc.nextLine();
                                    successfulOrder = newRoomService.order(foodName);
                                    if (successfulOrder){
                                        System.out.println("You have ordered " + foodName);
                                    }
                                    else{
                                        System.out.println("Food Item Not Available");
                                    }
                                }
                                else{
                                    break;
                                }
                            }
                            System.out.println("Any remarks?");
                            remarks = sc.nextLine();
                            newRoomService.setRemarks(remarks);

                            rsDB.append(code, newRoomService);
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

                case 5:
                    break;

            }
        }


    }

}
