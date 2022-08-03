package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "/login";

    public void waitForLoadLoginPage() {
        loginButton.shouldBe(visible);
    }

    // поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']")
    @Getter
    @Setter
    private SelenideElement emailField;

    // поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']")
    @Getter
    @Setter
    private SelenideElement passwordField;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    // Нажать кнопку "Войти"
    public MainPage clickLoginButton() {
        loginButton.click();
        var mainPage = page(MainPage.class);
        mainPage.waitForLoadMainPageAuthorized();
        return mainPage;
    }

    // Кнопка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordButton;

    // Нажать кнопку "Восстановить пароль"
    public RestorePasswordPage clickRestorePasswordButton() {
        restorePasswordButton.click();
        var restorePasswordPage = page(RestorePasswordPage.class);
        restorePasswordPage.waitForLoadRestorePasswordPage();
        return restorePasswordPage;
    }
}
