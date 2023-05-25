package com.solvd.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public WebDriver create(Browser browserType, String hubUrl) throws MalformedURLException {
        switch (browserType) {
            case CHROME -> {
                return getChromeDriver(hubUrl);
            }
            case FIREFOX -> {
                return getFirefoxDriver(hubUrl);
            }
            case SAFARI -> {
                return getSafariDriver(hubUrl);
            }
            default -> throw new IllegalArgumentException("Provided browser doesn't exist");
        }
    }

    private WebDriver getChromeDriver(String hubUrl) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(new URL(hubUrl), options);
    }

    private WebDriver getFirefoxDriver(String hubUrl) throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        return new RemoteWebDriver(new URL(hubUrl), options);
    }

    private WebDriver getSafariDriver(String hubUrl) throws MalformedURLException {
        SafariOptions options = new SafariOptions();
        return new RemoteWebDriver(new URL(hubUrl), options);
    }
}
