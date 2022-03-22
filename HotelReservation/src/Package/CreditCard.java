
import java.util.Scanner;

public class CreditCard {
    private String cardHolderName;
    private String creditCardNumber;
    private String expirationDate;

    CreditCard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input card holder name");
        this.cardHolderName = sc.nextLine();
        System.out.println("Input credit card number");
        this.creditCardNumber = sc.nextLine();
        System.out.println("Input expiration date");
        this.expirationDate = sc.nextLine();
    }

    public String getCardHolderName(){
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName){
        this.cardHolderName = cardHolderName;
    }

    public String getCreditCardNumber(){
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber){
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate){
        this.expirationDate = expirationDate;
    }
}
