package Assignment;


import java.util.Scanner;

public class RoomService implements Payment{

    public enum RoomServiceStatus{
        CONFIRMED,
        PREPARING,
        DELIVERED,
    }

    protected RoomServiceStatus roomServiceStatus;
    private String dateTime;
    private String remarks = null;
    private double totalAmount = 0.0;
    private Menu menu;
    private double payment;

    public RoomService(String dateTime, Menu menu) {
        this.dateTime = dateTime;
        this.menu = menu;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean order(String foodName) {
        for (int i=0; i<menu.foods.size();i++) {
            if (foodName.equals(menu.foods.get(i).getName())) {
                totalAmount += menu.foods.get(i).getPrice();
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

}
