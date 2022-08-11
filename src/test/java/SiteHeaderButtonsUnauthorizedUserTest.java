import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@Feature("Переход по кнопкам в шапке с разных стартовых страниц неавторизованным пользователем")
@RunWith(Parameterized.class)
public class SiteHeaderButtonsUnauthorizedUserTest extends BasicDriverConfigurationTest {
    private final String startPageUrl;
    private final SelenideElement buttonToClick;
    private final SelenideElement expectedElement;

    public SiteHeaderButtonsUnauthorizedUserTest(String startPageUrl, SelenideElement buttonToClick, SelenideElement expectedElement, String buttonNameForAllure) {
        this.startPageUrl = startPageUrl;
        this.buttonToClick = buttonToClick;
        this.expectedElement = expectedElement;
    }

    @Parameterized.Parameters(name = " URL начальной страницы = \"{0}\" | кнопка = {3} ")
    public static Object[][] getData() {
        return new Object[][]{
                {"", page(SiteHeader.class).getConstructorButton(), page(MainPage.class).getPageHeader()
                        , "Кнопка \"Конструктор\""},
                {"", page(SiteHeader.class).getSiteLogo(), page(MainPage.class).getPageHeader()
                        , "Кнопка - лого"},
                {"", page(SiteHeader.class).getPrivateOfficeButton(), page(LoginPage.class).getLoginButton()
                        , "Кнопка \"Войти\""},
        };
    }

    @Test
    public void loginFromDifferentPagesTest() {
        //given
        open(startPageUrl);
        //when
        buttonToClick.click();
        // then
        MatcherAssert.assertThat("Поле не найдено на странице", expectedElement.isDisplayed());
    }
}