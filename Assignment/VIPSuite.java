package Assignment;

public class VIPSuite extends Room{

    public VIPSuite (String roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.MASTER;
        this.roomtype = RoomType.VIPSUITE;
        this.weekendRate = 250.0;
        this.weekdayRate = 200.0;
    }
}
