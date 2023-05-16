package com.solvd.components;

import com.solvd.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookieDialog extends BasePage {

    @FindBy(css = "#cookiebanner #cookiebotDialogOkButton")
    private WebElement okBtn;

    public CookieDialog(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.visibilityOf(okBtn));
        click(okBtn);
    }
}
