package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Component
public class MilomatFacade {
    @Resource(name="FlightAddCSV")
    private FlightAddStrategy flightAddCSV;

    @Resource(name="FlightsAddUserInput")
    private FlightAddStrategy flightAddUserInput;

    @Autowired
    UsersSource usersSource;

    @Autowired
    FlightsSource flightsSource;

    public String addFlightCSV(String pathCSV){
        return flightAddCSV.addFlight(pathCSV);
    }

    /**
     * This method takes user's flight input and passes it to the strategy.
     * @param flight
     * @return
     */
    public String addFlightUserInput(Flight flight){
        return flightAddUserInput.addFlight(flight);
    }

    /**
     * This method retrieve's user's flights based on his email address.
     * @param email
     * @return
     */
    public List<Flight> getTravelersFlight(String email){
        return flightsSource.retrieveFlights(email);
    }

    /**
     * This method returns a list of users.
     * @return
     */
    public List<User> getAllUsers() {
        return usersSource.retrieveAllUsers();
    }

    public boolean testUser(User user){
        return usersSource.testUser(user);
    }

    /**
     * This method retrieves User object based on his email address.
     * @param travelerMail
     * @return
     */
    public User getUserInfo( String travelerMail) {
        return usersSource.getUserInfo(travelerMail);
    }

    /**
     * This method registers a user, returns a String with the feedback information.
     * @param user
     * @return
     */
    public String register(User user){
        return usersSource.registerUser(user);
    }

    @PostConstruct
    public  void init(){
        addFlightCSV("/Users/jan/IdeaProjects/sabre_Coookiez_juanwojtek/flights-service/src/main/resources/usersWithFlightsSHORTER.csv");
        /*
            JANEK: "/Users/jan/IdeaProjects/sabre_Coookiez_juanwojtek/flights-service/src/main/resources/usersWithFlights.csv"
            KUBA: "C:\\sabre_Coookiez_juanwojtek\\flights-service\\src\\main\\resources\\usersWithFlights.csv"
            KUBA: "E:\\sabre_Coookiez_juanwojtek\\flights-service\\src\\main\\resources\\usersWithFlights.csv"
            JANEK: "/Users/jan/IdeaProjects/sabre_Coookiez_juanwojtek/flights-service/src/main/resources/usersWithFlightsSHORTER.csv"
            KUBA: "E:\\sabre_Coookiez_juanwojtek\\flights-service\\src\\main\\resources\\usersWithFlightsSHORTER.csv"
        */
    }
}