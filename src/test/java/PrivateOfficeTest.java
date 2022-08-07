
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.pageobjects.PrivateOfficePage;
import site.nomoreparties.stellarburgers.pageobjects.SiteHeader;

import static org.hamcrest.MatcherAssert.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.nomoreparties.stellarburgers.helpers.RandomSequences.*;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.UserSteps.*;
import static site.nomoreparties.stellarburgers.pageobjects.LoginPage.LOGIN_PAGE_URL;
import static site.nomoreparties.stellarburgers.pageobjects.PrivateOfficePage.PRIVATE_OFFICE_PAGE_URL;

public class PrivateOfficeTest extends BasicTestClass {

    private User user;

    @Before
    public void before() {
        user = new User(getRandomName(), getRandomEmail(), createRandomPassword());
        registerUser(user);
        var loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillLoginForm(user);
        loginPage.clickLoginButton();
    }

    @Test
    public void mainPageClickPrivateOfficeButtonAuthorizedOpenPrivateOfficeTest() {
        var privateOffice = page(SiteHeader.class).clickPrivateOfficeButtonAuthorized();
        assertThat("Не найдена кнопка \"Профиль\"", privateOffice.getProfileButtonHeader().isDisplayed());
    }

    @Test
    public void privateOfficePageClickConstructorOpenMainPageTest() {
        var privateOfficePage = open(PRIVATE_OFFICE_PAGE_URL, PrivateOfficePage.class);
        var mainPage = privateOfficePage.clickConstructorButton();
        assertThat("При переходе по кнопке конструктор из личного кабинета не открылась главная страница"
                , mainPage.getPageHeader().isDisplayed());
    }

    @Test
    public void privateOfficePageClickBurgerLogoOpenMainPageTest() {
        var privateOfficePage = open(PRIVATE_OFFICE_PAGE_URL, PrivateOfficePage.class);
        var mainPage = privateOfficePage.clickSiteLogo();
        assertThat("При переходе по клику на лого из личного кабинета не открылась главная страница"
                , mainPage.getPageHeader().isDisplayed());
    }

    @Test
    public void privateOfficePageClickExitButtonOpenLoginPageTest() {
        var privateOfficePage = open(PRIVATE_OFFICE_PAGE_URL, PrivateOfficePage.class);
        var loginPage = privateOfficePage.clickExitButton();
        assertThat("При переходе по клику на \"Выход\" из личного кабинета не открылась страница входа"
                , loginPage.getLoginButton().isDisplayed());
    }

    @After
    public void after() {
        var accessToken = loginUser(user);
        if (accessToken != null) deleteUser(accessToken);
    }
}
