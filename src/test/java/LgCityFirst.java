import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class LgCityFirst extends WebDriverSettings {
    @Test
    public void firstTest() throws InterruptedException {
        driver.get("https://lgcity.ru/");
        driver.findElement(By.xpath("//div[@id=\"menu-326\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"menu-333\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Мужская обувь\")]")));
        String amountBefore = driver.findElement(By.xpath("//div[@class=\"catalog__subtitle\"]")).getText();
        driver.findElement(By.xpath("//div[@class=\"filter__title\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), \"Сбросить фильтры\")]")));
        driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[contains(@class, \"filter__item--open\")]")));
        WebElement sale = driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[@class=\"filter__input-box\"]/following-sibling::div//button[contains(text(), \"Выбрать все\")]"));
        sale.click();
        driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[@class=\"filter__input-box\"]/following-sibling::div//button[contains(text(), \"Показать\")]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Мужская обувь, со скидками\")]")));
        String amountAfter = driver.findElement(By.xpath("//div[@class=\"catalog__subtitle\"]")).getText();

        if (amountAfter.equals(amountBefore)){
            Assert.fail("Количество товаров не изменилось");
        }

        driver.findElement(By.xpath("//*[@id=\"menu-331\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Мужская верхняя одежда\")]")));
        driver.findElement(By.xpath("//img[@num=\"0\"]")).click();
        String cardBefore = driver.findElement(By.xpath("//span[@class=\"card__info-desc\"]")).getText();
        driver.findElement(By.xpath("//div[contains(text(), \"Добавить в избранное\")]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), \"Товар в избранном\")]")));
        driver.findElement(By.xpath("//a[@id=\"header-favorite\"]")).click();
        String cardAfter = driver.findElement(By.xpath("//div[@class=\"catalog__item-desc\"]")).getText();

        if (!cardAfter.equals(cardBefore)){
            Assert.fail("Карточки не совпали");
        }

        driver.findElement(By.xpath("//div[@class=\"favorite__button\"]/*")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), \"Список понравившихся товаров пуст\")]")));

        Thread.sleep(5000);//просто задержка для просмотра
    }
}