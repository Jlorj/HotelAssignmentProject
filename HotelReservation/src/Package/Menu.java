package Assignment;


import java.util.ArrayList;

public class Menu{

    private int numFood;
    private Food food;
    ArrayList<Food> foods = new ArrayList<Food>();

    public void printMenuItems() {
        String format = "%-20s%-20s%-20s%n";
        System.out.printf(format, "Name", "Preparation", "Price($)");
        System.out.printf(format, "================", "==================", "==================");
        for (int i =0; i<foods.size();i++) {
            System.out.printf(format, foods.get(i).getName(), foods.get(i).getPrepMethod(), foods.get(i).getPrice());
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
}
