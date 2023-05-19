package com.solvd.components;

import com.solvd.base.BasePage;
import com.solvd.pages.NewInSubCatPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;

public class ProductFilters extends BasePage {

    @FindBy(xpath = ".//aside[@id='categoryFilters']//label[text()='Sort by']/parent::div")
    private WebElement sortByFilterLabel;

    @FindBy(xpath = ".//aside[@id='categoryFilters']//label[text()='Sort by']/parent::div//li")
    private List<WebElement> sortOptions;

    @FindBy(xpath = ".//aside[@id='categoryFilters']//label[text()='Sort by']/parent::div//button[@type='submit']")
    private WebElement sortBtn;

    @FindBy(xpath = ".//aside[@id='categoryFilters']//label[text()='Price']/parent::div")
    private WebElement priceFilterLabel;

    @FindBy(css = "#priceFrom")
    private WebElement priceFromTextField;

    @FindBy(css = "#priceTo")
    private WebElement priceToTextField;

    @FindBy(xpath = ".//aside[@id='categoryFilters']//label[text()='Price']/parent::div//button[@type='submit']")
    private WebElement priceFilterBtn;

    public ProductFilters(WebDriver driver) {
        super(driver);
    }

    public NewInSubCatPage sortBy(SortOption sortOption) {
        click(sortByFilterLabel);
        wait.until(ExpectedConditions.visibilityOfAllElements(sortOptions));
        WebElement chosenSortOption = sortOptions
                .stream()
                .filter(el -> el.findElement(By.cssSelector("input")).getAttribute("value").equals(sortOption.getValue()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sort option doesn't exist"));
        click(chosenSortOption);
        click(sortBtn);
        wait.until(ExpectedConditions.invisibilityOfAllElements(sortOptions));
        return new NewInSubCatPage(driver);
    }

    public int getNumberOfActiveSortFilters() {
        return getNumberOfActiveFilters(sortByFilterLabel);
    }

    public NewInSubCatPage filterByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        click(priceFilterLabel);
        wait.until(ExpectedConditions.visibilityOf(priceFilterBtn));
        sendText(priceFromTextField, String.valueOf(priceFrom));
        sendText(priceToTextField, String.valueOf(priceTo));
        click(priceFilterBtn);
        wait.until(ExpectedConditions.invisibilityOf(priceFromTextField));
        return new NewInSubCatPage(driver);
    }

    public int getNumberOfActivePriceFilters() {
        return getNumberOfActiveFilters(priceFilterLabel);
    }

    private int getNumberOfActiveFilters(WebElement filterLabel) {
        String activeFiltersNumber = getText(filterLabel).replaceAll("\\D", "");
        return activeFiltersNumber.isBlank() ? 0 : Integer.parseInt(activeFiltersNumber);
    }
}
