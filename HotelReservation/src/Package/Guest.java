package Package;

import java.util.Scanner;

public class Guest {
    private IdentityCard ic;
    private CreditCard cc = null;

    Guest(){
        Scanner sc = new Scanner(System.in);
        ic = new IdentityCard();
        System.out.println("Using credit card or cash?");
        String reply = sc.nextLine();
        // verify user input
        if (reply.equals("Credit Card")){
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
