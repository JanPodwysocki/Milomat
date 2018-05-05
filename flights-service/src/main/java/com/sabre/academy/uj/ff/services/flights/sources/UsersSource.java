package com.sabre.academy.uj.ff.services.flights.sources;

import com.sabre.academy.uj.ff.services.flights.domain.Flight;
import com.sabre.academy.uj.ff.services.flights.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersSource {
    List<User> retrieveAllUsers();
    boolean addUser(User user);
    boolean removeUser(User user);
    Optional<User> checkIfExist(String email);
    String addMiles(Flight flight);
    boolean testUser(User user);
    String registerUser(User user);
    User getUserInfo(String mail);
}
