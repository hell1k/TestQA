import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class LgCityFirst extends WebDriverSettings {
    @Test
    public void firstTest(){
        LgCityPage lgCityPage = PageFactory.initElements(driver, LgCityPage.class);
        lgCityPage.open();
        lgCityPage.mensClick();
        lgCityPage.shoesClick();
        String amountBefore = lgCityPage.amountBefore();
        lgCityPage.filterClick();
        lgCityPage.chooseAll();
        String amountAfter = lgCityPage.amountAfter();
        lgCityPage.compareAmount(amountBefore, amountAfter);
        lgCityPage.outerwearClick();
        lgCityPage.cardClick();
        String cardBefore = lgCityPage.cardBefore();
        lgCityPage.addFavorite();
        lgCityPage.favoriteClick();
        String cardAfter = lgCityPage.cardAfter();
        lgCityPage.compareCardInfo(cardBefore, cardAfter);
        lgCityPage.removeFromFavorites();
    }
}