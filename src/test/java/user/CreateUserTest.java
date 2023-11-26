package user;

import org.junit.Test;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.junit4.DisplayName;

import static org.apache.http.HttpStatus.*;

public class CreateUserTest extends BaseTest {

    private User user;

    @Test
    @DisplayName("Создать нового пользователя")
    public void createNewUserTest() {

        user = UserGenerator.createNewUser();
        Response response = CreateUser.createUser(user);
        token = response.then().extract().path("accessToken").toString();
        response.then().assertThat().statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

    }

    @Test
    @DisplayName("Создать пользователя без email")
    public void createUserWithNoEmailTest() {
        user = UserGenerator.createCourierWithNoEmail();
        Response response = CreateUser.createUser(user);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создать пользователя без пароля")
    public void createUserWithNoPasswordTest() {
        user = UserGenerator.createCourierWithNoPassword();
        Response response = CreateUser.createUser(user);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создать пользователя без имени")
    public void createUserWithNoNameTest() {
        user = UserGenerator.createCourierWithNoName();
        Response response = CreateUser.createUser(user);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создать пользователя, который уже был зарегистрирован")
    public void createAlreadyCreatedUserTest() {
        user = UserGenerator.createNewUser();
        CreateUser.createUser(user);
        Response response = CreateUser.createUser(user);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
    }

}