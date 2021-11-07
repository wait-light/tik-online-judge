package top.adxd.tikonlinejudge.message.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.message.api.Email;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;
import top.adxd.tikonlinejudge.message.api.VerifyCodeStatus;

import java.time.Duration;

/**
 * @author wait-light
 * @date 2021/11/5.
 */
@Service("emailVerificationCodeService")
@DubboService
public class EmailVerificationCodeService implements IVerificationCodeService {
    private static final String CODE_PREFIX = "vc:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendVerificationCode(String destination, Duration duration) {
        String randomCode = RandomUtil.randomString(6);
        //以redis中的数据为准
        redisTemplate.opsForValue().set(CODE_PREFIX + destination, randomCode, duration);
        Email email = new Email(destination, "验证码", randomCode);
        rabbitTemplate.convertAndSend(Email.QUEUE, email);
    }
    @Override
    public VerifyCodeStatus verifyCode(String destination, String code) {
        String randomCode = redisTemplate.opsForValue().get(CODE_PREFIX + destination);
        if (randomCode == null || code == null) {
            return VerifyCodeStatus.error;
        }
        return randomCode.equals(code) ? VerifyCodeStatus.success : VerifyCodeStatus.error;
    }
}
