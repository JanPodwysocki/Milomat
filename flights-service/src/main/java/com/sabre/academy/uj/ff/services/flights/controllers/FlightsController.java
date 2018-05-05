package com.sabre.academy.uj.ff.services.flights.controllers;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.sources.MilomatFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Component
@RestController
public class FlightsController {

    @Autowired
    MilomatFacade milomatFacade;

    /**
     * This method is mapped to a user's flights website, gets their flights and shows them in a table.
     * @param travelerMail
     * @return
     */
    @RequestMapping(value = "travelers/{travelerMail}/flights", method = RequestMethod.GET)
    public List<Flight> retrieveTravelersFlights(@PathVariable String travelerMail) {
        return milomatFacade.getTravelersFlight(travelerMail);
    }

    /**
     * This method is mapped to a user's flights website, it posts the .
     * @param travelerMail
     * @param flight
     * @return
     */
    @RequestMapping(value = "/travelers/{travelerMail}/flights/", method = RequestMethod.POST)
    public String addFlight(@PathVariable String travelerMail, @RequestBody Flight flight) {
        flight.setMail(travelerMail);
        return milomatFacade.addFlightUserInput(flight);
    }
}
