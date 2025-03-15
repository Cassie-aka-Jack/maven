import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class MtsTest extends WebDriverTest {

    @DataProvider(name = "serviceOptions")
    public Object[][] getServiceOptions() {
        return new Object[][]{
                {"Домашний интернет", "Номер абонента", "Сумма"},
                {"Рассрочка", "Номер счета на 44", "Сумма"},
                {"Задолженность", "Номер счета на 2073", "Сумма"}
        };
    }

    @Test
    public void testBlockTitle() {
        HomePage homePage = new HomePage(driver);
        String blockTitle = homePage.getBlockTitle();
        Assert.assertEquals(blockTitle, "Онлайн пополнение\nбез комиссии", "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testPaymentLogos() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
        Assert.assertTrue(homePage.isVerifiedByVisaLogoDisplayed(), "Логотип Verified By Visa не отображается");
        Assert.assertTrue(homePage.isMasterCardLogoDisplayed(), "Логотип MasterCard не отображается");
        Assert.assertTrue(homePage.isMasterCardSecureCodeLogoDisplayed(), "Логотип MasterCard Secure Code не отображается");
        Assert.assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается");
    }

    @Test
    public void testDetailsLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickDetailsLink();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.mts.by", "Ссылка 'Подробнее о сервисе' не сработала");
    }

    @Test
    public void testEmptyFieldsForCommunicationServices() {
        HomePage homePage = new HomePage(driver);
        homePage.clickContinueButton();
        Assert.assertEquals(homePage.getPhoneNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненного номера телефона не отображается");
        Assert.assertEquals(homePage.getAmountFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненной суммы не отображается");
    }

    @Test(dataProvider = "serviceOptions")
    public void testEmptyFieldsForOtherServices(String serviceOption, String fieldName, String amountFieldName) {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption(serviceOption);
        homePage.clickContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        switch (serviceOption) {
            case "Домашний интернет":
                Assert.assertEquals(homePage.getSubscriberNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненного номера абонента не отображается");
                break;
            case "Рассрочка":
                Assert.assertEquals(homePage.getAccountNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненного номера счета не отображается");
                break;
            case "Задолженность":
                Assert.assertEquals(homePage.getArrearsAccountNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненного номера счета не отображается");
                break;
        }
        Assert.assertEquals(homePage.getAmountFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненной суммы не отображается");
    }

    @Test
    public void testPaymentAmountDisplay() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> homePage.getAmountDisplayElement().isDisplayed());
        String displayedAmount = homePage.getDisplayedAmount();
        Assert.assertEquals(displayedAmount, "100.00 BYN", "Отображаемая сумма не соответствует ожидаемой");
    }

    @Test
    public void testButtonAmount() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String buttonAmount = homePage.getAmountField();
        Assert.assertEquals(buttonAmount, " Оплатить ", "Сумма на кнопке оплаты не соответствует ожидаемой");
    }

    @Test
    public void testDisplayedPhoneNumber() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        String displayedPhoneNumber = homePage.getDisplayedPhoneNumber();
        Assert.assertEquals(displayedPhoneNumber, "(29)777-77-77", "Отображаемый номер телефона не соответствует ожидаемому");
    }

    @Test
    public void testCardNumberFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        Assert.assertEquals(homePage.getCardNumberFieldLabel(), "Номер карты", "Надпись в поле 'Номер карты' не соответствует ожидаемой");
    }

    @Test
    public void testExpirationDateFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        Assert.assertEquals(homePage.getExpirationDateFieldLabel(), "Срок действия", "Надпись в поле 'Срок действия' не соответствует ожидаемой");
    }

    @Test
    public void testCvcFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        System.out.println("Проверка метки поля 'CVC'...");
        String cvcLabel = homePage.getCvcFieldLabel();
        Assert.assertEquals(cvcLabel, "CVC", "Надпись в поле 'CVC' не соответствует ожидаемой");
    }

    @Test
    public void testCardHolderFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        Assert.assertEquals(homePage.getCardHolderFieldLabel(), "Имя держателя (как на карте)", "Надпись в поле 'Имя держателя' не соответствует ожидаемой");
    }

    @Test
    public void testPaymentSystemLogos() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        Assert.assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
        Assert.assertTrue(homePage.isMasterCardLogoDisplayed(), "Логотип MasterCard не отображается");
        Assert.assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается");
    }
}