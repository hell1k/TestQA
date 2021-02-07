import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class YandexMainTest extends WebDriverSettings {
    @Test
    public void firstTest() {
        YandexMainPage yandexMainPage = PageFactory.initElements(driver, YandexMainPage.class);
        yandexMainPage.open();
        yandexMainPage.textInput("123");
        yandexMainPage.clickSearch();
    }
}
