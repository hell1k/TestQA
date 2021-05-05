import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class YandexScr {
    public WebDriver driver;
    public String baseUrl;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://yandex.ru";
        driver.manage().window().maximize();
    }

    @Test
    public void first() throws IOException {
        driver.get(baseUrl);
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        for (int i = 0; i <= 10; i++) {
            WebElement searchField = driver.findElement(By.xpath("//input[@name='text']"));
            WebElement searchButton = driver.findElement(By.cssSelector(".search2__button"));
            searchField.clear();
            searchField.sendKeys("" + i);
            searchButton.click();
            File file = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("screenshot/" + i + ".jpg"));
        }
    }

    @After
    public void quit() {
        driver.quit();
    }
}
