package Assignment

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public class RoomServiceDB {
    Hashtable<String, ArrayList<RoomService>> rsDB = new Hashtable<String, ArrayList<RoomService>>();
    RoomServiceDB(){};

    public Boolean append(String reservationCode, RoomService roomService){
        ArrayList<RoomService> temp;
        if (rsDB.containsKey(reservationCode)){
            temp = rsDB.get(reservationCode);
            temp.add(roomService);
        }
        else{
            temp = new ArrayList<>();
            temp.add(roomService);
            rsDB.put(reservationCode, temp);
        }
        return true;
    }

    public void displayDB(){
        rsDB.forEach((key, value)-> System.out.println(key + " = " + value));
    }

    public RoomService.RoomServiceStatus checkStatus(String reservationCode){
        if (!rsDB.containsKey(reservationCode)){
            return null;
        }
        ArrayList<RoomService> cell = rsDB.get(reservationCode);
        return cell.get(cell.size() - 1).getStatus();
    }
}
