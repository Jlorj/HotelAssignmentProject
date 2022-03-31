package Package;

import java.util.ArrayList;

public class ReservationsDB {
    private ArrayList<Object> ReservationDataBase = new ArrayList<Object>();
    //Index		Variable
    //0			Reservation Code
    //1			Reservation
    ReservationsDB(){}

    public void appendRow(String reservationCode, Reservation reservation) {
        ArrayList<Object> newRow = new ArrayList<Object>();
        newRow.add(reservationCode);
        newRow.add(reservation);

        ReservationDataBase.add(newRow);
    }

    public ArrayList<Object> getReservationDataBase() {
        return ReservationDataBase;
    }


    public void setReservationDataBase(ArrayList<Object> reservationDataBase) {
        ReservationDataBase = reservationDataBase;
    };


    public Reservation getReservationFromReservationCode(String ReservationCode){
        for(int i = 0; i<ReservationDataBase.size(); i++) {
            if(((String)((ArrayList<Object>)ReservationDataBase.get(i)).get(0)).equals(ReservationCode)) {
                return (Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1);
            }
        }
        return null;
    }

    public int getReservationIndexFromReservationCode(String ReservationCode){
        for(int i = 0; i<ReservationDataBase.size(); i++) {
            if(((String)((ArrayList<Object>)ReservationDataBase.get(i)).get(0)).equals(ReservationCode)) {
                return i;
            }
        }
        return 0;
    }

    public void checkOut(String reservationCode) {
    	getReservationFromReservationCode(reservationCode).getRoom().setStatus(Room.RoomStatus.VACANT);
        ReservationDataBase.remove(getReservationIndexFromReservationCode(reservationCode));
        System.out.println("Successfully Checked Out! Reservation has been deleted");
    }
    
    public void removeReservation(String reservationCode) {
    	getReservationFromReservationCode(reservationCode).getRoom().setStatus(Room.RoomStatus.VACANT);
        ReservationDataBase.remove(getReservationIndexFromReservationCode(reservationCode));
        System.out.println("Successfully Removed Reservation! Reservation has been deleted");
    }


    public void displayAllReservations(){
        String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
        System.out.printf(format, "Reservation Code", "Check-In Date", "Check-Out Date", "# of Adults", "# of Children", "Room Type" );
        System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");

        for(int i = 0; i<ReservationDataBase.size(); i++) {
            System.out.printf(format, ((ArrayList<Object>)ReservationDataBase.get(i)).get(0), ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckInDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckOutDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getAdults(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getChildren(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getRoom().getRoomType());
        }
    }
    
    public void printReservationFromReservationCode(String ReservationCode) {
    	String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
        System.out.printf(format, "Reservation Code", "Check-In Date", "Check-Out Date", "# of Adults", "# of Children", "Room Type" );
        System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");
        for(int i = 0; i<ReservationDataBase.size(); i++) {
            if(((String)((ArrayList<Object>)ReservationDataBase.get(i)).get(0)).equals(ReservationCode)) {
            	System.out.printf(format, ((ArrayList<Object>)ReservationDataBase.get(i)).get(0), ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckInDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckOutDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getAdults(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getChildren(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getRoom().getRoomType());
            }
        }
    }


    public void displayGuest(String guestName) {

        String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
        boolean found = false;
        
        for(int i=0;i<ReservationDataBase.size();i++) {
        	if (((Reservation) ((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getName().equals(guestName)) {
        		System.out.printf(format, "Guest name", "Guest country", "Guest gender", "Guest nationality", "Guest address", "Address contact" );
                System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");
        		System.out.printf(format, (((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getName()), ((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getCountry(), ((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getGender(), ((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getNationality(), ((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getAddress(), ((Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1)).getGuest().getIc().getContact());
        		found = true;
        	}
        	
        }
        if (!found) System.out.println("Sorry, we cannot find the Guest Name in our Guest List!");
    }
    
    public void displayAllGuests() {

        ArrayList<Object> retrievDB = this.ReservationDataBase;

        String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
        System.out.printf(format, "Guest name:", "Guest country", "Guest gender", "Guest nationality", "Guest address", "Address contact" );
        System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");

        for(int i=0;i<retrievDB.size();i++) {

            ArrayList<Object> row = (ArrayList<Object>) retrievDB.get(i);

            System.out.printf(format, ( ((Reservation)row.get(1)).getGuest().getIc().getName()), ((Reservation)row.get(1)).getGuest().getIc().getCountry(),  ((Reservation)row.get(1)).getGuest().getIc().getGender(), ((Reservation)row.get(1)).getGuest().getIc().getNationality(), ((Reservation)row.get(1)).getGuest().getIc().getAddress(), ((Reservation)row.get(1)).getGuest().getIc().getContact());
        }
    }

    public boolean isValidCode(String ReservationCode){
        for(int i = 0; i<ReservationDataBase.size(); i++) {
            if(((String)((ArrayList<Object>)ReservationDataBase.get(i)).get(0)).equals(ReservationCode)) {
                return true;
            }
        }
        return false;
    }

}
