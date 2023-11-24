package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User createNewUser() {
        return new User()
                .withEmail(RandomStringUtils.randomAlphabetic(8) + "@gmail.com")
                .withPassword(RandomStringUtils.randomAlphabetic(10))
                .withName(RandomStringUtils.randomAlphabetic(7));
    }

    public static User createCourierWithNoEmail() {
        return new User()
                .withPassword(RandomStringUtils.randomAlphabetic(7))
                .withName(RandomStringUtils.randomAlphabetic(7));
    }

    public static User createCourierWithNoPassword() {
        return new User()
                .withEmail(RandomStringUtils.randomAlphabetic(8) + "@gmail.com")
                .withName(RandomStringUtils.randomAlphabetic(7));
    }

    public static User createCourierWithNoName() {
        return new User()
                .withEmail(RandomStringUtils.randomAlphabetic(8) + "@gmail.com")
                .withPassword(RandomStringUtils.randomAlphabetic(7));

    }
}

