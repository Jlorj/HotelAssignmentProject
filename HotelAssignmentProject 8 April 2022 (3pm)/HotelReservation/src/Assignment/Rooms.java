package Assignment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Rooms implements Serializable {
    protected Room[] rooms;
    
    int numRooms = 48;

    Rooms(){
        rooms = new Room[48];
    }

    public void addRoom(int num, String roomNum, String roomType, Boolean wifi, Boolean smoking, Boolean view){
        switch(roomType){
            case "SingleRoom":
                rooms[num - 1] = new SingleRoom(roomNum, wifi, smoking, view);
                break;
            case "DoubleRoom":
                rooms[num - 1] = new DoubleRoom(roomNum, wifi, smoking, view);
                break;
                
            case "Deluxe":
                rooms[num - 1] = new Deluxe(roomNum, wifi, smoking, view);
                break;
            case "VIPSuite":
                rooms[num - 1] = new VIPSuite(roomNum, wifi, smoking, view);
                break;

        }
    }

    public void displayRooms(){
        System.out.println("Room Number, Room Type, Wifi, Smoking, View");
        for (int i = 0; i < 48; i++){
            System.out.printf("%d, %s, %b, %b, %b", rooms[i].getRoomNum(), rooms[i].getRoomType(), rooms[i].getWifi(), rooms[i].getSmoking(), rooms[i].getView());
            System.out.println();
        }
    }

    public Room getRoom(int roomNum){
        return rooms[roomNum - 1];
    }

    public ArrayList<String> printVacantRoomsByType(Room.RoomType rt){
        ArrayList<String> roomNums = new ArrayList<String>();
        double totalVacant = 0;
        double total = 0;
        for (int i = 0; i < 48; i++){
            if (rooms[i].getRoomType() == rt){
                total += 1;
                if (rooms[i].getStatus() == Room.RoomStatus.valueOf("VACANT")){
                    roomNums.add(formatRoomNum(i));
                    totalVacant += 1;
                }
            }
        }
        double vacancyRate = totalVacant/total;
        System.out.printf("| %s Room | ", rt.toString());
        for (int i = 0; i < roomNums.size(); i++){
            System.out.printf("%s ", roomNums.get(i));
        }
        System.out.printf("| Vacancy Rate | %.2f |\n", vacancyRate);
        return roomNums;
    }

    public ArrayList<String> printVacantRoomWithInfo(Room.RoomType rt){
        ArrayList<String> toReturn = printVacantRoomsByType(rt);
        System.out.println();
        String format = "%-20s%-20s%-20s%-20s%n";
        System.out.printf(format, "Room Number", "WIFI", "Smoking", "View");
        System.out.printf(format, "================", "==================", "==================", "==================");
        Room room;
        for (int i = 0; i < toReturn.size(); i++){
            room = rooms[deformatRoomNum(toReturn.get(i)) - 1];
            System.out.printf(format, toReturn.get(i), room.getWifi(),  room.getSmoking(),  room.getView());
        }
        return toReturn;
    }

    public void printVacantRooms(){
        printVacantRoomsByType(Room.RoomType.valueOf("SINGLE"));
        printVacantRoomsByType(Room.RoomType.valueOf("DOUBLE"));
        printVacantRoomsByType(Room.RoomType.valueOf("DELUXE"));
        printVacantRoomsByType(Room.RoomType.valueOf("VIPSUITE"));
    }

    private void printRoomsByStatusSub(Room.RoomStatus rs){
        ArrayList<String> roomNums = new ArrayList<String>();
        for (int i = 0; i < 48; i++){
            if (rooms[i].getStatus() == rs){
                roomNums.add(formatRoomNum(i));
            }
        }
        System.out.printf("| %s |", rs.toString());
        if (roomNums.size() == 0){
            System.out.println();
            return;
        }
        for (int i = 0; i < roomNums.size(); i++){
            System.out.printf("%s ", roomNums.get(i));
        }
        System.out.println("|");
    }

    public void printRoomsByStatusMain(){
        printRoomsByStatusSub(Room.RoomStatus.valueOf("VACANT"));
        printRoomsByStatusSub(Room.RoomStatus.valueOf("RESERVED"));
        printRoomsByStatusSub(Room.RoomStatus.valueOf("OCCUPIED"));
        printRoomsByStatusSub(Room.RoomStatus.valueOf("MAINTENANCE"));
    }

    public String formatRoomNum(int roomNum){
        return "0" + Integer.toString(roomNum/8 + 1) + "0" + Integer.toString(roomNum%8 + 1);
    }

    public int deformatRoomNum(String roomNum){
        return ((Character.getNumericValue(roomNum.charAt(1)) - 1) * 8) + (Character.getNumericValue(roomNum.charAt(3)));
    }

}
