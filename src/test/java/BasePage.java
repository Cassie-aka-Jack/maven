import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.js = (JavascriptExecutor) driver;
    }

    protected void log(String message) {
        js.executeScript("console.log(arguments[0]);", message);
    }

    protected void waitForVisibility(WebElement element) {
        log("Ожидание видимости элемента: " + element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        log("Ожидание кликабельности элемента: " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        log("Клик по элементу: " + element);
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        log("Ввод текста '" + text + "' в элемент: " + element);
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        log("Получение текста из элемента: " + element);
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            log("Проверка отображения элемента: " + element);
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            log("Элемент не отображается: " + element);
            return false;
        }
    }
}