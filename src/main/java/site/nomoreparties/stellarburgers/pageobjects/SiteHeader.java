package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class SiteHeader {

    // Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//ul[@class='AppHeader_header__list__3oKJj']/li[1]/a")
    private SelenideElement constructorButton;

    // Центральный логотип в шапке сайта
    @FindBy(how = How.CLASS_NAME, using = ".AppHeader_header__logo__2D0X2")
    private SelenideElement siteLogo;

    // Кнопка "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    @Getter
    private SelenideElement privateOfficeButton;

    // Нажать кнопку "Личный кабинет"
    public LoginPage clickPrivateOfficeButtonUnauthorized() {
        privateOfficeButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Нажать кнопку "Личный кабинет"
    public PrivateOfficePage clickPrivateOfficeButtonAuthorized() {
        privateOfficeButton.click();
        var privateOfficePage = page(PrivateOfficePage.class);
        privateOfficePage.waitForLoadPrivateOfficePage();
        return privateOfficePage;
    }
}
