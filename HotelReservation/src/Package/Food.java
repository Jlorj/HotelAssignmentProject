package Assignment;

public class Food {
	
	private String name;
	private String prepMethod;
	private double price;
	
	public Food(String name, String prepMethod, double price) {
		this.name = name;
		this.prepMethod = prepMethod;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getPrepMethod() {
		return this.prepMethod;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrepMethod(String prepMethod) {
		this.prepMethod = prepMethod;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
