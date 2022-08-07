import io.qameta.allure.Feature;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPageTest extends BasicTestClass {

    private final String INGREDIENTS_PANEL_FEATURE_TESTS_NAME = "Панель ингредиентов на главной странице";
    MainPage mainPage;

    @Before
    public void before() {
        mainPage = open("", MainPage.class);
        mainPage.getPageHeader().shouldBe(visible);
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    public void ingredientsPanelPressBunsScrollToBunsTest() {
        mainPage.scrollToLastIngredient();
        mainPage.clickBunHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Булки\""
                , mainPage.getBunInsideHeader().isDisplayed());
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    public void ingredientsPanelPressSaucesScrollToSaucesTest() {
        mainPage.scrollToLastIngredient();
        mainPage.clickSauceHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Соусы\""
                , mainPage.getSaucesInsideHeader().isDisplayed());
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    public void ingredientsPanelPressFillingsScrollToFillingsTest() {
        mainPage.clickFillingHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Начинки\""
                , mainPage.getFillingInsideHeader().isDisplayed());
    }
}
