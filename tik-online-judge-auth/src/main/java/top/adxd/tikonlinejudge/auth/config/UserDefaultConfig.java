package top.adxd.tikonlinejudge.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
@ConfigurationProperties("tik-online-judge-auth.user.user-default")
@Component
public class UserDefaultConfig {
    public String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
