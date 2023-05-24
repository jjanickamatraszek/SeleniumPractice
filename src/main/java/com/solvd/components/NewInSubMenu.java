package com.solvd.components;

import com.solvd.base.BasePage;
import com.solvd.pages.NewInSubCatPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class NewInSubMenu extends BasePage {

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul[contains(@class,'inner-list')]")
    private WebElement submenuContainer;

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//ul/div[contains(@class,'photo-section')]")
    private List<WebElement> subCategories;

    private String subcategoryXPATH = ".//span[contains(text(),'new in')]/parent::li//div[text()='%s']";

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
                .map(el -> getText(el.findElement(By.cssSelector("div[class*='Text']"))))
                .filter(String::isBlank)
                .count();
    }

    public List<String> getSubCategoriesTitles() {
        return subCategories
                .stream()
                .map(el -> getText(el.findElement(By.cssSelector("div[class*='Text']"))))
                .collect(Collectors.toList());
    }

    public NewInSubCatPage clickOnSubcategory(String title) {
        String path = subcategoryXPATH.replace("%s", title.toUpperCase());
        click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path))));
        wait.until(ExpectedConditions.invisibilityOf(submenuContainer));
        return new NewInSubCatPage(driver);
    }
}
