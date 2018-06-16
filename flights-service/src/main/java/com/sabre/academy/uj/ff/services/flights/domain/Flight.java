package com.sabre.academy.uj.ff.services.flights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "User")
@Accessors(chain = true)
public class Flight {

    @Column(name = "Surname", length = 100, nullable = false)
    private String surname;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @Column(name = "Mail", length = 100, nullable = false)
    private String mail;

    @Column(name = "ClassType", length = 100, nullable = false)
    private String classType;

    @Column(name = "TripType", length = 100, nullable = false)
    private String tripType;

    @Column(name = "DepartureDate2", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate2;

    @Column(name = "DepartureAirportCode", length = 100, nullable = false)
    private String departureAirportCode;

    @Column(name = "ArrivalAirportCode", length = 100, nullable = false)
    private String arrivalAirportCode;

    @Column(name = "Carrier", length = 100, nullable = false)
    private String carrier;

    @Column(name = "Number", length = 100, nullable = false)
    private String number;

    @Column(name = "DepartureDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FlightID")
    private int id;



    public Flight() {}

    public Flight(String name, String surname, String mail,
                  String departureAirportCode, String arrivalAirportCode, String carrier,
                  String number, String classType, String tripType, LocalDate departureDate) {
        this.surname = surname;
        this.name = name;
        this.mail = mail;
        this.classType = classType;
        this.tripType = tripType;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.carrier = carrier;
        this.number = number;
        this.departureDate = departureDate;
    }
    public void setDepartureDate(String date) {
        this.departureDate = (LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
    public void setDepartureDate2(String date) {
        if(date == null || date.length() <=5){
            this.departureDate2 = null;
        }
        else
            this.departureDate2 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }

    public String getTripType() {
        return tripType;
    }
}
