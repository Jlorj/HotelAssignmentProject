package Assignment;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String remarks = null;
    private Menu menu;
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

    public boolean order(String foodName) {
        for (int i=0; i<menu.foods.size();i++) {
            if (foodName.equals(menu.foods.get(i).getName())) {
            	this.date = LocalDate.now();
            	this.time = LocalTime.now();
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
    	String format = "%-20s%-20s%-20s%n";
    	DecimalFormat df = new DecimalFormat("0.00");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kk:mm:ss");
        System.out.printf(format, this.date, this.time.format(formatter), "SGD" + df.format(this.payment), this.remarks);
    }

}
