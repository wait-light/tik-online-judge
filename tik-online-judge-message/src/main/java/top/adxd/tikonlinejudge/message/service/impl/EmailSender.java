package top.adxd.tikonlinejudge.message.service.impl;
/*
 * @author  wait-light
 * @date  2021/10/28 下午1:27
 */
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.message.service.Sender;

@Service("emailSender")
public class EmailSender implements Sender {
    @Override
    public void send(String to, String subject, String content) {
        MailUtil.send(to, subject, content, false);
    }

//    public void Send
}
