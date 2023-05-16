package com.solvd.components;

import com.solvd.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu extends BasePage {

    @FindBy(xpath = ".//ul[@data-testid='category-list']//span[contains(text(),'new in')]/parent::li")
    private WebElement newInCategory;

    public MainMenu(WebDriver driver) {
        super(driver);
    }

    public NewInSubMenu hoverOverNewInCategory() {
        Actions actions = new Actions(driver);
        actions.moveToElement(newInCategory).build().perform();
        return new NewInSubMenu(driver);
    }
}
