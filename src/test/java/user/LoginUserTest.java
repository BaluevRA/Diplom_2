package user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static user.UserCredentials.credentials;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

public class LoginUserTest extends BaseTestLogIn {
    @Test
    @DisplayName("Авторизоваться с валидными данными")
    public void userLoginTest() {
        createUser.authUser(credentials(user)).then().assertThat().statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Авторизоваться с некорректным паролем")
    public void userLoginWithIncorrectPasswordTest() {
        UserCredentials loginUserPasswordIncorrectFail = new UserCredentials(user.getEmail(), "7577676558676765876758585");
        createUser.authUser(loginUserPasswordIncorrectFail).then().assertThat().statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Авторизоваться с некорректным email")
    public void userLoginWithIncorrectEmailTest() {
        UserCredentials loginUserPasswordIncorrectFail = new UserCredentials("jhhjghgjghghjghjgjhgjgjhgjhjgjgj", user.getPassword());
        createUser.authUser(loginUserPasswordIncorrectFail).then().assertThat().statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));

    }
}


