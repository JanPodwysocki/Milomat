package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class InMemoryUsersSource implements UsersSource{
    private List<User> users = new ArrayList<>();

    /**
     * This method returns a list of users.
     * @return
     */
    @Override
    public List<User> retrieveAllUsers() {
        return users;
    }
    //Narazie zakladamy ze tylko caly obiekt sprawdzamy czy jest, ale nei dodaje nowych uzytkownikow bez nadpisanego equals, neiw iem dlaczego, ale to dobrze:)

    /**
     * This method adds a user to our "database".
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        if(!users.stream().anyMatch(x-> x.getEmail().equals(user.getEmail()))){
            users.add(user);
            return true;
        }
        return false;
    }

    /**
     * This method removes a user from our "database".
     * @param user
     * @return
     */
    @Override
    public boolean removeUser(User user) {
        if(users.stream().anyMatch(x-> x.getEmail().equals(user.getEmail()))){
            users.remove(user);
            return true;
        }
        return false;
    }

    /**
     * This method calculates number of miles in our system based on the length of the trip, trip type and class type.
     * @param miles
     * @param tripType
     * @param classType
     * @return
     */
    private double milesMultiplier(double miles, String tripType, String classType){
        int x = 0;
        int y = 0;
        double[][] mult = new double[2][4];
        mult[0][0] = 1.0;
        mult[0][1] = 1.5;
        mult[0][2] = 3.0;
        mult[0][3] = 5.0;
        mult[1][0] = 2.6;
        mult[1][1] = 3.6;
        mult[1][2] = 8.0;
        mult[1][3] = 12.0;

        switch (tripType){
            case "roundTrip":
                y = 1;
                break;
            case "oneWay":
                y = 0;
        }

        switch (classType){
            case "First":
                x = 3;
                break;
            case "Business":
                x = 2;
                break;
            case "Premium Economy":
                x = 1;
                break;
            case "Economy":
                x = 0;
        }
        //System.out.println("Wybralem mnoznik: " + mult[y][x]);
        return miles * mult[y][x];
    }

    /**
     * This method checks if there's already a given email in our "database".
     * @param email
     * @return
     */
    @Override
    public Optional<User> checkIfExist(String email) {
        return users.stream().filter(x -> x.getEmail().equals(email)).findAny();
    }

    /**
     * This method adds miles to a user's account.
     * @param flight
     * @return
     */
    @Override
    public String addMiles(Flight flight) {
        System.out.println("Im adding flights and miles");
        double result = GeoCodeCalculator.getInstance().mileCalculatorBrain(flight.getDepartureAirportCode(), flight.getArrivalAirportCode());
        Optional<User> user = this.checkIfExist(flight.getMail());
        //System.out.println("Znaleziony User: " + user.get().toString());
        //System.out.println("Zwrocona liczba mil GeoCode: " + result);
        if(user.isPresent() && result > 0 ){
            double actMiles = user.get().getMiles();
            result = milesMultiplier(result, flight.getTripType(), flight.getClassType());
            //System.out.println("Liczba dodanych punktow: " + result);
            user.get().setMiles(result + actMiles);
            //System.out.println("Liczba punktów w sumie: " + user.get().getMiles());
            user.get().upDateStatus();
            return "Success";
        }
        return "Wrong airport code or user not found";
    }

    /**
     * This method checks if a user exists in our "database" and if the credentials are correct.
     * @param user
     * @return
     */
    @Override
    public boolean testUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Optional<User> optUser = this.checkIfExist(email);
        return optUser.map(user1 -> user1.getPassword().equals(password)).orElse(false);
    }


    /**
     * This method registers a user, returns a String with the feedback information.
     * @param user
     * @return
     */
    @Override
    public String registerUser(User user){
        user.upDateStatus();
        if(user.toString().contains("=,"))
            return "Wrong data, please fill all the gaps";
        if(!user.getEmail().contains("@"))
            return "Wrong email";
        if(this.checkIfExist(user.getEmail()).isPresent())
            return "Email adress already in use";
        this.addUser(user);
        return "Success";
    }

    /**
     * This method retrieves User object based on his email address.
     * @param mail
     * @return
     */
    @Override
    public User getUserInfo(String mail) {
        Optional<User> user = this.checkIfExist(mail);
        if(user.isPresent())
            return user.get();
        return null;
    }
}
