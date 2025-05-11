package pojo;

import lombok.Getter;

/*
Users pojo class
 */
@Getter
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
