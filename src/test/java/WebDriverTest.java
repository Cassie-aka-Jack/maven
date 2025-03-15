import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class WebDriverTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Minun\\chrome-win64\\chromeTesting.exe");
        driver = new ChromeDriver(options);
        driver.get("https://www.mts.by");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        closeCookiePopup();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void closeCookiePopup() {
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять')]")
            ));
            acceptCookiesButton.click();
        } catch (Exception ignored) {
        }
    }
}