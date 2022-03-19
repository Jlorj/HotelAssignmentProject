package Package;

import java.util.ArrayList;
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
            rooms.displayRooms();
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
