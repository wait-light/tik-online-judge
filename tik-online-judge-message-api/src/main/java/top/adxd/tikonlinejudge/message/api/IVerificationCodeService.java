package top.adxd.tikonlinejudge.message.api;

import java.time.Duration;

/**
 * @author wait-light
 * @date 2021/11/5.
 */

public interface IVerificationCodeService {
    /**
     * 发送验证码到指定目的
     *
     * @param destination 可以是邮箱，手机号等，根据具体实现而定
     * @param duration    有效期时长
     */
    void sendVerificationCode(String destination, Duration duration);

    /**
     * 验证验证码是否正确
     *
     * @param destination 指定的目的
     * @param code        验证码
     * @return 返回验证状态码
     */
    VerifyCodeStatus verifyCode(String destination, String code);
}
