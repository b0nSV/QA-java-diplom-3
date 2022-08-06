package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import site.nomoreparties.stellarburgers.dto.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "/login";

    // поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//form/fieldset[1]//input")
    private SelenideElement emailField;

    // поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//form/fieldset[2]//input")
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

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Заполнить форму входа")
    public void fillLoginForm(User user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
    }

    @Step("Нажать кнопку \"Войти\"")
    public MainPage clickLoginButton() {
        loginButton.click();
        var mainPage = page(MainPage.class);
        mainPage.waitForLoadMainPageAuthorized();
        return mainPage;
    }

    @Step("Нажать кнопку \"Восстановить пароль\"")
    public RestorePasswordPage clickRestorePasswordButton() {
        restorePasswordButton.click();
        return page(RestorePasswordPage.class);
    }
}
