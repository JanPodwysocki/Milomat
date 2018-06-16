package com.sabre.academy.uj.ff.services.flights.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "User")
public class User{

    @Column(name = "FirstName", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 100, nullable = false)
    private String lastName;

    @Column(name = "Password", length = 100, nullable = false)
    private String password;

    @Id
    @Column(name = "Email", length = 100, nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "Miles", nullable = false)
    private double miles;

    @Column(name = "Status", length = 100, nullable = false, unique = true)
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