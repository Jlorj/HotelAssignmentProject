package Assignment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DateCheck implements Serializable
{
	
	//	Use this to check for time different
	//	If dateMargin == 0 ---> Same date
	//	If dateMargin == -1 --> dt is earlier than date1
	//	If dateMargin == 1 ---> dt is later than date1
    //	int dateMargin = getZeroTimeDate(dt).compareTo(getZeroTimeDate(date1));

	public static void performChecks(Date dt, ReservationsDB DataBase, RoomServiceDB rsDB) throws ParseException {
		checkCheckOut(dt, DataBase);
		checkRoomService(dt, DataBase, rsDB);
		checkCheckIn(dt,  DataBase);
	}
	
	public static Date getZeroTimeDate(Date date1) {
        Date datereturned = date1;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date1 );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        datereturned = calendar.getTime();

        return datereturned;
    }

    
	public static void checkCheckOut(Date dt, ReservationsDB DataBase) throws ParseException {
		// Check for reservations that are due for checkout:
    	Date tempCheckOutDate;
    	int dateMargin;
    	Boolean anyReservationCheckedOut = false;
    	Reservation tempReservation;
  
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
    			if(dateMargin == 0 | dateMargin > 0) {
    				System.out.println("========================");
    				System.out.println("This reservation code has exceeded the check out time: " + tempReservation.getReservationCode());
    				System.out.println("Guest Name is: " + tempReservation.getGuest().getIc().getName());
    				System.out.println("========================");
    				DataBase.removeReservation(tempReservation.getReservationCode());
    				anyReservationCheckedOut = true;
    			}
    			
    			
    		}
    		
    	
        	
    		        		
    		
    		if(anyReservationCheckedOut == true) {
    			System.out.println("========================");
    			System.out.println("Overstayed Guests Have Been Evicted");
    			System.out.println("========================");
    		} 
    		else{
    			System.out.println("========================");
    			System.out.println("No Overstayed Guests Have Been Evicted");
    			System.out.println("========================");
    		}
    	}   	
    	
    	
	}

	
	public static void checkRoomService(Date dt, ReservationsDB DataBase, RoomServiceDB rsDB) throws ParseException {
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

        for(int i = 0; i < DataBase.getReservationDataBase().size(); i++) {
			tempReservation = (Reservation)((ArrayList<Object>) DataBase.getReservationDataBase().get(i)).get(1);
			tempCheckOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempReservation.getCheckOutDate());
			dateMargin = DateCheck.getZeroTimeDate(dt).compareTo(DateCheck.getZeroTimeDate(tempCheckOutDate));
			

			
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
        
	}
	
	public static void checkCheckIn(Date dt, ReservationsDB DataBase) throws ParseException {
		// Check for reservations that are due for checkout:
    	Date tempCheckIn;
    	int dateMargin;
    	Boolean anyReservationCheckedOut = false;
    	Reservation tempReservation;
  
    	
    	// Set Confirmation Time at Current Time.////
    	LocalDateTime curTime = dt.toInstant()
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
    			

    			
    			tempCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(tempReservation.getCheckInDate());
    			dateMargin = DateCheck.getZeroTimeDate(dt).compareTo(DateCheck.getZeroTimeDate(tempCheckIn));
    			
    			Calendar c1 = Calendar.getInstance();
    	    	c1.setTime(tempCheckIn);
    	        c1.add(Calendar.HOUR_OF_DAY, 15);
    	        tempCheckIn = c1.getTime();
    	        LocalDateTime checkInExpiry = tempCheckIn.toInstant()
    	                .atZone(ZoneId.systemDefault())
    	                .toLocalDateTime();
    	        
//    	        System.out.println("CheckinExpriy is "+ checkInExpiry);

    			
    			
    			//If current day is the checkout date
    			if(dateMargin == 0 | dateMargin < 0) {
    				if(curTime.isAfter(checkInExpiry)){
    					
    					System.out.println("========================");
    					System.out.println("This reservation code has exceeded the check in time: " + tempReservation.getReservationCode());
    					System.out.println("Guest Name is: " + tempReservation.getGuest().getIc().getName());
    					System.out.println("========================");
    					System.out.println("Check in Has Expired!");
    					anyReservationCheckedOut = true;
    					DataBase.removeReservation(tempReservation.getReservationCode());
    				}
    			}
    			
    			
    		}
    		
    	
        	
    		        		
    		
    		if(anyReservationCheckedOut == true) {
    			System.out.println("========================");
    			System.out.println("Expired Check Ins Have Been Removed");
    			System.out.println("========================");
    		} 
    		else{
    			System.out.println("========================");
    			System.out.println("No Expired Check Ins Have Been Removed");
    			System.out.println("========================");
    		}
    	}   	
    	
    	
	}

    
}

