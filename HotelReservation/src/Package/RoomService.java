package Assignment;

import java.util.Scanner;

public class RoomService {
	
	public enum RoomServiceStatus{ 
		CONFIRMED,
		PREPARING,
		DELIVERED,
	}
	
	protected RoomServiceStatus roomServiceStatus;
	private String dateTime;
	private String remarks;
	private double totalAmount = 0.0;
	private Menu menu;
	
	public RoomService(String dateTime, String remarks, Menu menu) {
		this.dateTime = dateTime;
		this.remarks = remarks;
		this.menu = menu;
	}
	
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void order() {
		menu.printMenuItems();
		boolean order = true;
		Scanner sc = new Scanner(System.in);
		
		while (order) {
			String choice = sc.nextLine();
			for (int i=0; i<menu.foods.size();i++) {
				if (choice.equals(menu.foods.get(i).getName())) {
					System.out.println("You have ordered " + choice + " with " + this.remarks);
					totalAmount += menu.foods.get(i).getPrice();
					this.roomServiceStatus = RoomServiceStatus.CONFIRMED;
				}
			}
		}
	}
	// include dateTime for RoomServiceStatus
	
}
