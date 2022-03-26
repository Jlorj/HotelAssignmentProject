package Assignment;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Reservation implements Payment{
    private Room room;
    private Guest guest;
    private String checkInDate;
    private String checkOutDate;
    private int adults;
    private int children;
    private String code;
    private double payment;

    Reservation(Guest guest){
    	//Exception Handling for all the dates inputed
        this.guest = guest;
        Scanner sc = new Scanner(System.in);
        System.out.println("Input check in date (YYYY-MM-DD)");
        String date_checkin = sc.nextLine();
        this.checkInDate = Check_date.get_input(date_checkin);
        
        
        System.out.println("Input check out date (YYYY-MM-DD)");
        String date_checkout = sc.nextLine();
        this.checkOutDate = Check_date.get_input(date_checkout);
        
      //Exception Handling for all number of children and adult inputed
        System.out.println("Input number of adults");
        String num_adults = sc.nextLine();
        this.adults = Check_if_int.get_input(num_adults,"adults");
        
        System.out.println("Input number of children");
        String num_children = sc.nextLine();
        this.children = Check_if_int.get_input(num_children,"children");
    }

    public String getReservationCode() {
        return code;
    }

    public Room getRoom(){
        return room;
    }

    public Guest getGuest(){
        return guest;
    }

    public String getCheckInDate(){
        return checkInDate;
    }

    public String getCheckOutDate(){
        return checkOutDate;
    }

    public int getAdults(){
        return adults;
    }

    public int getChildren(){
        return children;
    }

    public String getCode(){
        return code;
    }

    public void generateCode(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789" +
                "abcdefghijklmnopqrs";
        StringBuilder sb = new StringBuilder(12);

        for (int i = 0; i < 6; i++){
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        this.code = sb.toString();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPayment() {
        // counting the number of weekdays and weekends and add the rates accordingly
        LocalDate parsedCheckInDate = LocalDate.parse(this.checkInDate);
        LocalDate parsedCheckOutDate = LocalDate.parse(this.checkOutDate);
        
        for (LocalDate date = parsedCheckInDate; date.isBefore(parsedCheckOutDate); date = date.plusDays(1)){
            if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
                    date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                this.payment += room.weekdayRate;
            }
            else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                this.payment += room.weekendRate;
            }
        }
        return this.payment;
    }
    
    public void printBill() {
    	DecimalFormat df = new DecimalFormat("0.00");
    	System.out.println("The subtotal bill for your hotel stay is: SGD" + df.format(this.payment));
    }
}
