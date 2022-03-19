package Package;

import java.util.Scanner;

public class IdentityCard {
    private String name;
    private String country;
    private String gender;
    private String nationality;
    private String address;
    private String contact;

    IdentityCard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input name");
        this.name = sc.nextLine();
        System.out.println("Input country");
        this.country = sc.nextLine();
        System.out.println("Input gender");
        this.gender = sc.nextLine();
        System.out.println("Input nationality");
        this.nationality = sc.nextLine();
        System.out.println("Input address");
        this.address = sc.nextLine();
        System.out.println("Input contact");
        this.contact = sc.nextLine();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getNationality(){
        return nationality;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getContact(){
        return contact;
    }

    public void setContact(String contact){
        this.contact = contact;
    }


}

