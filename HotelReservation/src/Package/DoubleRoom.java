package Assignment

public class DoubleRoom extends Room{


    public DoubleRoom (int roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        super(roomNum,wifi,smoking,view);
        this.bedtype = BedType.DOUBLE;
        this.roomtype = RoomType.DOUBLE;
        this.weekendRate = 100.0;
        this.weekdayRate = 50.0;


    }
}
