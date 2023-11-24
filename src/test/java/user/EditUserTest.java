package user;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static user.UserCredentials.credentials;

import io.qameta.allure.junit4.DisplayName;

public class EditUserTest extends BaseTestLogIn {

    @Test
    @DisplayName("Изменить данные пользователя с авторизацией")
    public void changeUserDataWithAuthTest() {
        createUser.authUser(credentials(user)).then().assertThat().statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
        User newUser = UserGenerator.createNewUser();
        CreateUser.changeUserWithAuth(token, newUser)
                .then()
                .assertThat().statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user", notNullValue());
    }

    @Test
    @DisplayName("Изменить данные пользователя без авторизации")
    public void changeUserDataNoAuthTest() {
        User newUser = UserGenerator.createNewUser();
        CreateUser.changeUserNoAuth(newUser)
                .then()
                .assertThat().statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));

    }
}


