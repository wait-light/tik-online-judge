package top.adxd.tikonlinejudge.user.config.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wait_light
 * @create 2021/9/11
 */
@Component
@ConfigurationProperties(prefix = "tik-online-judge-user.super-admin")
public class SuperAdminProperties {
    public String username = "admin";
    public String avatar = "https://imgtu.com/i/4SEo8O";
    public String password = "admin";

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

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
