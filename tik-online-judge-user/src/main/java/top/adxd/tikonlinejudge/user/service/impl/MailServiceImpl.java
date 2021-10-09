package top.adxd.tikonlinejudge.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.service.IMailService;

/**
 * @author light
 */
@Service("mailService")
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public CommonResult sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            return CommonResult.success("发送成功,有效期3分钟");
        } catch (Exception e) {
            return CommonResult.error("发送失败");
        }
    }

    private static final String CODE_SUBJECT = "TikOnlineJudge";
//    private static final String CODE_CONTENT = "%s";

    @Override
    public CommonResult sendCode(String to) {
        return sendSimpleMail(to, CODE_SUBJECT, RandomUtil.randomString(6));
    }


}
