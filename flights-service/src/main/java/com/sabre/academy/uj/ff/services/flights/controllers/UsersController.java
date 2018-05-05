package com.sabre.academy.uj.ff.services.flights.controllers;

import com.sabre.academy.uj.ff.services.flights.domain.User;
import com.sabre.academy.uj.ff.services.flights.sources.MilomatFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Component
@RestController
public class UsersController {
    @Autowired
    MilomatFacade milomatFacade;

    /**
     * This method retrieves all users from our "database".
     * @return
     */
    @RequestMapping(value = "travelers/users", method = RequestMethod.GET)
    public List<User> retrieveAllUsers() {
        return milomatFacade.getAllUsers();
    }

    /**
     * This method checks if the users exists in our "database", compares credentials
     * and eventually may log user in to our service.
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean testUser(@RequestBody User user){
        return  milomatFacade.testUser(user);
    }

    /**
     * This method is mapped to a user's profile website, it retrieves the user's information
     * based on the user's email address.
     * @param travelerMail
     * @return
     */
    @RequestMapping(value = "travelers/{travelerMail}/profile", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable String travelerMail) {
        return milomatFacade.getUserInfo(travelerMail);
    }

    /**
     * This method is mapped to registration website,
     * it registers a user's account based on the credentials he entered.
     * @param user
     * @return
     */
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        return milomatFacade.register(user);
    }
}
