package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightsSource {

    List<Flight> retrieveFlights(String travelerId);
    String addFlight(Flight flight);
    boolean removeFlight(Flight flight);
    Optional<Flight> checkIfExists(Flight flight);
    String checkIfCorrect(Flight flight);
}
