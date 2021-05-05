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
        Actions actions = new Actions(driver);//объект для взаимодействия с клавиатурой
        driver.get("https://yandex.ru");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search2__placeholder")));//ожидание элемента по названию класса
        driver.findElement(By.name("text")).sendKeys("фильмы");
        driver.findElement(By.className("search2__button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Фильмы")));//ожидание по названию ссылки
        driver.findElement(By.xpath("//b[contains(text(), 'kinopoisk')]/../self::a")).click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());//конструкция для получения количества открытых вкладок
        driver.switchTo().window(tabs.get(1));//переключение на другую вкладку

        String currentUrl = driver.getCurrentUrl();//получение текущего адреса

        if (currentUrl.contains("hd.kinopoisk.ru")) {//проверка, что текущий адрес не содержит адреса в скобках
            driver.get("https://www.kinopoisk.ru/popular/films/?tab=all");//если же содержит, то перенаправление на нужный адрес
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Популярные фильмы и сериалы')]")));
        WebElement rating = driver.findElement(By.xpath("//a[contains(text(), \"С высоким рейтингом\")]"));
        rating.click();
        String filter = rating.getAttribute("class");//получение значения атрибута class

        if (!filter.contains("quick-filter-item")) {//проверка, если значение атрибута не содержит данных из скобок
            Assert.fail("Класс не содержит данного текста, фильтр не активен");//заваливаем тест с сообщением
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), \"Все страны\")]")));//проверка видимости элемента
        driver.findElement(By.xpath("//span[contains(text(), \"Все страны\")]")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='selections-select__dropdown']")), "Популярные"));//проверка, что элемент содержит текст

        driver.findElement(By.xpath("//span[contains(text(), \"Популярные\")]")).click();//клик по блоку div, чтобы сделать фокус на нем
        actions.sendKeys(Keys.PAGE_DOWN).perform();//теперь взаимодействие с этим блоком клавиатурой
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        driver.findElement(By.xpath("//a[contains(text(), 'Барбадос')]")).click();

        WebElement message = driver.findElement(By.xpath("//h1[@class='error-message__title']"));
        Assert.assertTrue(message.isDisplayed());//проверка, что объект на экране

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"selections-select__button-caption\" and contains(text(), \"Барбадос\")]")));

        Thread.sleep(5000);//просто задержка для просмотра
    }
}
