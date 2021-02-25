import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LgCityPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public LgCityPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open() {
        driver.get("https://lgcity.ru/");
    }

    @FindBy(xpath = "//div[@id=\"menu-326\"]")
    private WebElement mens;

    public void mensClick() {
        mens.click();
    }

    @FindBy(xpath = "//*[@id=\"menu-333\"]")
    private WebElement shoes;

    By xpathMensShoes = By.xpath("//h1[contains(text(),\"Мужская обувь\")]");

    public void shoesClick() {
        shoes.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpathMensShoes));
    }

    public String amountBefore() {
        return driver.findElement(By.xpath("//div[@class=\"catalog__subtitle\"]")).getText();
    }

    public void filterClick() {
        driver.findElement(By.xpath("//div[@class=\"filter__title\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), \"Сбросить фильтры\")]")));
    }

    public void chooseAll() {
        driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[contains(@class, \"filter__item--open\")]")));
        driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[@class=\"filter__input-box\"]/following-sibling::div//button[contains(text(), \"Выбрать все\")]")).click();
        driver.findElement(By.xpath("//a[contains(text(), \"Распродажа\")]/ancestor::div[@class=\"filter__input-box\"]/following-sibling::div//button[contains(text(), \"Показать\")]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Мужская обувь, со скидками\")]")));
    }

    public String amountAfter() {
        return driver.findElement(By.xpath("//div[@class=\"catalog__subtitle\"]")).getText();
    }

    public void compareAmount(String amountBefore, String amountAfter) {
        Assert.assertNotEquals(amountBefore, amountAfter, "Количество товаров не изменилось");
    }

    public void outerwearClick() {
        driver.findElement(By.xpath("//*[@id=\"menu-331\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),\"Мужская верхняя одежда\")]")));
    }

    public void cardClick() {
        driver.findElement(By.xpath("//img[@num=\"0\"]")).click();
    }

    public String cardBefore() {
        return driver.findElement(By.xpath("//span[@class=\"card__info-desc\"]")).getText();
    }

    public void addFavorite() {
        driver.findElement(By.xpath("//div[contains(text(), \"Добавить в избранное\")]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), \"Товар в избранном\")]")));
    }

    public void favoriteClick() {
        driver.findElement(By.xpath("//a[@id=\"header-favorite\"]")).click();
    }

    public String cardAfter() {
        return driver.findElement(By.xpath("//div[@class=\"catalog__item-desc\"]")).getText();
    }

    public void compareCardInfo(String cardBefore, String cardAfter) {
        Assert.assertEquals(cardBefore, cardAfter, "Информация по товарам отличается");
    }

    public void removeFromFavorites(){
        driver.findElement(By.xpath("//div[@class=\"favorite__button\"]/*")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), \"Список понравившихся товаров пуст\")]")));
    }

}
