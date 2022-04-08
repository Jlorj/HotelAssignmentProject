package Assignment;

public class DoubleRoom extends Room{


    public DoubleRoom (String roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.DOUBLE;
        this.roomtype = RoomType.DOUBLE;
        this.weekendRate = 150.0;
        this.weekdayRate = 100.0;

    }
}
