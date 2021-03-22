import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByPricePage {
    WebDriver driver;
    WebDriverWait wait;

    public SortByPricePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='catalog__item-price']")
    WebElement goods;

    @FindBy(xpath = "//button[contains(text(), 'По новинкам') and contains(@class, 'item--active')]")
    WebElement novelty;

    @FindBy(xpath = "//button[contains(text(), 'По возрастанию цены')]")
    WebElement increaseInPrice;

    @FindBy(xpath = "//button[contains(text(), 'По убыванию цены')]")
    WebElement decreaseInPrice;

    @FindBy(xpath = "//h1")
    WebElement catalogTitle;

    @FindBy(xpath = "//div[@class='catalog__subtitle']")
    WebElement goodsAmount;

    public void open(String url) {
        driver.get(url);
    }

    public void sortingByPrice(String increaseOrDecrease) {
        int amount = Integer.parseInt(goodsAmount.getText().replaceAll("[^\\d.]", ""));
        List<Integer> priceList = new ArrayList<Integer>();

        String attribute;
        String listPrice;

        for (int i = 1; i <= amount; i++) {
            attribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]")).getAttribute("itemprop");

            if (attribute.equals("offers")){
                listPrice = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]//div[@class='catalog__item-price-new']")).getText().replaceAll("[^\\d.]", "");
                int priceInt = Integer.parseInt(listPrice);
                priceList.add(priceInt);
            } else {
                listPrice = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]")).getText().replaceAll("[^\\d.]", "");
                int priceInt = Integer.parseInt(listPrice);
                priceList.add(priceInt);
            }
        }

        int minPrice = Collections.min(priceList);
        int maxPrice = Collections.max(priceList);

        novelty.click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='catalog__sort-list']/../self::div"), "class", "sort--open"));

        if (increaseOrDecrease.equals("increase")) {
            increaseInPrice.click();
            Assert.assertTrue(catalogTitle.getText().contains("недорого"), "Несоответствие");
        } else {
            decreaseInPrice.click();
            Assert.assertTrue(catalogTitle.getText().contains("премиум"), "Несоответствие");
        }

        String firstAttribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[1]")).getAttribute("itemprop");
        String lastAttribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amount + "]")).getAttribute("itemprop");

        int first;

        if (firstAttribute.equals("offers")) {
            first = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price-new'])[1]")).getText().replaceAll("[^\\d.]", ""));
        } else {
            first = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[1]")).getText().replaceAll("[^\\d.]", ""));
        }

        int last;

        if (lastAttribute.equals("offers")) {
            last = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amount + "]//div[@class='catalog__item-price-new']")).getText().replaceAll("[^\\d.]", ""));
        } else {
            last = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amount + "]")).getText().replaceAll("[^\\d.]", ""));
        }

        if (increaseOrDecrease.equals("increase")) {
            Assert.assertEquals(first, minPrice);
            Assert.assertEquals(last, maxPrice);
        } else {
            Assert.assertEquals(first, maxPrice);
            Assert.assertEquals(last, minPrice);
        }
    }
}
