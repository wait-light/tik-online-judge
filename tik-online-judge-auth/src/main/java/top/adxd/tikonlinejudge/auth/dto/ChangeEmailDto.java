package top.adxd.tikonlinejudge.auth.dto;

import java.io.Serializable;

/**
 * @author wait-light
 * @date 2021/11/5.
 */

public class ChangeEmailDto implements Serializable {
    public String email;
    public String code;
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
