import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage {
    WebDriver driver;
    WebDriverWait wait;

    public EmailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='EMAIL']")
    WebElement emailInput;

    @FindBy(xpath = "//button[@id='subscribe-submit']")
    WebElement subscribe;

    public void email(String email, String text) {
        emailInput.clear();
        emailInput.sendKeys(email);
        subscribe.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//label[@id='EMAIL-error']"), text));
    }
}
