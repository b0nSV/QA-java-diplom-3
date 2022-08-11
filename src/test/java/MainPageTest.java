import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPageTest extends BasicDriverConfigurationTest {

    private final String INGREDIENTS_PANEL_FEATURE_TESTS_NAME = "Панель ингредиентов на главной странице";
    MainPage mainPage;

    @Before
    public void before() {
        mainPage = open("", MainPage.class);
        mainPage.getPageHeader().shouldBe(visible);
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    @DisplayName("Клик на \"Булки\" в заголовке панели ингредиентов прокручивает панель до булок")
    public void ingredientsPanelPressBunsScrollToBunsTest() {
        mainPage.scrollToLastIngredient();
        mainPage.clickBunHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Булки\""
                , mainPage.getBunInsideHeader().isDisplayed());
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    @DisplayName("Клик на \"Соусы\" в заголовке панели ингредиентов прокручивает панель до соусов")
    public void ingredientsPanelPressSaucesScrollToSaucesTest() {
        mainPage.scrollToLastIngredient();
        mainPage.clickSauceHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Соусы\""
                , mainPage.getSaucesInsideHeader().isDisplayed());
    }

    @Feature(INGREDIENTS_PANEL_FEATURE_TESTS_NAME)
    @Test
    @DisplayName("Клик на \"Начинки\" в заголовке панели ингредиентов прокручивает панель до начинок")
    public void ingredientsPanelPressFillingsScrollToFillingsTest() {
        mainPage.clickFillingHeader();

        assertThat("Не выполняется прокрутка до элементов раздела после клика по разделу \"Начинки\""
                , mainPage.getFillingInsideHeader().isDisplayed());
    }
}
