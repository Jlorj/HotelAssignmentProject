package Assignment;

public class SingleRoom extends Room{

    public SingleRoom(String roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.SINGLE;
        this.roomtype = RoomType.SINGLE;
        this.weekendRate = 100.0;
        this.weekdayRate = 50.0;
    }
}
