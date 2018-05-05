package com.sabre.academy.uj.ff.services.flights.controllers;

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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UsersControllerTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private FlightsSource UsersSource;

    @Test
    public void testIncorrectUser() {
        User user = new User();
        user.setEmail("thrterrthr@gr3e.pl");
        user.setPassword("ertwq3werhger");
        boolean correctUser = restTemplate.postForObject("/login", user, Boolean.class);
        Assert.assertFalse(correctUser);
    }

    @Test
    public void testCorrectUser() {
        User user = new User();
        user.setEmail("elijah.adams@travel-sabre.com");
        user.setPassword("Elijah");
        boolean correctUser = restTemplate.postForObject("/login", user, Boolean.class);
        Assert.assertTrue(correctUser);
    }

    @Test
    public void testAllTravelerList() {
        List<User> users = Arrays.asList(restTemplate.getForObject("/travelers/users", User[].class));
        assertThat(users)
                .hasSize(5);
    }


    @Test
    public void testGetUserInfo() {
        User user = restTemplate.getForObject("/travelers/isabella.davis@travel-sabre.com/profile", User.class);

        assertThat(user.getPassword())
                .contains("Isab");

        assertThat(user.getStatus())
                .contains("onze");
    }
    @Test
    public void testRegistration() {
        User user = new User();
        user.setFirstName("Rahim");
        user.setLastName("Diawara");
        user.setEmail("rDiaw@gmail.com");
        user.setPassword("Diawara");
        String result = restTemplate.postForObject("/registration", user, String.class);

        assertThat(result)
                .contains("Success");

        List<User> users = Arrays.asList(restTemplate.getForObject("/travelers/users", User[].class));

        assertThat(users.toString())
                .contains("rDiaw@gmail.com");
    }
}
