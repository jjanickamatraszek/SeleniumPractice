package com.solvd.components;

import com.solvd.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SideBar extends BasePage {

    @FindBy(css = "ul[data-testid='sidebarSubMenu']")
    private WebElement sideBarContainer;

    @FindBy(css = "ul[data-testid='sidebarSubMenu']>li.active")
    private WebElement activeCategory;

    public SideBar(WebDriver driver) {
        super(driver);
    }

    public String getActiveCategoryTitle() {
        wait.until(ExpectedConditions.visibilityOf(sideBarContainer));
        return getText(activeCategory);
    }
}
