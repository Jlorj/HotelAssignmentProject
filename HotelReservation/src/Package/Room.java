package Assignment;

import java.io.Serializable;

public class Room implements Serializable{

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
    private String roomNum;
    private Boolean wifi;
    private Boolean smoking;
    private Boolean view;


    public Room(String roomNum, Boolean wifi, Boolean smoking, Boolean view) {

        this.roomNum = roomNum;
        this.wifi = wifi;
        this.smoking = smoking;
        this.view = view;
    }

    public RoomStatus checkAvail() {
        return this.status;
    }

    public String getRoomNum(){
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
    
    public void setWeekdayRate(double weekdayRate) {
    	this.weekdayRate  = weekdayRate;
    }
    
    public void setWeekendRate(double weekendRate) {
    	this.weekendRate  = weekendRate;
    }

}
