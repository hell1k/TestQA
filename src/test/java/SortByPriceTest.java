import org.junit.Test;

public class SortByPriceTest extends WebDriverSettings {
    final String URL = "https://lgcity.ru/shoes/keds/women/";
    SortByPricePage sortByPricePage;

    @Test
    public void sortByPrice(){
        sortByPricePage = new SortByPricePage(driver, wait);
        sortByPricePage.open(URL);
        sortByPricePage.sortingByPrice("decrease");
    }
}