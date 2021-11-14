package top.adxd.tikonlinejudge.auth.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class PasswordUpdateByEmailDto implements Serializable {
    public String email, code, newPassword;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", message = "邮箱不符合要求")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "验证码不能为空")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,17}$", message = "密码不符合要求")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
