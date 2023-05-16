package com.solvd.components;

import com.solvd.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookieDialog extends BasePage {

    @FindBy(css = "#cookiebanner #cookiebotDialogOkButton")
    private WebElement okBtn;

    public CookieDialog(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        okBtn.click();
    }
}
