package top.adxd.tikonlinejudge.auth.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wait-light
 * @date 2021/11/2.
 */
@Data
public class LoginEmailDto implements Serializable {
    public String email;
    public String code;

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }
}
