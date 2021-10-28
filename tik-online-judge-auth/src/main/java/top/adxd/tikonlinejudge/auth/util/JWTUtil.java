package top.adxd.tikonlinejudge.auth.util;

import cn.hutool.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.common.exeption.CommonException;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
@Component
public class JWTUtil {
    @Autowired
    private SecureConfig secureConfig;
    private byte[] secretKeyBytes;

    @PostConstruct
    private void initialization() {
        secretKeyBytes = secureConfig.getSecretKey().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 根据用户信息生成token
     *
     * @param user 用户信息
     * @return token字符串
     */
    public String generate(User user) {
        if (user == null) {
            throw new CommonException("生成token的用户不能为空");
        }
        return JWT.create()
                .setPayload("uid", user.getUid())
                .setPayload("username", user.getUsername())
                .setKey(secretKeyBytes)
                .sign();
    }

    /**
     * 验证token是否合法
     *
     * @param token token
     * @return token是否合法
     */
    private boolean validate(String token) {
        return JWT.of(token).setKey(secretKeyBytes).verify();
    }

    /**
     * 从token中获取用户id
     *
     * @param token token
     * @return 用户id，若是token不合法，返回null
     */
    public Long uid(String token) {
        if (!validate(token)) {
            return null;
        }
        return Long.getLong(JWT.of(token).setKey(secretKeyBytes).getPayload("uid").toString());
    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return 用户名，若是token不合法，返回null
     */
    public String username(String token) {
        if (!validate(token)) {
            return null;
        }
        return JWT.of(token).setKey(secretKeyBytes).getPayload("username").toString();
    }

}
