package com.sabre.academy.uj.ff.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestMilomatFunctiona extends TestBase{
    @Test
    public void correctLoginAndCheckProfile(){
        driver.get(baseURL);
        driver.findElement(By.id("login-user-button")).click();
        driver.findElement(By.id("mail")).sendKeys("elijah.adams@travel-sabre.com");
        driver.findElement(By.id("password")).sendKeys("Elijah");
        driver.findElement(By.id("login-user-button")).click();

        //open Side Menu
        waitForElementVisible(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button"));
        driver.findElement(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button")).click();


        //choose "Profile"
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[2]/div/a[1]/div/span"));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[2]/div/a[1]/div/span")).click();

        //check Miles
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]"));
        System.out.println("Elijah Miles: " + driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]")).getText());

    }

    @Test
    public void wrongLoginAndRemindRules(){
        //try sign in with wrong credentials
        driver.get(baseURL);
        driver.findElement(By.id("login-user-button")).click();
        driver.findElement(By.id("mail")).sendKeys("ah.adams@travel-sabre.com");
        driver.findElement(By.id("password")).sendKeys("ah");
        driver.findElement(By.id("login-user-button")).click();

        //wait for info about wrong credentials
        waitForElementVisible(By.id("resultInfo"));

        //checkRules
        driver.findElement(By.cssSelector("#root > div > main > div > div > div.buttonStyle > a:nth-child(15)")).click();
    }
    @Test
    public void registerAndLogin(){
        driver.get(baseURL);
        driver.findElement(By.xpath("//*[@id=\"register\"]/div")).click();
        //register
        waitForElementVisible(By.id("name"));
        driver.findElement(By.id("name")).sendKeys("Helena");
        driver.findElement(By.id("surname")).sendKeys("Tegene");
        driver.findElement(By.id("mail")).sendKeys("hTegene@gmail.com");
        driver.findElement(By.id("password")).sendKeys("hela");
        driver.findElement(By.xpath("//*[@id=\"register-user-button\"]/div/div")).click();

        //sign in
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[3]/h3"));
        driver.findElement(By.id("login-user-button")).click();
        driver.findElement(By.id("mail")).sendKeys("hTegene@gmail.com");
        driver.findElement(By.id("password")).sendKeys("hela");
        driver.findElement(By.id("login-user-button")).click();
    }

    @Test
    public void loginAndAddFlightWithMilesCheck(){

        //login
        driver.get(baseURL);
        driver.findElement(By.id("login-user-button")).click();
        driver.findElement(By.id("mail")).sendKeys("noah.williams@travel-sabre.com");
        driver.findElement(By.id("password")).sendKeys("Noah");
        driver.findElement(By.id("login-user-button")).click();

        //checkMilesNumberBefore:
        waitForElementVisible(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button"));
        driver.findElement(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button")).click();
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[2]/div/a[1]/div/span"));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[2]/div/a[1]/div/span")).click();
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]"));
        String milesBefore = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]")).getText().split(" ")[2];
        System.out.println(milesBefore);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/button")).click();
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[4]/div[2]/div/a[2]/div/span/div/div"));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[4]/div[2]/div/a[2]/div/span/div/div")).click();

        //add flight
        waitForElementVisible(By.id("origin"));
        driver.findElement(By.id("origin")).sendKeys("MEL");
        driver.findElement(By.id("destination")).sendKeys("KRK");
        driver.findElement(By.id("carrier")).sendKeys("LH");
        driver.findElement(By.id("flightNo")).sendKeys("9999");

        driver.findElement(By.id("classType")).click();
        waitForElementVisible(By.xpath("/html/body/div[4]/div/div/div"));
        WebElement classType = driver.findElement(By.xpath("/html/body/div[4]/div/div/div"));
        classType.findElement(By.xpath("/html/body/div[4]/div/div/div/div[4]/span")).click();

        waitForElementInVisible(By.xpath("/html/body/div[4]/div/div/div"));
        driver.findElement(By.id("tripType")).click();

        waitForElementVisible(By.xpath("/html/body/div[4]/div/div/div/div[1]/span/div/div/div"));
        WebElement tripType = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/span/div/div/div"));
        tripType.findElement(By.xpath("/html/body/div[4]/div/div/div/div[2]/span/div/div/div")).click();

        waitForElementInVisible(By.xpath("/html/body/div[4]/div/div/div/div[1]/span/div/div/div"));

        waitForElementVisible(By.id("departureDate"));
        driver.findElement(By.id("departureDate")).click();
        waitForElementVisible(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div"));
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div/div[3]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/div/div[2]/div[2]/button[2]/div")).click();

        waitForElementInVisible(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div"));
        driver.findElement(By.id("comeBackDate")).click();
        waitForElementVisible(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div"));
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div/div[4]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div/div[2]/div[2]/button[2]/div")).click();

        waitForElementInVisible(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div/div[2]/div[1]/div[3]/div/div"));
        driver.findElement(By.xpath("//*[@id=\"add-flight-button\"]/div/div")).click();

        waitForElementVisible(By.id("origin"));
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("origin")).getAttribute("value").length() == 0;
            }
        });


        System.out.println("Czy dodany lot: " + driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[4]/div[2]/table")).getText().contains("LH 9999 2018-01-16 MEL KRK"));

        //Check Miles After
        waitForElementVisible(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button"));
        driver.findElement(By.cssSelector("#root > div > main > div > div:nth-child(1) > div.Bar > button")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[1]/div[2]/div[2]/div/a[1]/div/span")).click();
        waitForElementVisible(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]"));
        String milesAfter = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[5]/h3[3]")).getText().split(" ")[2];
        System.out.println(milesAfter);

        //compare miles after and miles before
        if(milesBefore.equals(milesAfter)) {
            try {
                throw new Exception("Number of miiles before and after opertaion is the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Miles added");
        }
    }
}
