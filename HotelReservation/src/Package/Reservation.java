package Package;

import java.util.Scanner;

public class Reservation {


    public enum RoomType{
        SINGLEROOM,DOUBLEROOM,DELUXE, VIPSUITE;
    }

    private RoomType roomtype;
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
        //BUFFER
        sc.nextLine();
        System.out.println("Input Room Type:");
        String roomInput = sc.nextLine().toUpperCase();
        switch (roomInput) {
            case "SINGLE ROOM":
                this.roomtype = RoomType.SINGLEROOM;
                break;
            case "DOUBLE ROOM":
                this.roomtype = RoomType.DOUBLEROOM;
                break;
            case "DELUXE":
                this.roomtype = RoomType.DELUXE;
                break;
            case "VIP SUITE":
                this.roomtype = RoomType.VIPSUITE;
                break;
            default:
                System.out.println("Please enter a valid room type!");
        }

        generateCode();

        // input for room, check for validity

    }

    public RoomType getRoomtype(){
        return roomtype;
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
    
    private void generateCode(){
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
    
}
