package Assignment;

import java.util.ArrayList;

public class Menu{

	private int numFood;
	private Food food;
	ArrayList<Food> foods = new ArrayList<Food>();
	
	public void printMenuItems() {
		System.out.println("List of Menu Items");
		for (int i =0; i<foods.size();i++) {
			System.out.println(foods.get(i).getName());
			System.out.println(foods.get(i).getPrepMethod());
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
	
}
