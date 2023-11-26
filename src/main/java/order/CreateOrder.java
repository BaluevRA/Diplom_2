package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constant.Endpoints.*;
import static io.restassured.RestAssured.given;

public class CreateOrder {

    @Step("Получить ингредиенты")
    public static Response getIngredients() {
        return given()
                .get(BASE_URL + GET_INGREDIENTS);
    }

    @Step("Сделать заказ от авторизованного пользователя")
    public static Response createOrderWhileLoggedIn(Order order, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body(order)
                .post(BASE_URL + CREATE_ORDER);
    }

    @Step("Сделать заказ без авторизации")
    public static Response createOrdersNoAuth(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(BASE_URL + CREATE_ORDER);
    }

    @Step("Получить заказы пользователя с авторизацией")
    public static Response getOrdersLoggedInUser(String token) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .header("Authorization", token)
                .get(BASE_URL + CREATE_ORDER);
    }

    @Step("Получить заказы пользователя без авторизации")
    public static Response getOrdersNoLoggedInUser() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .get(BASE_URL + CREATE_ORDER);
    }
}
