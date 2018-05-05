package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "FlightsAddUserInput")
public class FlightAddUserInput implements FlightAddStrategy{
    @Autowired
    FlightsSource flightSource;
    @Autowired
    UsersSource usersSource;

    /**
     * This method takes user's flight input and passes it to the strategy.
     * @param toAddObject
     * @return
     */
    @Override
    public String addFlight(Object toAddObject) {
        Flight flight = (Flight)toAddObject;
        //System.out.println(flight.toString());
        //System.out.println("!!" + flightSource.retrieveFlights(travelerMail).get(0).toString());
        String result;
        Optional<User> user = usersSource.checkIfExist(flight.getMail());
        if(user.isPresent()){
            flight.setName(user.get().getFirstName());
            flight.setSurname(user.get().getLastName());
        }
        result = flightSource.addFlight(flight);
        if(result.equals("Success"))
            result = usersSource.addMiles(flight);
        return result;
    }
}
