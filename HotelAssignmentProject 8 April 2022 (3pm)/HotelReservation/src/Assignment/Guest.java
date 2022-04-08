package Assignment;

import java.io.Serializable;
import java.util.Scanner;

public class Guest implements Serializable{
    private IdentityCard ic;
    private CreditCard cc = null;

    Guest(){
        Scanner sc = new Scanner(System.in);
        this.ic = new IdentityCard();
    }

    public IdentityCard getIc(){
        return this.ic;
    }

    public CreditCard getCC(){
        return cc;
    }

}
