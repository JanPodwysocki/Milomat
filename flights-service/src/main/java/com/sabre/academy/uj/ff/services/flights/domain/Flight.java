package com.sabre.academy.uj.ff.services.flights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Accessors(chain = true)
public class Flight {

    private String surname;
    private String name;
    private String mail;
    private String classType;
    private String tripType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate2;
    //
    private String departureAirportCode;
    private String arrivalAirportCode;
    private String carrier;
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

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
