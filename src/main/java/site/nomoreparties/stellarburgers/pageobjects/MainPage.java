package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

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
    @Getter
    private SelenideElement loginButton;
}
