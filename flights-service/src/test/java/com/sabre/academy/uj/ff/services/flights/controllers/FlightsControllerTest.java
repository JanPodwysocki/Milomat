package com.sabre.academy.uj.ff.services.flights.controllers;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;
import com.sabre.academy.uj.ff.services.flights.sources.FlightsSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FlightsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private FlightsSource flightsSource;

    @Test
    public void returnsFlightsForExistingTraveler() {
        List<Flight> flights = Arrays.asList(restTemplate.getForObject("/travelers/isabella.davis@travel-sabre.com/flights", Flight[].class));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Flight isaFlight = new Flight("Isabella", "Davis", "isabella.davis@travel-sabre.com", "ORD",
                "FLL", "AA", "1433", "Premium Economy", "oneWay", LocalDate.parse("2013-01-02", formatter));

        assertThat(flights)
                .hasSize(5)
                .contains(isaFlight);
    }

    @Test
    public void checkIfFlyAdded() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Flight newFlight = new Flight("Isabella", "Davis", "isabella.davis@travel-sabre.com", "MEL",
                "KRK", "AA", "1933", "Premium Economy", "oneWay", LocalDate.parse("2017-01-02", formatter));

        restTemplate.postForObject("/travelers/isabella.davis@travel-sabre.com/flights/", newFlight, String.class);
        List<Flight> flights = Arrays.asList(restTemplate.getForObject("/travelers/isabella.davis@travel-sabre.com/flights", Flight[].class));

        assertThat(flights)
                .hasSize(6)
                .contains(newFlight);
    }
}