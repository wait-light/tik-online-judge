package top.adxd.tikonlinejudge.user.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.service.IMailService;
import top.adxd.tikonlinejudge.user.service.IUserRealmService;
import top.adxd.tikonlinejudge.user.shiro.LoginToken;
import top.adxd.tikonlinejudge.user.shiro.PasswordRealm;

import javax.validation.Valid;

/**
 * @author light
 */
@RestController("/index")
public class IndexController {
    @Autowired
    private IMailService mailService;
    @Autowired
    private IUserRealmService userRealmService;
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginToken loginToken) {
        Subject subject = SecurityUtils.getSubject();
        //由于实现的问题，必须要使用LoginToken
//        LoginToken loginToken = new LoginToken(LoginType.PASSWORD, "admin@qq.com", "admin");
        try {
            subject.login(loginToken);
            return CommonResult.success("登录成功");
        } catch (AuthenticationException authenticationException) {
            return CommonResult.error(authenticationException.getMessage());
        }
    }
    @GetMapping("/code/{email}")
    public CommonResult getCode(@PathVariable String email){
        if (!Validator.isEmail(email)){
            return CommonResult.error("邮箱格式非法");
        }
        return userRealmService.sendCode(email, RandomUtil.randomString(6));
    }
}
