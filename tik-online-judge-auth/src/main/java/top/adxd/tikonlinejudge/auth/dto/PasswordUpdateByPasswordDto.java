package top.adxd.tikonlinejudge.auth.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class PasswordUpdateByPasswordDto implements Serializable {
    public String username, password, newPassword;

    @NotNull(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_\\@\\.]{5,25}$", message = "账号不符合要求")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,17}$", message = "密码不符合要求")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "新密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,17}$", message = "新密码不符合要求")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
