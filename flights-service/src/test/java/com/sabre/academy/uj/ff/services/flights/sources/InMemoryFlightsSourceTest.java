package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InMemoryFlightsSourceTest {

    @Autowired
    private FlightsSource flightsSource;

    @Test
    public void shouldSayWrongDepartureAirportCode() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "ĄĘŻ", "WAW", "LO", "1234", "First", "oneWay", LocalDate.now());
        Assert.assertEquals("Wrong Departure Airport Code, try again or check rules", flightsSource.checkIfCorrect(flight));
    }
    @Test
    public void shouldSayWrongCarrier() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "KRK", "WAW", "i", "1234", "First", "oneWay", LocalDate.now());
        Assert.assertEquals("Wrong Carrier Id, try again or check rules", flightsSource.checkIfCorrect(flight));
    }
    @Test
    public void shouldSayWrongFlightNumber() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "KRK", "WAW", "LO", "123u", "First", "oneWay", LocalDate.now());
        Assert.assertEquals("Wrong Flight Number, try again or check rules", flightsSource.checkIfCorrect(flight));
    }
    @Test
    public void shouldSayWrongClassType() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "KRK", "WAW", "LO", "1234", "", "oneWay", LocalDate.now());
        Assert.assertEquals("Wrong Class Type, try again or check rules", flightsSource.checkIfCorrect(flight));
    }
    @Test
    public void shouldSayWrongTripType() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "KRK", "WAW", "LO", "1234", "First", "", LocalDate.now());
        Assert.assertEquals("Wrong Trip Type, try again or check rules", flightsSource.checkIfCorrect(flight));
    }


    @Test
    public void testCheckIfNotExists() {
        Flight flight = new Flight("kasprzak", "kuba", "k@wp.pl",
                "KRK", "WAW", "LO", "1234", "First", "oneWay", LocalDate.now());
        boolean result = flightsSource.checkIfExists(flight).isPresent();
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckIfExists() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Flight flight = new Flight("Elijah", "Adams", "elijah.adams@travel-sabre.com",
                "GRU", "SCL", "QR", "3870", "First", "oneWay", LocalDate.parse("2010-03-02", formatter));
        boolean result = flightsSource.checkIfExists(flight).isPresent();
        Assert.assertTrue(result);
    }

}
