package com.example.contactspage;

import androidx.annotation.NonNull;

public class ContactModel {
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String Email;

    //constructor
    public ContactModel(String firstname, String lastname,String phonenumber, String Email){
        this.firstname = firstname;
        this.lastname=lastname;
        this.phonenumber=phonenumber;
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
                ", phonenumber=" + phonenumber +
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
