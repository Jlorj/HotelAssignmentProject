package Assignment;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Serializable{

    private int numFood;
    private Food food;
    ArrayList<Food> foods = new ArrayList<Food>();

    public void printMenuItems() {
        String format = "%-20s%-20s%-20s%n";
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.printf(format, "Name", "Preparation", "Price($)");
        System.out.printf(format, "================", "==================", "==================");
        for (int i =0; i<foods.size();i++) {
            System.out.printf(format, foods.get(i).getName(), foods.get(i).getPrepMethod(), df.format(foods.get(i).getPrice()));
        }
    }

    public boolean addFood(Food food) {
        for (int i =0; i<foods.size();i++) {
            if (foods.get(i).getName().equals(food.getName()))
                return false;
        }
        foods.add(food);
        return true;
    }

    public boolean removeFood(String name){
        for (int i = 0; i < foods.size(); i++){
            if (foods.get(i).getName().equals(name)){
                foods.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean updateMenu(String name) {
    	Scanner sc = new Scanner(System.in);
    	
    	for (int i =0; i<foods.size();i++) {
	    	if (foods.get(i).getName().equals(name)) {
	    		boolean update = true;
	    		while(update) {
	    		System.out.println("What would you like to update?");
	        	System.out.println("(1) Name");
	        	System.out.println("(2) Preparation");
	        	System.out.println("(3) Price($)");
	        	System.out.println("(4) Exit");
	        	int option = sc.nextInt();
	        	sc.nextLine();
		        	switch(option) {
		    	    	case 1:
		    	    		System.out.println("Enter New Name of food");
		    	    		String newName = sc.nextLine();
		    	    		foods.get(i).setName(newName);
		    	    		break;
		    	    	case 2:
		    	    		System.out.println("Enter New Preparation Method of food");
		    	    		String prep = sc.nextLine();
		    	    		foods.get(i).setPrepMethod(prep);
		    	    		break;
		    	    	case 3:
		    	    		System.out.println("Enter New Price of food");
		    	    		double price = sc.nextDouble();
		    	    		foods.get(i).setPrice(price);
		    	    		break;
		    	    	case 4:
		    	    		update = false;
		    	    		break;
		        	}
	        	}
	        	return true;
	    	}
    	}
    	
    	return false;
    }
}
