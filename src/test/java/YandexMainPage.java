import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMainPage {
    public WebDriverWait wait;
    WebDriver driver;

    public YandexMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void loginTestTrue() {
        driver.findElement(By.id("login")).sendKeys("trueLogin");
        driver.findElement(By.id("pass")).sendKeys("truePass");
        driver.findElement(By.id("submit")).click();
        Assert.assertEquals("text1", "text2");
    }

    public void loginTestError() {
        driver.findElement(By.id("login")).sendKeys("trueLogin");
        driver.findElement(By.id("pass")).sendKeys("wrongPass");
        driver.findElement(By.id("submit")).click();
        Assert.assertEquals("text1", "text2");
    }

    @FindBy(id = "login")
    WebElement elementLogin;

    @FindBy(id = "password")
    WebElement elementPassword;

    @FindBy(id = "password")
    WebElement elementSubmit;

    public void loginTest(String login, String password) {
        elementLogin.sendKeys(login);
        elementPassword.sendKeys(password);
        elementSubmit.submit();
        Assert.assertEquals("text1", "text2");
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
