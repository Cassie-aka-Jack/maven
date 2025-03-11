import org.testng.Assert;
import org.testng.annotations.Test;

public class MtsTest extends WebDriverTest {

    @Test
    public void testBlockTitle() {
        HomePage homePage = new HomePage(driver);
        String blockTitle = homePage.getBlockTitle();
        Assert.assertEquals(blockTitle, "Онлайн пополнение\n" + "без комиссии", "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testPaymentLogos() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
        Assert.assertTrue(homePage.isVerifiedByVisaLogoDisplayed(), "Логотип Verified By Visa не отображается");
        Assert.assertTrue(homePage.isMasterCardLogoDisplayed(), "Логотип MasterCard не отображается");
        Assert.assertTrue(homePage.isMasterCardSecureCodeLogoDisplayed(), "Логотип MasterCard Secure Code не отображается");
        Assert.assertTrue(homePage.isБелкартLogoDisplayed(), "Логотип Белкарт не отображается");
    }

    @Test
    public void testDetailsLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickDetailsLink();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.mts.by", "Ссылка 'Подробнее о сервисе' не сработала");
    }

    @Test
    public void testContinueButton() {
        HomePage homePage = new HomePage(driver);
        homePage.fillFormAndClickContinue("29777777", "100", "test@example.com");
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.mts.by", "Форма не была отправлена");
    }
}