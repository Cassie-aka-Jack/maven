import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение ')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//img[contains(@alt, 'Visa')]")
    private WebElement visaLogo;

    @FindBy(xpath = "//img[contains(@alt, 'Verified By Visa')]")
    private WebElement verifiedbyvisaLogo;

    @FindBy(xpath = "//img[contains(@alt, 'MasterCard')]")
    private WebElement mastercardLogo;

    @FindBy(xpath = "//img[contains(@alt, 'MasterCard Secure Code')]")
    private WebElement mastercardsecurecodeLogo;

    @FindBy(xpath = "//img[contains(@alt, 'Белкарт')]")
    private WebElement белкартLogo;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//button[contains(@class, 'select__header')]")
    private WebElement serviceOptionButton; // Кнопка для открытия выпадающего списка

    @FindBy(xpath = "//span[contains(text(), 'Услуги связи')]")
    private WebElement serviceOption; // Опция "Услуги связи" в выпадающем списке

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneNumberField; // Поле для номера телефона

    @FindBy(xpath = "//input[@placeholder='Сумма']")
    private WebElement amountField; // Поле для суммы

    @FindBy(xpath = "//input[@placeholder='E-mail для отправки чека']")
    private WebElement emailField; // Поле для email

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton; // Кнопка "Продолжить"

    @FindBy(xpath = "//button[contains(text(), 'Принять')]")
    private WebElement acceptCookieButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        closeCookiePopup(); // Закрываем всплывающее окно с cookie
    }

    private void closeCookiePopup() {
        try {
            if (isElementDisplayed(acceptCookieButton)) {
                click(acceptCookieButton);
                logger.info("Cookie popup closed successfully.");
            } else {
                logger.info("Cookie popup not found.");
            }
        } catch (Exception e) {
            logger.warn("Failed to close cookie popup: " + e.getMessage());
        }
    }

    public String getBlockTitle() {
        String title = getText(blockTitle);
        logger.info("Block title: " + title);
        return title;
    }

    public boolean isVisaLogoDisplayed() {
        boolean isDisplayed = isElementDisplayed(visaLogo);
        logger.info("Visa logo displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isVerifiedByVisaLogoDisplayed() {
        boolean isDisplayed = isElementDisplayed(verifiedbyvisaLogo);
        logger.info("Verified By Visa logo displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isMasterCardLogoDisplayed() {
        boolean isDisplayed = isElementDisplayed(mastercardLogo);
        logger.info("MasterCard logo displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isMasterCardSecureCodeLogoDisplayed() {
        boolean isDisplayed = isElementDisplayed(mastercardsecurecodeLogo);
        logger.info("MasterCard Secure Code logo displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isБелкартLogoDisplayed() {
        boolean isDisplayed = isElementDisplayed(mastercardsecurecodeLogo);
        logger.info("Белкарт logo displayed: " + isDisplayed);
        return isDisplayed;
    }

    public void clickDetailsLink() {
        logger.info("Clicking 'Подробнее о сервисе' link.");
        click(detailsLink);
    }

    public void fillFormAndClickContinue(String phoneNumber, String amount, String email) {
        logger.info("Filling form and clicking 'Продолжить'.");

        // 1. Клик на кнопку, чтобы открыть выпадающий список
        click(serviceOptionButton);

        // 2. Ожидание появления выпадающего списка
        wait.until(ExpectedConditions.visibilityOf(serviceOption));

        // 3. Выбор опции "Услуги связи"
        click(serviceOption);

        // 4. Заполнение полей
        sendKeys(phoneNumberField, phoneNumber);
        sendKeys(amountField, amount);
        sendKeys(emailField, email);

        // 5. Клик на кнопку "Продолжить"
        click(continueButton);
    }
}