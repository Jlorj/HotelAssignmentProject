package Assignment;

import Assignment.Room.BedType;
import Assignment.Room.Status;

public class VIPSuite extends Room{
	
public VIPSuite (int roomNum, Boolean wifi, Boolean smoking, Boolean view) {
		
		super(roomNum,wifi,smoking,view);
		this.bedtype = BedType.MASTER;
		this.weekendRate = 100.0;
		this.weekdayRate = 50.0;
		this.status = Status.OCCUPIED;
		
		
	}

}
