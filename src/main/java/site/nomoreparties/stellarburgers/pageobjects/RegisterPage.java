package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import site.nomoreparties.stellarburgers.dto.User;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "/register";

    // поле ввода "Имя"
    @FindBy(how = How.XPATH, using = ".//form/fieldset[1]//input")
    private SelenideElement nameField;

    // поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//form/fieldset[2]//input")
    private SelenideElement emailField;

    // поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//form/fieldset[3]//input")
    private SelenideElement passwordField;

    // Кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    @Getter
    private SelenideElement loginButton;

    // Сообщение о некорректном вводе в поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    @Getter
    private SelenideElement passwordErrorMessage;

    public void waitForLoadRegisterPage() {
        registerButton.shouldBe(visible);
    }

    public void setName(String name) {
        nameField.setValue(name);
    }

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Заполнить форму регистрации")
    public void fillRegisterForm(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return page(LoginPage.class);
    }

    @Step("Нажать кнопку \"Войти\" внизу страницы")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
