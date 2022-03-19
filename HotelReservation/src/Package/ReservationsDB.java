package Package;

import java.util.ArrayList;

public class ReservationsDB {
	private ArrayList<Object> ReservationDataBase = new ArrayList<Object>();
	//Index		Variable
	//0			Reservation Code
	//1			Guest 
	//2			Reservation
	ReservationsDB(){
		
	}
	
	public void appendRow(int reservationCode, Guest guest, Reservation reservation) {
		ArrayList<Object> newRow = new ArrayList<Object>();
		newRow.add(reservationCode);
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
	
	public Guest getGuestFromReservationCode(int ReservationCode){
		for(int i = 0; i<ReservationDataBase.size(); i++) {
			if((int)((ArrayList<Object>)ReservationDataBase.get(i)).get(0) == ReservationCode) {
				return (Guest)((ArrayList<Object>) ReservationDataBase.get(i)).get(1);
			}
		}
		return null;	
	}
	
	public Reservation getReservationFromReservationCode(int ReservationCode){
		for(int i = 0; i<ReservationDataBase.size(); i++) {
			if((int)((ArrayList<Object>)ReservationDataBase.get(i)).get(0) == ReservationCode) {
				return (Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(2);
			}
		}
		return null;
	}
}
