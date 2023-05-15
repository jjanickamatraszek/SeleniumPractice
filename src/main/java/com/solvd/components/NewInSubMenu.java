package com.solvd.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NewInSubMenu {
    private final WebDriver driver;

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul[contains(@class,'inner-list')]")
    private WebElement submenuContainer;

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul/div[contains(@class,'photo-section')]")
    private List<WebElement> subCategories;

    public NewInSubMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSubMenuDisplayed() {
        return isElementVisible(submenuContainer);
    }

    public int getNumberOfSubcategories() {
        return subCategories.size();
    }

    public long getNumberOfSubcategoriesWithoutImg() {
        return subCategories
                .stream()
                .map(el -> el.findElement(By.tagName("img")).getAttribute("src"))
                .filter(String::isBlank)
                .count();
    }

    public long getNumberOfSubcategoriesWithoutTitle() {
        return subCategories
                .stream()
                .map(el -> el.findElement(By.cssSelector("div[class*='Text']")).getText())
                .filter(String::isBlank)
                .count();
    }

    private boolean isElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isVisible = true;
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            isVisible = false;
        }
        return isVisible;
    }
}
