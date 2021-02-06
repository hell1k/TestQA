import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public WebDriver driver;//объявление драйвера публично, чтобы пользоваться во всем классе
    public String baseUrl;//публичная переменная имени сайта

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//путь к хромдрайверу(лежит в корне, поэтому просто название)
        driver = new ChromeDriver();//инициализируем драйвер
        baseUrl = "https://yandex.ru";//инициализация переменной имени сайта
        driver.manage().window().maximize();//делаем браузер на весь экран
    }

    @Test
    public void first() {
        driver.get(baseUrl);//команда get откроет сайт, который хранит переменная baseUrl
    }

    @After
    public void quit() {
        driver.quit();//выключаем драйвер и закрываем все связанные с ним окна
    }
}
