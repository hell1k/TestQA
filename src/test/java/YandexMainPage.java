import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMainPage {
    public WebDriverWait wait;
    WebDriver driver;

    public YandexMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open() {
        driver.get("https://yandex.ru");
    }

    @FindBy(name = "text")
    private WebElement searchField;

    public void textInput(String text) {
        searchField.sendKeys(text);
    }

    @FindBy(className = "search2__button")
    private WebElement searchButton;

    By searchTextClass = By.className("serp-adv__found");

    public void clickSearch() {
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextClass));
    }
}
