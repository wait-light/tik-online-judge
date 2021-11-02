package top.adxd.tikonlinejudge.auth.dto;

import java.io.Serializable;

/**
 * @author wait-light
 * @date 2021/11/2.
 */

public class LoginUsernameDto implements Serializable {
    public String username,password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
