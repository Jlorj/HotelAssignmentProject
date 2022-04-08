package Assignment;

public interface Payment {
	
	final double GST = 0.07;
	final String stringGST = "7%";
	final double serviceTax = 0.1;
	final String stringServiceTax = "10%";
	final double totalTax = GST + serviceTax;
	
	public abstract double getPayment();
	public abstract void printBill();
}
