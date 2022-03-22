package Assignment;

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
		ReservationDataBase.remove(getReservationIndexFromReservationCode(reservationCode));
		System.out.println("Successfully Checked Out! Reservation has been deleted");
	}
	
	
	public void displayAllReservations(){
		String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
		System.out.printf(format, "Reservation Code", "Check-In Date", "Check-Out Date", "# of Adults", "# of Children", "Room Type" );
		System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");

		for(int i = 0; i<ReservationDataBase.size(); i++) {
			System.out.printf(format, ((ArrayList<Object>)ReservationDataBase.get(i)).get(0), ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckInDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getCheckOutDate(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getAdults(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getChildren(),  ((Reservation)((ArrayList<Object>)ReservationDataBase.get(i)).get(1)).getRoomtype());
		}
	}
	
	
	public void DisplayGuest() {
		
		ArrayList<Object> retrievDB = this.ReservationDataBase;
		
		String format = "%-20s%-20s%-20s%-20s%-20s%-20s%n";
		System.out.printf(format, "Guest name:", "Guest country", "Guest gender", "Guest nationality", "Guest address", "Address contact" );
		System.out.printf(format, "================", "==================", "==================", "==================", "==================",  "==================");
		
		for(int i=0;i<retrievDB.size();i++) {
			
			ArrayList<Object> row = (ArrayList<Object>) retrievDB.get(i);
			
			System.out.printf(format, ( ((Reservation)row.get(1)).getGuest().getIc().getName()), ((Reservation)row.get(1)).getGuest().getIc().getCountry(),  ((Reservation)row.get(1)).getGuest().getIc().getGender(), ((Reservation)row.get(1)).getGuest().getIc().getNationality(),   ((Reservation)row.get(1)).getGuest().getIc().getAddress(), ((Reservation)row.get(1)).getGuest().getIc().getContact()  );
			
			
			
		}
	}
	
}
