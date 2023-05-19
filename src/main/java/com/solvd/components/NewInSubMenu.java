package com.solvd.components;

import com.solvd.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewInSubMenu extends BasePage {

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul[contains(@class,'inner-list')]")
    private WebElement submenuContainer;

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul/div[contains(@class,'photo-section')]")
    private List<WebElement> subCategories;

    public NewInSubMenu(WebDriver driver) {
        super(driver);
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
}
