package user;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public class BaseTestLogIn {
    public CreateUser createUser;
    public User user;
    public String token;

    @Before
    public void setup() {

        createUser = new CreateUser();
        user = UserGenerator.createNewUser();
        Response response = createUser.createUser(user);
        token = response.then().extract().path("accessToken").toString();

    }

    @After
    public void tearDown() {
        CreateUser.deleteUser(token);
    }

}
