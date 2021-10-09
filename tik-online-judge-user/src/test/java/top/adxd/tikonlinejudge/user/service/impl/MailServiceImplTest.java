package top.adxd.tikonlinejudge.user.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.user.service.IMailService;
import top.adxd.tikonlinejudge.user.utils.HashUtil;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MailServiceImplTest {
    @Autowired
    private IMailService mailService;
    @Test
    public void etst(){
//        mailService.sendSimpleMail("915779941@qq.com","验证码测试","864D");
        String admin = HashUtil.hash("admin");
        System.out.println(admin);
        String hashAlgorithmName = "MD5";
        Object credentials = "admin";
        Object salt = ByteSource.Util.bytes("admin");;
        int hashIterations = 3;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}