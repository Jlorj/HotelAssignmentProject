package Package;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter; 

public class RoomService implements Payment{

    public enum RoomServiceStatus{
        CONFIRMED,
        PREPARING,
        DELIVERED,
    }

    protected RoomServiceStatus roomServiceStatus;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime orderDateandTime;
    private LocalDateTime pendingDateandTime;
    private LocalDateTime deliveredDateandTime;
    private String remarks = null;
    private Menu menu;
    private String name;
    private double payment;

    public RoomService(Menu menu) {
        this.menu = menu;
    }

	public void setDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean order(String foodName, Date dt) {
    	System.out.println("The time is " + dt);
    	
    	// Set Confirmation Time at Current Time
    	this.orderDateandTime = dt.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    	
    	// Set Pending Time at 15 Minutes After Confirmation Time
    	Calendar c = Calendar.getInstance();
    	c.setTime(dt);
        c.add(Calendar.MINUTE, 15);
        dt = c.getTime();
        this.pendingDateandTime = dt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        
        // Set Delivery Time at 60 Minutes After Confirmation Time
    	c = Calendar.getInstance();
    	c.setTime(dt);
        c.add(Calendar.MINUTE, 45);
        dt = c.getTime();
        this.deliveredDateandTime = dt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    	
    	System.out.println("The confirmation Date and Time is " + this.orderDateandTime);
    	System.out.println("The pending Date and Time is " + this.pendingDateandTime);
    	System.out.println("The delivered Date and Time is " + this.deliveredDateandTime);
    	
    	
        for (int i=0; i<menu.foods.size();i++) {
            if (foodName.equals(menu.foods.get(i).getName())) {
            	this.date = LocalDate.now();
            	this.time = LocalTime.now();
            	this.name = foodName;
                this.payment = menu.foods.get(i).getPrice();
                this.roomServiceStatus = RoomServiceStatus.CONFIRMED;
                return true;
            }
        }
        return false;
    }

    public RoomServiceStatus getStatus(){
        return roomServiceStatus;
    }
    // include dateTime for RoomServiceStatus
    
    public double getPayment() {
    	return this.payment;
    }
    
    public void printBill() {
    	String format = "%-20s%-20s%-20s%-20s%-20s%n";
    	DecimalFormat df = new DecimalFormat("0.00");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kk:mm:ss");
        System.out.printf(format, this.date, this.name, this.time.format(formatter), "SGD" + df.format(this.payment), this.remarks);
    }

	public LocalDateTime getOrderDateandTime() {
		return orderDateandTime;
	}

	public LocalDateTime getPendingDateandTime() {
		return pendingDateandTime;
	}

	public LocalDateTime getDeliveredDateandTime() {
		return deliveredDateandTime;
	}

	public void setRoomServiceStatus(RoomServiceStatus roomServiceStatus) {
		this.roomServiceStatus = roomServiceStatus;
	}
    
	

}
