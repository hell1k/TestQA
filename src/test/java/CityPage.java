import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CityPage {
    WebDriver driver;
    WebDriverWait wait;

    public CityPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='header-title-user-location']")
    WebElement cityInHeader;

    @FindBy(xpath = "//input[@id='input-user-locate']")
    WebElement cityInput;

    @FindBy(xpath = "//a[text()='Сохранить']")
    WebElement save;

    @FindBy(xpath = "//a[@class='locate__popular-list-item']")
    WebElement popularCity;

    public void open(String url) {
        driver.get(url);
    }

    public void city(String city) throws InterruptedException {
        cityInHeader.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Название населенного пункта']")));
        cityInput.clear();
        cityInput.sendKeys(city);
        save.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='header-title-user-location' and contains(text(), " + city + ")]")));
        Assert.assertEquals(cityInHeader.getText(), city);

        cityInHeader.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Название населенного пункта']")));
        String popular = popularCity.getText();
        popularCity.click();
        save.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='header-title-user-location']"), popular));
        Assert.assertEquals(cityInHeader.getText(), popular);
    }
}
