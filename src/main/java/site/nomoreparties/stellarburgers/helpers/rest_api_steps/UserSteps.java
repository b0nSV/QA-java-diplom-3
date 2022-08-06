package site.nomoreparties.stellarburgers.helpers.rest_api_steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.dto.UserCredentials;
import site.nomoreparties.stellarburgers.dto.UserLoginResponse;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.helpers.rest_api_steps.BaseApiSpecs.*;

public class UserSteps {

    public static final String REGISTER_COURIER_URL = "/auth/register";
    public static final String LOGIN_COURIER_URL = "/auth/login";
    public static final String DELETE_COURIER_URL = "/auth/user";

    @Step("API. Зарегистрировать пользователя")
    public static String registerUser(User user) {
        var response = given()
                .spec(getPostReqSpec())
                .and()
                .body(user)
                .when()
                .post(BASE_URL + REGISTER_COURIER_URL);
        return response.as(UserLoginResponse.class).getAccessToken();
    }

    @Step("API. Получить токен, выполнив вход")
    public static String loginUser(User user) {
        var userCredentials = new UserCredentials(user);
        var response = given()
                .spec(getPostReqSpec())
                .and()
                .body(userCredentials)
                .when()
                .post(BASE_URL + LOGIN_COURIER_URL);
        return response.as(UserLoginResponse.class).getAccessToken();
    }

    @Step("API. Удалить пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .spec(getDeleteReqSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + DELETE_COURIER_URL);
    }
}
