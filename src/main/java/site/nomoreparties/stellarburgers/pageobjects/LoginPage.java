package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "/login";

    // поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']")
    private SelenideElement emailField;

    // поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']")
    private SelenideElement passwordField;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    @Getter
    private SelenideElement loginButton;

    // Кнопка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordButton;

    public void waitForLoadLoginPage() {
        loginButton.shouldBe(visible);
    }

    // Нажать кнопку "Войти"
    public MainPage clickLoginButton() {
        loginButton.click();
        var mainPage = page(MainPage.class);
        mainPage.waitForLoadMainPageAuthorized();
        return mainPage;
    }

    // Нажать кнопку "Восстановить пароль"
    public RestorePasswordPage clickRestorePasswordButton() {
        restorePasswordButton.click();
        return page(RestorePasswordPage.class);
    }
}
