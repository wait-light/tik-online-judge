package top.adxd.tikonlinejudge.auth.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.common.exeption.CommonException;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.time.Instant;
import java.util.Date;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
@Component
public class JWTUtil {
    @Autowired
    private SecureConfig secureConfig;
    private byte[] secretKeyBytes;

    public static final String PAYLOAD_UID = "uid";
    public static final String PAYLOAD_USERNAME = "username";
    public static final String PAYLOAD_ADMIN = "admin";

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
                .setPayload(PAYLOAD_UID, user.getUid().toString())
                .setPayload(PAYLOAD_USERNAME, user.getUsername())
                .setPayload(PAYLOAD_ADMIN,user.getAdmin())
                .setIssuedAt(Date.from(Instant.now()))
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
        try {
            return JWT.of(token).setKey(secretKeyBytes).verify();
        }catch (JWTException jwtException){
            return false;
        }catch (NullPointerException nullPointerException){
            return false;
        }
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
        return Long.parseLong((String) JWT.of(token).setKey(secretKeyBytes).getPayload(PAYLOAD_UID));
    }

    /**
     * 从token返回此用户是否管理员
     * @param token token
     * @return 是否管理员
     */
    public Boolean isAdmin(String token){
        if (!validate(token)) {
            return null;
        }
        return (Boolean) JWT.of(token).setKey(secretKeyBytes).getPayload(PAYLOAD_ADMIN);
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
        return JWT.of(token).setKey(secretKeyBytes).getPayload(PAYLOAD_USERNAME).toString();
    }

}
