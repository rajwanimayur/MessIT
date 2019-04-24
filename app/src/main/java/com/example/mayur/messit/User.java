package com.example.mayur.messit;

public class User {
    public String email;
    public String name;
    public String contact;
    public String rollNo;
    public String foodPreference;


    public User() {
        //Default Constructor for Firebase Reference
    }

    public User(String email, String name, String contact, String rollNo, String foodPreference) {
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.rollNo = rollNo;
        this.foodPreference = foodPreference;
    }

}
