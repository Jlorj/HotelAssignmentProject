package Package;


public class SingleRoom extends Room{

    public SingleRoom(int roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.SINGLE;
        this.roomtype = RoomType.SINGLE;
        this.weekendRate = 100.0;
        this.weekdayRate = 50.0;
        this.status = RoomStatus.OCCUPIED;

    }
}
