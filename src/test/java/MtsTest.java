import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.*;

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
    @Description("Онлайн пополнение без комиссии")
    public void testBlockTitle() {
        HomePage homePage = new HomePage(driver);
        String blockTitle = homePage.getBlockTitle();
        checkBlockTitle(blockTitle, "Онлайн пополнение\nбез комиссии");
    }

    @Step("'{actualTitle}' соответствует '{expectedTitle}'")
    private void checkBlockTitle(String actualTitle, String expectedTitle) {
        Assert.assertEquals(actualTitle, expectedTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    @Description("Проверка отображения логотипов платежных систем")
    public void testPaymentLogos() {
        HomePage homePage = new HomePage(driver);
        checkLogoDisplayed(homePage.isVisaLogoDisplayed(), "Логотип Visa");
        checkLogoDisplayed(homePage.isVerifiedByVisaLogoDisplayed(), "Логотип Verified By Visa");
        checkLogoDisplayed(homePage.isMasterCardLogoDisplayed(), "Логотип MasterCard");
        checkLogoDisplayed(homePage.isMasterCardSecureCodeLogoDisplayed(), "Логотип MasterCard Secure Code");
        checkLogoDisplayed(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт");
    }

    @Step("Логотип '{logoName}' отображается")
    private void checkLogoDisplayed(boolean isLogoDisplayed, String logoName) {
        Assert.assertTrue(isLogoDisplayed, "Логотип " + logoName + " не отображается");
    }

    @Test
    @Description("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testDetailsLink() {
        HomePage homePage = new HomePage(driver);
        clickDetailsLink(homePage);
        checkUrlChanged();
    }

    @Step("Нажатие на 'Подробнее о сервисе'")
    private void clickDetailsLink(HomePage homePage) {
        homePage.clickDetailsLink();
    }

    @Step("Проверка, что URL изменился после нажатия")
    private void checkUrlChanged() {
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.mts.by", "Ссылка 'Подробнее о сервисе' не сработала");
    }

    @Test
    @Description("Проверка сообщений об ошибке для незаполненных полей при оформлении услуг связи")
    public void testEmptyFieldsForCommunicationServices() {
        HomePage homePage = new HomePage(driver);
        homePage.clickContinueButton();
        checkPhoneNumberFieldError(homePage);
        checkAmountFieldError(homePage);
    }

    @Step("Проверка сообщения об ошибке для поля 'Номер телефона'")
    private void checkPhoneNumberFieldError(HomePage homePage) {
        Assert.assertEquals(
                homePage.getPhoneNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненного номера телефона не отображается");
    }

    @Step("Проверка сообщения об ошибке для поля 'Сумма'")
    private void checkAmountFieldError(HomePage homePage) {
        Assert.assertEquals(
                homePage.getAmountFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для незаполненной суммы не отображается");
    }

    @Test(dataProvider = "serviceOptions")
    @Description("Проверка сообщений об ошибке для незаполненных полей при оформлении различных услуг")
    public void testEmptyFieldsForOtherServices(String serviceOption, String fieldName, String amountFieldName) {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption(serviceOption);
        homePage.clickContinueButton();
        checkFieldError(homePage, serviceOption, fieldName);
        checkAmountFieldError(homePage, amountFieldName);
    }

    @Step("Проверка сообщения об ошибке для поля '{fieldName}'")
    private void checkFieldError(HomePage homePage, String serviceOption, String fieldName) {
        switch (serviceOption) {
            case "Домашний интернет":
                Assert.assertEquals(
                        homePage.getSubscriberNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для поля '" + fieldName + "' не отображается");
                break;
            case "Рассрочка":
                Assert.assertEquals(
                        homePage.getAccountNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для поля '" + fieldName + "' не отображается");
                break;
            case "Задолженность":
                Assert.assertEquals(
                        homePage.getArrearsAccountNumberFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для поля '" + fieldName + "' не отображается");
                break;
        }
    }

    @Step("Проверка сообщения об ошибке для поля '{amountFieldName}'")
    private void checkAmountFieldError(HomePage homePage, String amountFieldName) {
        Assert.assertEquals(
                homePage.getAmountFieldErrorText(), "Заполните это поле.", "Сообщение об ошибке для поля '" + amountFieldName + "' не отображается");
    }

    @Test
    @Description("Проверка отображения суммы платежа на странице оформления услуг связи")
    public void testPaymentAmountDisplay() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkDisplayedAmount(homePage);
    }

    @Step("Проверка отображаемой суммы платежа")
    private void checkDisplayedAmount(HomePage homePage) {
        String displayedAmount = homePage.getDisplayedAmount();
        Assert.assertEquals(displayedAmount, "100.00 BYN", "Отображаемая сумма не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка отображения суммы на кнопке оплаты при оформлении услуг связи")
    public void testButtonAmount() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkButtonAmount(homePage);
    }

    @Step("Проверка суммы на кнопке оплаты")
    private void checkButtonAmount(HomePage homePage) {
        String buttonAmount = homePage.getAmountField();
        Assert.assertEquals(buttonAmount, " Оплатить 100.00 BYN ", "Сумма на кнопке оплаты не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка отображения номера телефона на странице оформления услуг связи")
    public void testDisplayedPhoneNumber() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkDisplayedPhoneNumber(homePage);
    }

    @Step("Проверка отображаемого номера телефона")
    private void checkDisplayedPhoneNumber(HomePage homePage) {
        String displayedPhoneNumber = homePage.getDisplayedPhoneNumber();
        Assert.assertEquals(displayedPhoneNumber, "(29)777-77-77", "Отображаемый номер телефона не соответствует ожидаемому");
    }

    @Test
    @Description("Проверка надписи в поле 'Номер карты' на странице оформления услуг связи")
    public void testCardNumberFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkCardNumberFieldLabel(homePage);
    }

    @Step("Проверка надписи в поле 'Номер карты'")
    private void checkCardNumberFieldLabel(HomePage homePage) {
        Assert.assertEquals(homePage.getCardNumberFieldLabel(), "Номер карты", "Надпись в поле 'Номер карты' не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка надписи в поле 'Срок действия' на странице оформления услуг связи")
    public void testExpirationDateFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkExpirationDateFieldLabel(homePage);
    }

    @Step("Проверка надписи в поле 'Срок действия'")
    private void checkExpirationDateFieldLabel(HomePage homePage) {
        Assert.assertEquals(homePage.getExpirationDateFieldLabel(), "Срок действия", "Надпись в поле 'Срок действия' не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка надписи в поле 'CVC' на странице оформления услуг связи")
    public void testCvcFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkCvcFieldLabel(homePage);
    }

    @Step("Проверка надписи в поле 'CVC'")
    private void checkCvcFieldLabel(HomePage homePage) {
        Assert.assertEquals(homePage.getCvcFieldLabel(), "CVC", "Надпись в поле 'CVC' не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка надписи в поле 'Имя держателя' на странице оформления услуг связи")
    public void testCardHolderFieldLabel() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkCardHolderFieldLabel(homePage);
    }

    @Step("Проверка надписи в поле 'Имя держателя'")
    private void checkCardHolderFieldLabel(HomePage homePage) {
        Assert.assertEquals(homePage.getCardHolderFieldLabel(), "Имя держателя (как на карте)", "Надпись в поле 'Имя держателя' не соответствует ожидаемой");
    }

    @Test
    @Description("Проверка отображения логотипов платежных систем на странице оформления услуг связи")
    public void testPaymentSystemLogos() {
        HomePage homePage = new HomePage(driver);
        homePage.selectServiceOption("Услуги связи");
        homePage.fillPhoneNumberField("297777777");
        homePage.fillAmountField("100");
        homePage.clickContinueButton();
        checkVisaLogo(homePage);
        checkMasterCardLogo(homePage);
        checkBelkartLogo(homePage);
    }

    @Step("Проверка отображения логотипа Visa")
    private void checkVisaLogo(HomePage homePage) {
        Assert.assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
    }

    @Step("Проверка отображения логотипа MasterCard")
    private void checkMasterCardLogo(HomePage homePage) {
        Assert.assertTrue(homePage.isMasterCardLogoDisplayed(), "Логотип MasterCard не отображается");
    }

    @Step("Проверка отображения логотипа Белкарт")
    private void checkBelkartLogo(HomePage homePage) {
        Assert.assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается");
    }
}