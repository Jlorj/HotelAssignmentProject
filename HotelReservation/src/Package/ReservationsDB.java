package Package;

import java.util.ArrayList;

public class ReservationsDB {
	private ArrayList<Object> ReservationDataBase = new ArrayList<Object>();
	
	ReservationsDB(){
		
	}
	
	public void appendRow(Guest guest, Reservation reservation) {
		ArrayList<Object> newRow = new ArrayList<Object>();
		newRow.add(guest);
		newRow.add(reservation);
		
		ReservationDataBase.add(newRow);
	}

	public ArrayList<Object> getReservationDataBase() {
		return ReservationDataBase;
	}
	

	public void setReservationDataBase(ArrayList<Object> reservationDataBase) {
		ReservationDataBase = reservationDataBase;
	};
	
//	public Guest getGuest(ArrayList<Object> list){
//		return (Guest)((ArrayList<Object>) list.get(0)).get(0);
//	}
	
	
}
