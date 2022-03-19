package Assignment

public class Rooms {
    private Room[] rooms;
    int numRooms = 48;
    Rooms(){
        rooms = new Room[48];
    }

    public void addRoom(int roomNum, String roomType, Boolean wifi, Boolean smoking, Boolean view){
        switch(roomType){
            case "SingleRoom":
                rooms[roomNum - 1] = new SingleRoom(roomNum, wifi, smoking, view);
                break;
            case "DoubleRoom":
                rooms[roomNum - 1] = new DoubleRoom(roomNum, wifi, smoking, view);
                break;
            case "Deluxe":
                rooms[roomNum - 1] = new Deluxe(roomNum, wifi, smoking, view);
                break;
            case "VIPSuite":
                rooms[roomNum - 1] = new VIPSuite(roomNum, wifi, smoking, view);
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
}
