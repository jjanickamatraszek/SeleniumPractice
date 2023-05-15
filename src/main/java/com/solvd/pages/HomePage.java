package com.solvd.pages;

import com.solvd.components.CookieDialog;
import com.solvd.components.MainMenu;
import com.solvd.propertiesReader.ConfigReader;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;

    @Getter
    private final MainMenu mainMenu;

    @Getter
    private final CookieDialog cookieDialog;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.cookieDialog = new CookieDialog(driver);
        this.mainMenu = new MainMenu(driver);
    }

    public HomePage goToPage() {
        driver.get(ConfigReader.getBaseUrl());
        return this;
    }
}
