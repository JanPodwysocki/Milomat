package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component(value = "FlightAddCSV")
public class FlightAddCSV implements  FlightAddStrategy{

    @Autowired
    FlightsSource flightSource;
    @Autowired
    UsersSource usersSource;

    /**
     * This method parses the CSV file and adds the flight data to the "database".
     * @param toAddObject
     * @return
     */
    @Override
    public String addFlight(Object toAddObject) {
        String pathSCV = (String)toAddObject;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(pathSCV));
        } catch (FileNotFoundException e) {
            return "There is no file";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(";\\s");
            if(line.length > 1){
                String firstName = line[0];
                String lastName = line[1];
                String email = line[2];
                //narazie  jako hasło podajemy firstName
                User user = new User(firstName, lastName, firstName, email, 0.0, "Bronze");
                usersSource.addUser(user);
                Flight flight = new Flight(firstName, lastName, email, line[3],
                        line[4], line[5], line[6], line[7], line[8], LocalDate.parse(line[9], formatter));
                if (flight.getTripType().equals("roundTrip")) {
                    flight.setDepartureDate2(line[10]);
                }
                String result = flightSource.addFlight(flight);
                if(result.equals("Success"))
                    result = usersSource.addMiles(flight);
                //System.out.println(flight.toString());
            }
        }
        scanner.close();
        return "Success";
    }
}
