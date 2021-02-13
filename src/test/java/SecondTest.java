import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class SecondTest extends WebDriverSettings {
    @Test
    public void firstTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://yandex.ru");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search2__placeholder")));
        WebElement search = driver.findElement(By.name("text"));
        search.sendKeys("фильмы");
        WebElement clickSearch = driver.findElement(By.className("search2__button"));
        clickSearch.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Фильмы")));
        WebElement link = driver.findElement(By.xpath("//b[contains(text(), 'kinopoisk')]/../self::a"));
        link.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Популярные фильмы и сериалы')]")));
        WebElement rating = driver.findElement(By.xpath("//a[contains(text(), \"С высоким рейтингом\")]"));
        rating.click();
        String filter = rating.getAttribute("class");

        if (!filter.contains("quick-filter-item")) {
            Assert.fail("Класс не содержит данного текста, фильтр не активен");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), \"Все страны\")]")));
        driver.findElement(By.xpath("//span[contains(text(), \"Все страны\")]")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='selections-select__dropdown']")), "Популярные"));

        driver.findElement(By.xpath("//span[contains(text(), \"Популярные\")]")).click();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        driver.findElement(By.xpath("//a[contains(text(), 'Барбадос')]")).click();

        WebElement message = driver.findElement(By.xpath("//h1[@class='error-message__title']"));
        Assert.assertTrue(message.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"selections-select__button-caption\" and contains(text(), \"Барбадос\")]")));

        Thread.sleep(5000);
    }
}
