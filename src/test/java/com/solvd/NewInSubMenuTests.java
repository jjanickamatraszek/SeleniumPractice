package com.solvd;

import com.solvd.components.NewInSubMenu;
import com.solvd.pages.HomePage;
import com.solvd.propertiesReader.ConfigReader;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class NewInSubMenuTests {
    private WebDriver driver;

    @BeforeMethod
    public void driverSetup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(ConfigReader.getHubUrl()), options);
    }

    @Test(description = "Display submenu of 'New In' menu category")
    @MethodOwner(owner = "jjanickamatraszek")
    public void displaySubMenuTest() {
        int expectedNumberOfSubcategories = 4;

        HomePage homePage = new HomePage(driver);
        homePage.goToPage().getCookieDialog().acceptCookies();
        NewInSubMenu newInSubMenu = homePage.getMainMenu().hoverOverNewInCategory();

        Assert.assertTrue(newInSubMenu.isSubMenuDisplayed(), "'New In' submenu is not visible after hover on menu category");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubMenu.getNumberOfSubcategories(), expectedNumberOfSubcategories, "Number of subcategories" +
                "is different than expected ");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutImg(), 0, "Some subcategories don't have an image");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutTitle(), 0, "Some subcategories don't have a title");
        soft.assertAll();
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
