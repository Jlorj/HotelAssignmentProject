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
        String roomInput = sc.nextLine();
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
 
        
        // input for room, check for validity

    }
   
    
    
}
