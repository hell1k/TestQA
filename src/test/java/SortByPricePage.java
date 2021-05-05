import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    /*
     * Проверка сортировки цен по убыванию/возрастанию
     *
     */
    public void sortingByPrice(String increaseOrDecrease) {
        novelty.click();//клик по меню сортировки
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class='catalog__sort-list']/../self::div"), "class", "sort--open"));//проверка, что меню открылось

        //клик по необходимой сортировке из меню, проверка, что загрузилась необходимая страница
        if (increaseOrDecrease.equals("increase")) {
            increaseInPrice.click();
          //  Assert.assertTrue(catalogTitle.getText().contains("недорого"), "Несоответствие в заголовке страницы");
        } else {
            decreaseInPrice.click();
           // Assert.assertTrue(catalogTitle.getText().contains("премиум"), "Несоответствие в заголовке страницы");
        }

        //получаем количество карточек товара на странице
        int amountCardsInPage = driver.findElements(By.xpath("//div[@class='catalog__item-price']")).size();

        //список, в который будем собирать цены
        List<Integer> priceList = new ArrayList<Integer>();
        String attribute;
        String price;
        int priceInt;

        //цикл для перебора карточек на странице, проверка есть ли у них скидка и добавление их в список
        for (int i = 1; i <= amountCardsInPage; i++) {
            attribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]")).getAttribute("itemprop");

            if (attribute.equals("offers")) {
                price = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]//div[@class='catalog__item-price-new']")).getText().replaceAll("[^\\d.]", "");
            } else {
                price = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + i + "]")).getText().replaceAll("[^\\d.]", "");
            }

            priceInt = Integer.parseInt(price);
            priceList.add(priceInt);
        }

        //получение атрибута первой и последней карточки для дальнейшей проверки есть ли у карточки скидка
        String firstAttribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[1]")).getAttribute("itemprop");
        String lastAttribute = driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amountCardsInPage + "]")).getAttribute("itemprop");

        //получение цен первой и последней карточки
        int first;

        if (firstAttribute.equals("offers")) {
            first = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price-new'])[1]")).getText().replaceAll("[^\\d.]", ""));
        } else {
            first = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[1]")).getText().replaceAll("[^\\d.]", ""));
        }

        int last;

        if (lastAttribute.equals("offers")) {
            last = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amountCardsInPage + "]//div[@class='catalog__item-price-new']")).getText().replaceAll("[^\\d.]", ""));
        } else {
            last = Integer.parseInt(driver.findElement(By.xpath("(//div[@class='catalog__item-price'])[" + amountCardsInPage + "]")).getText().replaceAll("[^\\d.]", ""));
        }

        //получение минимальной и максимальной цены из всего списка карточек на странице
        int minPrice = Collections.min(priceList);
        int maxPrice = Collections.max(priceList);

        //сравнение мин/макс цены с первой и последней ценой карточки на странице
        if (increaseOrDecrease.equals("increase")) {
            Assert.assertEquals(first, minPrice);
            Assert.assertEquals(last, maxPrice);
        } else {
            Assert.assertEquals(first, maxPrice);
            Assert.assertEquals(last, minPrice);
        }
    }
}
