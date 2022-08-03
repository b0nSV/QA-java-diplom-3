package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public void waitForLoadMainPageAuthorized() {
        createOrderButton.shouldBe(visible);
    }

    public void waitForLoadMainPageNotAuthorized() {
        loginButton.shouldBe(visible);
    }

    // Кнопка "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    // Кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    // Кнопка "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement privateOfficeButton;
}
