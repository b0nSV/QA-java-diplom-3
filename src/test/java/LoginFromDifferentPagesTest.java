import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static site.nomoreparties.stellarburgers.helpers.RandomSequences.*;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.UserSteps.deleteUser;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.UserSteps.registerUser;
import static site.nomoreparties.stellarburgers.pageobjects.RegisterPage.REGISTER_PAGE_URL;
import static site.nomoreparties.stellarburgers.pageobjects.RestorePasswordPage.RESTORE_PASSWORD_PAGE_URL;

@Feature("Выполнения входа с разных стартовых страниц")
@RunWith(Parameterized.class)
public class LoginFromDifferentPagesTest extends BasicTestClass {
    private final String startPageUrl;
    private final SelenideElement button;

    public LoginFromDifferentPagesTest(String startPageUrl, SelenideElement button, String buttonNameForAllure) {
        this.startPageUrl = startPageUrl;
        this.button = button;
    }

    @Parameterized.Parameters(name = " URL начальной страницы = \"{0}\" | кнопка = {2} ")
    public static Object[][] getData() {
        return new Object[][]{
                {"", page(SiteHeader.class).getPrivateOfficeButton()
                        , "Кнопка \"Личный кабинет в хедере\""},
                {"", page(MainPage.class).getLoginButton()
                        , "Кнопка \"Войти в аккаунт\" на главной странице"},
                {REGISTER_PAGE_URL, page(RegisterPage.class).getLoginButton()
                        , "Кнопка \"Войти\" на странице регистрации"},
                {RESTORE_PASSWORD_PAGE_URL, page(RestorePasswordPage.class).getLoginButton()
                        , "Кнопка \"Войти\" на странице восстановления пароля"},
        };
    }

    @Test
    public void loginFromDifferentPagesTest() {
        //given
        var user = new User(getRandomName(), getRandomEmail(), createRandomPassword());
        var accessToken = registerUser(user);
        open(startPageUrl);
        //when
        button.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        loginPage.fillLoginForm(user);
        loginPage.clickLoginButton();
        // then
        assertThat("Кнопка оформить заказ не отображается на странице"
                , page(MainPage.class).getCreateOrderButton().isDisplayed());
        // postcondition
        deleteUser(accessToken);
    }
}
