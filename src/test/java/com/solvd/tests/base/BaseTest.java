package com.solvd.tests.base;

import com.solvd.drivers.Browser;
import com.solvd.drivers.DriverFactory;
import com.solvd.propertiesReader.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browserType")
    public void driverSetup(String browserType) throws MalformedURLException {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.create(Browser.valueOf(browserType.toUpperCase()), ConfigReader.getHubUrl());

        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
