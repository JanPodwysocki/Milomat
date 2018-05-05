package com.sabre.academy.uj.ff.services.flights.domain;

import lombok.Data;

@Data
public class User{

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private double miles;
    private String status;

    /**
     * This method sets the status of the user based on the number of miles.
     */
    public void upDateStatus(){
        if(miles >= 120000)
            this.status="Golden";
        else if(miles >= 60000)
            this.status="Silver";
        else
            this.status="Bronze";
    }

    public User(String firstName, String lastName, String password, String email, double miles, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.miles = miles;
        this.status = status;
    }
    public User(){}
}