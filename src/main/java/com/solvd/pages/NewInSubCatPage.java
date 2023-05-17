package com.solvd.pages;

import com.solvd.base.BasePage;
import com.solvd.components.SideBar;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewInSubCatPage extends BasePage {

    @FindBy(css = "h1[class*='category-title']")
    private WebElement titleLabel;

    @Getter
    private SideBar sideBar;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
        this.sideBar = new SideBar(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(titleLabel));
        return getText(titleLabel);
    }
}
