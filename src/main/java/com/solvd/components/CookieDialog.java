package com.solvd.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookieDialog {

    @FindBy(css = "#cookiebanner #cookiebotDialogOkButton")
    private WebElement okBtn;

    public CookieDialog(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        okBtn.click();
    }
}
