package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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
    @Getter
    private SelenideElement createOrderButton;

    // Кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    @Getter
    private SelenideElement loginButton;

    // Заголовок страницы
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    @Getter
    private SelenideElement pageHeader;

    // Заголовок "Булки" внутри панели ингредиентов
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    @Getter
    private SelenideElement bunInsideHeader;

    // Заголовок "Соусы" внутри панели ингредиентов
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    @Getter
    private SelenideElement saucesInsideHeader;

    // Заголовок "Начинки" внутри панели ингредиентов
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    @Getter
    private SelenideElement fillingInsideHeader;

    // Последний элемент в панели ингредиентов, раздела "Начинки"
    @FindBy(how = How.XPATH, using = "(.//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul[3]/a)[last()]")
    @Getter
    private SelenideElement fillingLastElement;

    // Заголовок раздела "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']/..")
    @Getter
    private SelenideElement bunHeader;

    // Заголовок раздела "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    @Getter
    private SelenideElement sauceHeader;

    // Заголовок раздела "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    @Getter
    private SelenideElement fillingHeader;

    @Step("Прокрутить панель ингредиентов в самый низ")
    public void scrollToLastIngredient() {
        fillingLastElement.scrollIntoView(true);
    }

    @Step("Нажать на заголовок раздела \"Булки\"")
    public void clickBunHeader() {
        bunHeader.click();
    }

    @Step("Нажать на заголовок раздела \"Соусы\"")
    public void clickSauceHeader() {
        sauceHeader.click();
    }

    @Step("Нажать на заголовок раздела \"Начинки\"")
    public void clickFillingHeader() {
        fillingHeader.click();
    }
}
