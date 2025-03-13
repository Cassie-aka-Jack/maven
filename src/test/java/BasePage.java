import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    protected void waitForVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible: " + element);
        } catch (Exception e) {
            logger.error("Element not visible: " + e.getMessage());
            throw e;
        }
    }

    protected void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element is clickable: " + element);
        } catch (Exception e) {
            logger.error("Element not clickable: " + e.getMessage());
            throw e;
        }
    }

    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
        logger.info("Clicked on element: " + element);
    }

    protected void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Sent keys to element: " + element + ", text: " + text);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        String text = element.getText();
        logger.info("Text retrieved from element: " + element + ", text: " + text);
        return text;
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element displayed: " + element + ", isDisplayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.warn("Element not displayed: " + e.getMessage());
            return false;
        }
    }
}