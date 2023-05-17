package com.solvd.base;

import com.solvd.utils.Utils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.Duration;

public abstract class BasePage {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public boolean isElementVisible(WebElement element) {
        boolean isVisible = true;
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            LOGGER.info("Element is visible %s".formatted(Utils.extractSelector(element)));
        } catch (TimeoutException e) {
            isVisible = false;
            LOGGER.info("Element isn't visible %s".formatted(Utils.extractSelector(element)));
        }
        return isVisible;
    }

    public void click(WebElement element) {
        element.click();
        LOGGER.info("Click element %s".formatted(Utils.extractSelector(element)));
    }

    public void hoverOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        LOGGER.info("Hover over element %s".formatted(Utils.extractSelector(element)));
    }

    public String getText(WebElement element) {
        String text = element.getText().strip();
        LOGGER.info("Get text of element %s. Text is '%s'".formatted(Utils.extractSelector(element), text));
        return text;
    }
}
