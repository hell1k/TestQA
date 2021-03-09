import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class YandexMainTest extends WebDriverSettings {
    YandexMainPage yandexMainPage;

    @Test
    public void firstTest() {
        //YandexMainPage yandexMainPage = PageFactory.initElements(driver, YandexMainPage.class);
        yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.loginTest("login", "password");
        yandexMainPage.open();
        yandexMainPage.textInput("123");
        yandexMainPage.clickSearch();
    }
}
