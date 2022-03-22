package Package;

import java.util.ArrayList;

public class ReservationsDB {
	private ArrayList<Object> ReservationDataBase = new ArrayList<Object>();
	//Index		Variable
	//0			Reservation Code
	//1			Guest 
	//2			Reservation
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
	
	
	public Reservation getReservationFromReservationCode(int ReservationCode){
		for(int i = 0; i<ReservationDataBase.size(); i++) {
			if(((String)((ArrayList<Object>)ReservationDataBase.get(i)).get(0)).equals(ReservationCode)) {
				return (Reservation)((ArrayList<Object>) ReservationDataBase.get(i)).get(1);
			}
		}
		return null;
	}
}
