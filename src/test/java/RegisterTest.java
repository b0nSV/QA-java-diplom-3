import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobjects.RegisterPage;
import site.nomoreparties.stellarburgers.dto.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.containsString;
import static site.nomoreparties.stellarburgers.helpers.RandomSequences.*;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.UserSteps.deleteUser;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.UserSteps.loginUser;
import static site.nomoreparties.stellarburgers.pageobjects.RegisterPage.REGISTER_PAGE_URL;
import static org.hamcrest.MatcherAssert.*;

@Feature("Регистрация пользователя")
public class RegisterTest extends BasicDriverConfigurationTest {

    User user;
    RegisterPage registerPage;
    String accessToken;

    @Before
    public void before() {
        registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
    }

    @Test
    @DisplayName("После успешной регистрации происходит перенаправление на страницу входа")
    public void registerUserWithRequiredArgsRedirectToLoginPageTest() {
        // given
        user = new User(getRandomName(), getRandomEmail(), createRandomPassword());
        // when
        registerPage.fillRegisterForm(user);
        var loginPage = registerPage.clickRegisterButton();
        // then
        loginPage.getLoginButton().shouldBe(visible);
    }

    @Test
    @DisplayName("При попытке регистрации с меньше допустимой длинной пароля появляется сообщение об ошибке")
    public void registerUserWithLessThenAcceptedLengthPasswordDisplayError() {
        // given
        user = new User(getRandomName(), getRandomEmail(), createRandomPassword(3));
        // when
        registerPage.fillRegisterForm(user);
        registerPage.clickRegisterButton();
        // then
        registerPage.getPasswordErrorMessage().shouldBe(visible);
        assertThat(registerPage.getPasswordErrorMessage().getText(), containsString("Некорректный пароль"));
    }

    @After
    public void after() {
        accessToken = loginUser(user);
        if (accessToken != null) deleteUser(accessToken);
    }
}
