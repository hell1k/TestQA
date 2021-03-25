import org.junit.Test;

public class CityTest extends WebDriverSettings{
    final String URL = "https://lgcity.ru/";
    CityPage cityPage;
    String city = "Новосибирск";

    @Test
    public void city() throws InterruptedException {
        cityPage = new CityPage(driver, wait);
        cityPage.open(URL);
        cityPage.city(city);
    }
}
