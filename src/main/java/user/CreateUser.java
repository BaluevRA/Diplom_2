package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constant.Endpoints.*;
import static io.restassured.RestAssured.given;

public class CreateUser {
    @Step("Создать пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + CREATE_USER);
    }

    @Step("Авторизоваться под созданным пользователем")
    public static Response authUser(UserCredentials creds) {
        return given()
                .header("Content-type", "application/json")
                .body(creds)
                .post(BASE_URL + USER_LOGIN);
    }

    @Step("Изменить данные авторизованного пользователя")
    public static Response changeUserWithAuth(String token, User user) {
        return given()
                .header("Authorization", token)
                .body(user)
                .patch(BASE_URL + CHANGE_DELETE_USER);
    }

    @Step("Изменить данные пользователя без авторизации")
    public static Response changeUserNoAuth(User user) {
        return given()
                .body(user)
                .patch(BASE_URL + CHANGE_DELETE_USER);

    }

    @Step("Удалить пользователя")
    public static void deleteUser(String token) {
        if (token != null) {
            given()
                    .header("Authorization", token)
                    .delete(BASE_URL + CHANGE_DELETE_USER);

        }

    }
}

