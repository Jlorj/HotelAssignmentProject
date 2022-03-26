package Assignment;

import java.util.Scanner;

public class Guest {
    private IdentityCard ic;
    private CreditCard cc = null;

    Guest(){
        Scanner sc = new Scanner(System.in);
        this.ic = new IdentityCard();
        System.out.println("Using Credit Card or Cash?");
        String reply = sc.nextLine();
        // verify user input
        if (reply.equals("Credit Card") || reply.equals("credit card")){
            cc = new CreditCard();
        }
    }

    public IdentityCard getIc(){
        return this.ic;
    }

    public CreditCard getCC(){
        return cc;
    }

}
