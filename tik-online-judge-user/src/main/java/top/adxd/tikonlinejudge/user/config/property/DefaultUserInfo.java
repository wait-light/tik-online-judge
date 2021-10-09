package top.adxd.tikonlinejudge.user.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wait_light
 * @create 2021/9/11
 */
@Component
@ConfigurationProperties("tik-online-judge-user.user-default")
public class DefaultUserInfo {
    public String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
