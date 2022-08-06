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

public class RegisterTest extends BasicTestClass {

    User user;
    RegisterPage registerPage;
    String accessToken;

    @Before
    public void before() {
        registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
    }

    @Test
    public void registerUserWithRequiredArgsRedirectToLoginPageTest() {
        // given
        user = new User(getRandomName(), getRandomEmail(), createRandomPassword(8));
        // when
        registerPage.fillRegisterForm(user);
        var loginPage = registerPage.clickRegisterButton();
        // then
        loginPage.getLoginButton().shouldBe(visible);
    }

    @Test
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
