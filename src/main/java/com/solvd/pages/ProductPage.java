package com.solvd.pages;

import com.solvd.base.BasePage;
import com.solvd.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {

    @FindAll({@FindBy(css = "h1.product-name"), @FindBy(css = "h1[data-testid='product-name']")})
    private WebElement titleLabel;

    @FindBy(css = "div.basic-price")
    private WebElement priceLabel;

    @FindBy(css = "[data-selen='gallery']>[class*='MainPhoto'] .bWWRbz")
    private WebElement mainImg;

    @FindBy(css = "li.image-list-item img")
    private List<WebElement> thumbnailImages;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='description']"), @FindBy(css = "div[data-testid='description']")})
    private WebElement descriptionLabel;

    @FindBy(xpath = ".//label[@data-selen='description']/following-sibling::div//div[contains(@class,'content')]")
    private WebElement descriptionContent;

    @FindBy(xpath = ".//label[@data-selen='composition']")
    private WebElement materialAndCareLabel;

    @FindBy(xpath = ".//label[@data-selen='composition']/following-sibling::div//div[contains(@class,'content')]")
    private WebElement materialAndCareContent;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(titleLabel));
        return getText(titleLabel);
    }

    public String getPrice() {
        wait.until(ExpectedConditions.visibilityOf(priceLabel));
        return getText(priceLabel);
    }

    public boolean mainImageIsDisplayed() {
        return isElementVisible(mainImg);
    }

    public ProductPage expandCollapseDescriptionSection() {
        return expandCollapseSection(descriptionLabel, descriptionContent, materialAndCareContent);
    }

    public boolean isDescriptionExpanded() {
        return isElementDisplayed(descriptionContent);
    }

    public String getDescriptionText() {
        wait.until(ExpectedConditions.visibilityOf(descriptionContent));
        return getText(descriptionContent);
    }

    public ProductPage expandCollapseMaterialAndCareSection() {
        return expandCollapseSection(materialAndCareLabel, materialAndCareContent, descriptionContent);
    }

    private ProductPage expandCollapseSection(WebElement label, WebElement visibleContent, WebElement invisibleContent) {
        wait.until(ExpectedConditions.visibilityOf(label));
        if (label.findElement(By.xpath("./parent::div")).getAttribute("class").contains("expanded")) {
            click(label);
            wait.until(ExpectedConditions.invisibilityOf(visibleContent));
            LOGGER.info("Section collapsed %s".formatted(Utils.extractSelector(visibleContent)));
        } else {
            click(label);
            wait.until(ExpectedConditions.visibilityOf(visibleContent));
            LOGGER.info("Section expanded %s".formatted(Utils.extractSelector(visibleContent)));
            wait.until(ExpectedConditions.invisibilityOf(invisibleContent));
            LOGGER.info("Section collapsed %s".formatted(Utils.extractSelector(invisibleContent)));
        }
        return this;
    }

    public boolean isMaterialAndCareExpanded() {
        return isElementDisplayed(materialAndCareContent);
    }

    public String getMaterialAndCareText() {
        return getText(materialAndCareContent);
    }
}
