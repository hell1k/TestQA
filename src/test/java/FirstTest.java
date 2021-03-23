import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*^^^подключаемые библиотеки^^^ программа сама подсказывает, когда надо поключать*/

public class FirstTest {//объявление класса(имя класса должно совпадать с именем файла)
    public WebDriver driver;//объявление драйвера публично, чтобы пользоваться во всем классе
    public String baseUrl;//публичная переменная имени сайта

    @BeforeTest
    public void before() {//метод, в котором происходит что то до старта тестов (аннотация @Before)
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//путь к хромдрайверу(лежит в корне, поэтому просто название)
        driver = new ChromeDriver();//и нициализируем драйвер
        baseUrl = "https://yandex.ru";//инициализация переменной имени сайта
        driver.manage().window().maximize();//делаем браузер на весь экран
    }

    @Test

    public void first() {//метод, в котором идет сам тест (аннотация @Test)
        driver.get(baseUrl);//команда get откроет сайт, который хранит переменная baseUrl
    }

    @AfterTest
    public void quit() {//метод, в котором происходит что то после отработки тестов (аннотация @After)
        driver.quit();//выключаем драйвер и закрываем все связанные с ним окна
    }
}
