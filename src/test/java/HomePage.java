import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//img[@alt='Visa']")
    private WebElement visaLogo;

    @FindBy(xpath = "//img[@alt='Verified By Visa']")
    private WebElement verifiedByVisaLogo;

    @FindBy(xpath = "//img[@alt='MasterCard']")
    private WebElement masterCardLogo;

    @FindBy(xpath = "//img[@alt='MasterCard Secure Code']")
    private WebElement masterCardSecureCodeLogo;

    @FindBy(xpath = "//img[@alt='Белкарт']")
    private WebElement belkartLogo;

    @FindBy(xpath = "//button[contains(@class, 'select__header')]")
    private WebElement serviceOptionButton;

    @FindBy(xpath = "//p[contains(text(), 'Домашний интернет')]")
    private WebElement homeInternetOption;

    @FindBy(xpath = "//p[contains(text(), 'Рассрочка')]")
    private WebElement installmentOption;

    @FindBy(xpath = "//p[contains(text(), 'Задолженность')]")
    private WebElement debtOption;

    @FindBy(xpath = "//input[@id='connection-phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@id='internet-phone']")
    private WebElement subscriberNumberField;

    @FindBy(xpath = "//input[@id='score-instalment']")
    private WebElement accountNumberField;

    @FindBy(xpath = "//input[@id='score-arrears']")
    private WebElement arrearsAccountNumberField;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//div[@class='pay-description__cost']//span[contains(text(), 'BYN')]")
    private WebElement amountInPopup;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]/div[1]/span[contains(text(), 'BYN')]")
    private WebElement amountDisplay;

    @FindBy(xpath = "//button[@class='colored disabled' and @type='submit' and contains(normalize-space(text()), ' Оплатить ')]")
    private WebElement paymentButton;

    @FindBy(xpath = "//input[@label='Номер карты']")
    private WebElement cardNumberLabel;

    @FindBy(xpath = "//input[@label='Срок действия']")
    private WebElement expirationDateField;

    @FindBy(xpath = "//input[@label='CVC']")
    private WebElement cvcField;

    @FindBy(xpath = "//input[@label='Имя держателя (как на карте)']")
    private WebElement cardHolderField;

    @FindBy(xpath = "//input[@id='connection-phone']")
    private WebElement fieldName;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountFieldName;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getBlockTitle() {
        return blockTitle.getText();
    }

    public boolean isVisaLogoDisplayed() {
        return visaLogo.isDisplayed();
    }

    public boolean isVerifiedByVisaLogoDisplayed() {
        return verifiedByVisaLogo.isDisplayed();
    }

    public boolean isMasterCardLogoDisplayed() {
        return masterCardLogo.isDisplayed();
    }

    public boolean isMasterCardSecureCodeLogoDisplayed() {
        return masterCardSecureCodeLogo.isDisplayed();
    }

    public boolean isBelkartLogoDisplayed() {
        return belkartLogo.isDisplayed();
    }

    public void clickDetailsLink() {
        detailsLink.click();
    }

    public void selectServiceOption(String option) {
        if (option.equals("Услуги связи")) {
            return;
        }
        click(serviceOptionButton);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Домашний интернет')]")));

        switch (option) {
            case "Домашний интернет":
                click(homeInternetOption);
                break;
            case "Рассрочка":
                click(installmentOption);
                break;
            case "Задолженность":
                click(debtOption);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная опция: " + option);
        }
    }

    public void clickContinueButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            continueButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось кликнуть на кнопку 'Продолжить'.", e);
        }
    }

    public String getPhoneNumberFieldErrorText() {
        return phoneNumberField.getAttribute("validationMessage");
    }

    public String getAmountFieldErrorText() {
        return amountField.getAttribute("validationMessage");
    }

    public String getSubscriberNumberFieldErrorText() {
        return subscriberNumberField.getAttribute("validationMessage");
    }

    public String getAccountNumberFieldErrorText() {
        return accountNumberField.getAttribute("validationMessage");
    }

    public String getArrearsAccountNumberFieldErrorText() {
        return arrearsAccountNumberField.getAttribute("validationMessage");
    }

    public void fillPhoneNumberField(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void fillAmountField(String amount) {
        amountField.sendKeys(amount);
    }

    public String getDisplayedAmount() {
        return amountDisplay.getText();
    }

    public String getAmountField() {
        return amountField.getText();
    }

    public String getDisplayedPhoneNumber() {
        return phoneNumberField.getAttribute("value");
    }

    public String getCardNumberFieldLabel() {
        return cardNumberLabel.getText();
    }

    public String getExpirationDateFieldLabel() {
        return expirationDateField.getAttribute("placeholder");
    }

    public String getCvcFieldLabel() {
        return cvcField.getAttribute("placeholder");
    }

    public String getCardHolderFieldLabel() {
        return cardHolderField.getAttribute("placeholder");
    }
}