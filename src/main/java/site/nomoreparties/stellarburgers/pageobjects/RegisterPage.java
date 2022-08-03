package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "/register";

    public void waitForLoadRegisterPage() {
        registerButton.shouldBe(visible);
    }

    // поле ввода "Имя"
    @FindBy(how = How.XPATH, using = ".//label[text()='Имя']")
    @Getter @Setter private SelenideElement nameField;

    // поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']")
    @Getter @Setter private SelenideElement emailField;

    // поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']")
    @Getter @Setter private SelenideElement passwordField;

    // Кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    // Сообщение о некорректном вводе в поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    @Getter private SelenideElement passwordErrorMessageField;

    // Нажать кнопку "Зарегистрироваться"
    public LoginPage clickRegisterButton() {
        registerButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;

    // Нажать кнопку "Войти"
    public LoginPage clickLoginButton() {
        loginButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
