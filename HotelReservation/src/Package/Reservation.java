package Assignment

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Reservation {
    private Room room;
    private Guest guest;
    private String checkInDate;
    private String checkOutDate;
    private int adults;
    private int children;
    private String code;


    Reservation(Guest guest){

        this.guest = guest;
        Scanner sc = new Scanner(System.in);
        System.out.println("Input check in date");
        this.checkInDate = sc.nextLine();
        System.out.println("Input check out date");
        this.checkOutDate = sc.nextLine();
        System.out.println("Input number of adults");
        this.adults = sc.nextInt();
        System.out.println("Input number of children");
        this.children = sc.nextInt();
        sc.nextLine();
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy");
        LocalDate parsedCheckInDate = LocalDate.parse(this.checkInDate);
        LocalDate parsedCheckOutDate = LocalDate.parse(this.checkOutDate);
        if (room.getRoomType().equals(Room.RoomType.SINGLE)){
            for (LocalDate date = parsedCheckInDate; date.isBefore(parsedCheckOutDate); date = date.plusDays(1)){
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
                        date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    this.payment += room.weekdayRate;
                }
                else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    this.payment += room.weekendRate;
                }
            }
        }

        else if (room.getRoomType().equals(Room.RoomType.DOUBLE)){
            for (LocalDate date = parsedCheckInDate; date.isBefore(parsedCheckOutDate); date = date.plusDays(1)){
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
                        date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    this.payment += room.weekdayRate;
                }
                else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    this.payment += room.weekendRate;
                }
            }
        }

        else if (room.getRoomType().equals(Room.RoomType.DELUXE)){
            for (LocalDate date = parsedCheckInDate; date.isBefore(parsedCheckOutDate); date = date.plusDays(1)){
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
                        date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    this.payment += room.weekdayRate;
                }
                else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    this.payment += room.weekendRate;
                }
            }
        }

        else if (room.getRoomType().equals(Room.RoomType.VIPSUITE)){
            for (LocalDate date = parsedCheckInDate; date.isBefore(parsedCheckOutDate); date = date.plusDays(1)){
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
                        date.getDayOfWeek() == DayOfWeek.THURSDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    this.payment += room.weekdayRate;
                }
                else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    this.payment += room.weekendRate;
                }
            }
        }
        return this.payment;
    }

}
