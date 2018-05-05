package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryFlightsSource implements FlightsSource {

    private List<Flight> flights = new ArrayList<>();

    /**
     * This method retrieve's user's flights based on his email address.
     *
     * @param travelerMail
     * @return
     */
    @Override
    public List<Flight> retrieveFlights(String travelerMail) {
        return flights.stream().filter(f -> f.getMail().equals(travelerMail)).collect(Collectors.toList());
    }

    /**
     * This method adds a flight to the database, it also checks if the flight hadn't already been added.
     *
     * @param flight
     * @return
     */
    @Override
    public String addFlight(Flight flight) {
        if (flight.getTripType().equals("oneWay"))
            flight.setDepartureDate2(" ");
        if (!this.checkIfExists(flight).isPresent()) {
            String result = this.checkIfCorrect(flight);
            if (result.equals("Success")) {
                flights.add(flight);
            }
            return result;
        }
        return "Flight already exists";
    }

    /**
     * This method removes a given flight if it exists in the "database".
     *
     * @param flight
     * @return
     */
    @Override
    public boolean removeFlight(Flight flight) {
        if (this.checkIfExists(flight).isPresent()) {
            flights.remove(flight);
            return true;
        }
        return false;
    }

    /**
     * This method checks if the flight exists in our "database".
     *
     * @param flight
     * @return
     */
    @Override
    public Optional<Flight> checkIfExists(Flight flight) {
        //przetestowac to cos tu nie działa
        Optional<Flight> testfl = flights.stream().filter(x ->
                x.getMail().equals(flight.getMail()) &&
                        x.getNumber().equals(flight.getNumber()) &&
                        x.getDepartureDate().toString().substring(0, 9).equals(flight.getDepartureDate().toString().substring(0, 9)))
                .findAny();
        return testfl;
    }

    /**
     * This method checks if the given flight's data are correct. It returns feedback in a String.
     *
     * @param flight
     * @return
     */
    @Override
    public String checkIfCorrect(Flight flight) {
        if (!flight.getDepartureAirportCode().matches("[A-Z]{3}"))
            return "Wrong Departure Airport Code, try again or check rules";

        if (!flight.getArrivalAirportCode().matches("[A-Z]{3}"))
            return "Wrong Arrival Airport Code, try again or check rules";

        if (!flight.getCarrier().matches("[A-Z]{2}"))
            return "Wrong Carrier Id, try again or check rules";

        if (!(flight.getNumber().matches("\\d{4}")))
            return "Wrong Flight Number, try again or check rules";

        if (flight.getClassType().length() < 3)
            return "Wrong Class Type, try again or check rules";

        if (flight.getTripType().length() < 3)
            return "Wrong Trip Type, try again or check rules";

        if (flight.getDepartureDate().toString().length() < 3)
            return "Departure Date is missing, try again or check rules";

        if (flight.getTripType().equals("roundTrip") && flight.getDepartureDate2().toString().length() < 2)
            return "Come Back Date is missing, try again or check rules";

        return "Success";
    }
}
