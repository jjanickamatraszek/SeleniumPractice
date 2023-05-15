package com.solvd.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu {
    private final WebDriver driver;

    @FindBy(xpath = ".//ul[@data-testid='category-list']//span[contains(text(),'new in')]/parent::li")
    private WebElement newInCategory;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewInSubMenu hoverOverNewInCategory() {
        Actions actions = new Actions(driver);
        actions.moveToElement(newInCategory).build().perform();
        return new NewInSubMenu(driver);
    }
}
