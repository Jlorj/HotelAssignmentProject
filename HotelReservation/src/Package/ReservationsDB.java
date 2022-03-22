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
	
	public void DisplayGuest() {
		
		ArrayList<Object> retrievDB = this.ReservationDataBase;
		
		for(int i=0;i<retrievDB.size();i++) {
			
			ArrayList<Object> row = (ArrayList<Object>) retrievDB.get(i);
			
			System.out.println("Guest name:  " + ((Reservation)row.get(1)).getGuest().getIc().getName());
//			
			System.out.println("Guest country:  " + ((Reservation)row.get(1)).getGuest().getIc().getCountry());
			
			System.out.println("Guest gender:  " + ((Reservation)row.get(1)).getGuest().getIc().getGender());
			
			System.out.println("Guest nationality:  " + ((Reservation)row.get(1)).getGuest().getIc().getNationality());
			
			System.out.println("Guest address:  " + ((Reservation)row.get(1)).getGuest().getIc().getAddress());
			
			System.out.println("Guest contact:  " + ((Reservation)row.get(1)).getGuest().getIc().getContact());
			
		}
	}
	

}
