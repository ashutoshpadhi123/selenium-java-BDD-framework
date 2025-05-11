package factories;

import enums.Users;
import pojo.User;

/*
Has the list of usernames and passwords used by different kinds of users
 */

public class UserFactory {
    public static User getUser(Users users) {
        switch (users) {
            case STANDARD_USER:
                return new User("standard_user", "secret_sauce");
            case LOCKED_OUT_USER:
                return new User("locked_out_user", "secret_sauce");
            case PERFORMANCE_GLITCH_USER:
                return new User("performance_glitch_user", "secret_sauce");
            default:
                throw new IllegalArgumentException("Unknown user type: " + users);
        }
    }
}
