package com.solvd.pages;

import com.solvd.base.BasePage;
import com.solvd.components.SideBar;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NewInSubCatPage extends BasePage {

    @FindBy(css = "h1[class*='category-title']")
    private WebElement titleLabel;

    @Getter
    private SideBar sideBar;

    @FindBy(css = "#categoryProducts>article")
    private List<WebElement> products;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
        this.sideBar = new SideBar(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(titleLabel));
        return getText(titleLabel);
    }

    public ProductPage clickOnProductByTitle(String title) {
        click(products
                .stream()
                .filter(element -> getText(element.findElement(By.tagName("h3"))).equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with title '%s' doesn't exist".formatted(title))));
        return new ProductPage(driver);
    }
}
