import org.junit.Test;

public class EmailTest extends WebDriverSettings{
    final String URL = "https://lgcity.ru/";
    EmailPage emailPage;

    String correctMail = "test@test.ru";
    String invalidMail = "dich$%^$%^";

    String correctMailText = "Спасибо! Ваша подписка успешно оформлена.";
    String invalidMailText = "Пожалуйста, введите корректный E-mail";
    String space = "Пожалуйста, введите E-mail";

    @Test
    public void Email(){
        emailPage = new EmailPage(driver, wait);
        open(URL);
        emailPage.email("", space);
        emailPage.email(correctMail, correctMailText);
        emailPage.email(invalidMail, invalidMailText);
    }
}
