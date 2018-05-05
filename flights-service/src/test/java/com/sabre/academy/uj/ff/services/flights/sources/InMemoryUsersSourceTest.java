package com.sabre.academy.uj.ff.services.flights.sources;
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
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InMemoryUsersSourceTest{

    @Autowired
    private UsersSource usersSource;

    @Test
    public void shouldSayWrongData() {
        User user = new User();
        user.setEmail("elijah.adams@travel-sabre.com");
        user.setPassword("Elijah");
        user.setLastName(",");
        Assert.assertEquals("Wrong data, please fill all the gaps", usersSource.registerUser(user));
    }
    @Test
    public void shouldSayWrongEmail() {
        User user = new User();
        user.setEmail("elijah.adamstravel-sabre.com");
        user.setPassword("Elijah");
        user.setLastName("bhstcelhjrbs");
        user.setPassword("krhgjsk");
        Assert.assertEquals("Wrong email", usersSource.registerUser(user));
    }
    @Test
    public void shouldSayEmailInUse() {
        User user = new User();
        user.setEmail("elijah.adams@travel-sabre.com");
        user.setPassword("Elijah");
        user.setLastName("ghborsi");
        user.setPassword("krhgjsk");
        Assert.assertEquals("Email adress already in use", usersSource.registerUser(user));
    }

    @Test
    public void testCheckIfExists() {
        boolean result = usersSource.checkIfExist("elijah.adamsekfesyfvksu@travel-sabre.com").isPresent();
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckIfNotExists() {
        boolean result = usersSource.checkIfExist("elijah.adams@travel-sabre.com").isPresent();
        Assert.assertTrue(result);
    }

    @Test
    public void testAddUserInCorrect(){
        User user = new User();
        user.setEmail("elijah.adams@travel-sabre.com");
        user.setPassword("Elijah");
        user.setLastName("ghborsi");
        user.setPassword("krhgjsk");

        Assert.assertFalse(usersSource.addUser(user));
    }

    @Test
    public void testAddUserCorrect(){
        User user = new User();
        user.setEmail("elijah.adamgnerrses@travel-sabre.com");
        user.setPassword("Exfbxfb");
        user.setLastName("ghborxfbsi");
        user.setPassword("krhgxfbxfbxfbsk");

        Assert.assertTrue(usersSource.addUser(user));
    }
}
