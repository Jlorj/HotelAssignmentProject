package Assignment;

public abstract class Room {

    public enum BedType{
        SINGLE,DOUBLE,MASTER;
    }

    public enum RoomType{
        SINGLE, DOUBLE, DELUXE, VIPSUITE;
    }

    public enum RoomStatus{
        VACANT,OCCUPIED,RESERVED,MAINTENANCE;
    }

    protected BedType bedtype;
    protected RoomType roomtype;
    protected RoomStatus status = RoomStatus.VACANT;
    protected double weekendRate;
    protected double weekdayRate;
    private int roomNum;
    private Boolean wifi;
    private Boolean smoking;
    private Boolean view;


    public Room(int roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        this.roomNum = roomNum;
        this.wifi = wifi;
        this.smoking = smoking;
        this.view = view;
    }

    public RoomStatus checkAvail() {
        return this.status;
    }

    public int getRoomNum(){
        return roomNum;
    }

    public RoomType getRoomType(){
        return roomtype;
    }

    public Boolean getWifi(){
        return wifi;
    }

    public Boolean getSmoking(){
        return smoking;
    }

    public Boolean getView(){
        return view;
    }

    public void setStatus(RoomStatus rs){
        status = rs;
    }

    public RoomStatus getStatus(){
        return status;
    }

}

