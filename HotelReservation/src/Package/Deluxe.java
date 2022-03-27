package Assignment;

public class Deluxe extends Room{

    public Deluxe (String roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.MASTER;
        this.roomtype = RoomType.DELUXE;
        this.weekendRate = 200.0;
        this.weekdayRate = 150.0;
    }

}
