package com.sabre.academy.uj.ff.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    static WebDriver driver;
    static String baseURL;
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/jan/chromedriver");
        baseURL = "http://localhost:3000/";

        /*
        JANEK: "/Users/jan/chromedriver"
        */
        driver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver","C:\\PATH_TO_GECKODRIVER\\geckodriver.exe");
//        driver = new FirefoxDriver();
    }

    public void waitForElementVisible(By... by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        for (By element : by) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
    }

    public void waitForElementInVisible(By... by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        for (By element : by) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
