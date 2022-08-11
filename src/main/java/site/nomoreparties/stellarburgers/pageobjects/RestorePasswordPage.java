package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RestorePasswordPage {

    public static final String RESTORE_PASSWORD_PAGE_URL = "/forgot-password";

    public void waitForLoadRestorePasswordPage() {
        restorePasswordHeader.shouldBe(visible);
    }

    // Заголовок "Восстановление пароля"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Восстановление пароля']")
    private SelenideElement restorePasswordHeader;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    @Getter
    private SelenideElement loginButton;

    // Нажать кнопку "Войти"
    public MainPage clickLoginButton() {
        loginButton.click();
        var mainPage = page(MainPage.class);
        mainPage.waitForLoadMainPageAuthorized();
        return mainPage;
    }
}
