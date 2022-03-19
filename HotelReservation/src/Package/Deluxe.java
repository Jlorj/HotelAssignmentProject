package Assignment

public class Deluxe extends Room{

    public Deluxe (int roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.MASTER;
        this.roomtype = RoomType.DELUXE;
        this.weekendRate = 100.0;
        this.weekdayRate = 50.0;
        this.status = RoomStatus.OCCUPIED;


    }

}
