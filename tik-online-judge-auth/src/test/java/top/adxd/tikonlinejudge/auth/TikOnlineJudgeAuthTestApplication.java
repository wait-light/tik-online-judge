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
import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.auth.service.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;
import top.adxd.tikonlinejudge.auth.util.RegexUtil;
import top.adxd.tikonlinejudge.common.singleton.RequestMethod;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.Email;

import java.nio.charset.StandardCharsets;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
//@SpringBootTest
public class TikOnlineJudgeAuthTestApplication {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SecureConfig secureConfig;
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IAuthorizationService authorizationService;
    @Autowired
    private JWTUtil jwtUtil;
    @Test
    public void aaa(){
//        String admin = secureConfig.getSymmetricCrypto().encryptHex("admin");
//        System.out.println(admin);
        CommonResult commonResult = authenticationService.usernameLogin("admin", "admin");
        Object token = commonResult.get("token");
        Boolean admin = jwtUtil.isAdmin((String) token);
        System.out.println(admin);
        System.out.println(commonResult);
//        Object token = commonResult.get("token");
        CommonResult authorization = authorizationService.authorization((String) token, "/login", RequestMethod.GET);
        System.out.println(authorization);
    }
    @Test
    public void aaab(){
        System.out.println(RegexUtil.isEmail("9157@qqcom"));
    }
}
