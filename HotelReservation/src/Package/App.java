package Package;

import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		
		ReservationsDB DataBase = new ReservationsDB();
		Guest myGuest = new Guest();
		Reservation myReservation = new Reservation(myGuest);
		
		DataBase.appendRow(myGuest, myReservation);
		
		Guest myGuest2 = new Guest();
		Reservation myReservation2 = new Reservation(myGuest);
		
		DataBase.appendRow(myGuest2, myReservation2);
		
		System.out.println(DataBase.getReservationDataBase());
		
		
		ArrayList<Object> thisRow = (ArrayList<Object>) DataBase.getReservationDataBase().get(0); 
		Guest mimicGuest = (Guest) thisRow.get(0);
		System.out.println("GUEST NAME IS " + mimicGuest.getIc().getName());
		
		
	}

}
