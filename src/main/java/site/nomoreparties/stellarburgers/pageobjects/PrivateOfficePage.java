package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class PrivateOfficePage {
    public static final String PRIVATE_OFFICE_PAGE_URL = "/account/profile";

    public void waitForLoadPrivateOfficePage() {
        profileButtonHeader.shouldBe(visible);
    }

    // Кнопка "Профиль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Профиль']")
    private SelenideElement profileButtonHeader;

    // Описание раздела "Профиль"
    @FindBy(how = How.XPATH, using = ".//p[text()='В этом разделе вы можете изменить свои персональные данные']")
    private SelenideElement sectionsDescriptionText;

    // Кнопка "Выйти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;

    public LoginPage clickExitButton() {
        exitButton.click();
        var loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}
