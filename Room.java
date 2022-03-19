package Assignment;

public abstract class Room {

	public enum BedType{
		SINGLE,DOUBLE,MASTER;
	}
	
	public enum Status{
		VACANT,OCCUPIED,RESERVED,MAINTENANCE;
	}
	
	protected BedType bedtype;
	protected Status status;
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
		this.status = Status.VACANT;
		
	}
	
	public Status checkAvail() {
		return this.status;
	}
	
}
