package user;

import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public CreateUser createUser;
    String token;

    @Before
    public void setup() {
        createUser = new CreateUser();

    }

    @After
    public void tearDown() {
        CreateUser.deleteUser(token);
    }
}

