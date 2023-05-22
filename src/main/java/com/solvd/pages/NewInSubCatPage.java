package com.solvd.pages;

import com.solvd.base.BasePage;
import com.solvd.components.CookieDialog;
import com.solvd.components.ProductFilters;
import com.solvd.components.SideBar;
import com.solvd.propertiesReader.ConfigReader;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NewInSubCatPage extends BasePage {

    @FindBy(css = "h1[class*='category-title']")
    private WebElement titleLabel;

    @Getter
    private SideBar sideBar;

    @FindBy(css = "#categoryProducts>article")
    private List<WebElement> products;

    @Getter
    private final CookieDialog cookieDialog;

    @Getter
    private ProductFilters productFilters;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
        this.sideBar = new SideBar(driver);
        this.cookieDialog = new CookieDialog(driver);
        this.productFilters = new ProductFilters(driver);
    }

    public NewInSubCatPage goToPage(String route) {
        driver.get(ConfigReader.getBaseUrl() + route);
        return this;
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

    public List<BigDecimal> getProductsPricesAsNumbers() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")));
        return products
                .stream()
                .map(el -> new BigDecimal(el.findElement(By.cssSelector(".es-final-price")).getText().replaceAll("[^\\d.]", "")))
                .collect(Collectors.toList());
    }

    public List<String> getProductsTitles() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")));
        return products
                .stream()
                .map(el -> el.findElement(By.cssSelector(".es-product-name")).getText())
                .collect(Collectors.toList());
    }

    public boolean areProductsLoaded() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")));
    }
}