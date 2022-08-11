package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class PrivateOfficePage extends SiteHeader {
    public static final String PRIVATE_OFFICE_PAGE_URL = "/account/profile";

    // Кнопка "Профиль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Профиль']")
    @Getter
    private SelenideElement profileButtonHeader;

    // Описание раздела "Профиль"
    @FindBy(how = How.XPATH, using = ".//p[text()='В этом разделе вы можете изменить свои персональные данные']")
    private SelenideElement sectionsDescriptionText;

    // Кнопка "Выйти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;

    public void waitForLoadPrivateOfficePage() {
        profileButtonHeader.shouldBe(visible);
    }

    @Step("Нажать кнопку \"Выход\" в личном кабинете")
    public LoginPage clickExitButton() {
        exitButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
