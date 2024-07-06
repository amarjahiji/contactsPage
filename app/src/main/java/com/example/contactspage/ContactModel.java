package com.example.contactspage;

import androidx.annotation.NonNull;

public class ContactModel {
    private String firstname;
    private String lastname;
    private String Number;
    private String Email;

    //constructor
    public ContactModel(String firstname, String lastname,String Number, String Email){
        this.firstname = firstname;
        this.lastname=lastname;
        this.Number=Number;
        this.Email=Email;
    }


    //empty constructor because apparently android rewuires it
    public ContactModel() {
    }

    @NonNull
    public String toString(){
        return "ContactModel{"+
                "firstname=" + firstname +
                ", lastname='" + lastname + '\''+
                ", Number=" + Number +
                ", Email=" + Email +
                '}';
    }


    //setters getters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
