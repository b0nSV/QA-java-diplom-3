package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class SiteHeader {

    // Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//ul[@class='AppHeader_header__list__3oKJj']/li[1]/a")
    @Getter
    private SelenideElement constructorButton;

    // Центральный логотип в шапке сайта
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    @Getter
    private SelenideElement siteLogo;

    // Кнопка "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    @Getter
    private SelenideElement privateOfficeButton;

    @Step("Нажать кнопку \"Конструктор\"")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        var mainPage = page(MainPage.class);
        mainPage.getPageHeader().shouldBe(Condition.visible);
        return mainPage;
    }

    @Step("Нажать на лого в шапке сайте")
    public MainPage clickSiteLogo() {
        siteLogo.click();
        var mainPage = page(MainPage.class);
        mainPage.getPageHeader().shouldBe(Condition.visible);
        return mainPage;
    }

    @Step("Нажать кнопку \"Личный кабинет\" без авторизации")
    public LoginPage clickPrivateOfficeButtonUnauthorized() {
        privateOfficeButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Нажать кнопку \"Личный кабинет\" будучи авторизованным")
    public PrivateOfficePage clickPrivateOfficeButtonAuthorized() {
        privateOfficeButton.click();
        var privateOfficePage = page(PrivateOfficePage.class);
        privateOfficePage.waitForLoadPrivateOfficePage();
        return privateOfficePage;
    }
}
