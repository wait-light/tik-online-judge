package top.adxd.tikonlinejudge.auth;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.message.api.Email;

import java.nio.charset.StandardCharsets;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
@SpringBootTest
public class TikOnlineJudgeAuthTestApplication {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SecureConfig secureConfig;
    @Test
    public void aaa(){
        SymmetricCrypto symmetricCrypto = secureConfig.getSymmetricCrypto();
        String admin = symmetricCrypto.encryptHex("admin");
        System.out.println(admin);
        System.out.println(symmetricCrypto.decryptStr(admin));
    }
}
