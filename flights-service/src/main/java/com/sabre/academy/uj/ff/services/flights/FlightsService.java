package com.sabre.academy.uj.ff.services.flights;

import com.sabre.academy.uj.ff.services.flights.sources.GeoCodeCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightsService {
    public static void main(String[] args) {
        GeoCodeCalculator.getInstance();
        SpringApplication.run(FlightsService.class, args);
    }
}