package top.adxd.tikonlinejudge.user.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.api.Vo.SafeUserVo;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IMailService;
import top.adxd.tikonlinejudge.user.shiro.IUserRealmService;
import top.adxd.tikonlinejudge.user.service.IUserService;
import top.adxd.tikonlinejudge.user.service.impl.UserTokenService;
import top.adxd.tikonlinejudge.user.shiro.LoginToken;

import javax.validation.Valid;

/**
 * @author light
 */
@RestController
@RequestMapping("/user/index")
public class IndexController {
    @Autowired
    private IMailService mailService;
    @Autowired
    private IUserRealmService userRealmService;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginToken loginToken) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginToken);
            Object token = subject.getSession().getAttribute(UserTokenService.SESSION_TOKEN);
            if (token == null){
                User user = userService.getUserByUsername((String) loginToken.getPrincipal());
                userTokenService.issueRenewalToken(user);
            }
            token = subject.getSession().getAttribute(UserTokenService.SESSION_TOKEN);
            return CommonResult.success("登录成功").add(UserTokenService.SESSION_TOKEN,token);
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
    @GetMapping("/titile-info")
    public CommonResult userTitleInfo(@RequestHeader("token") String token){
        SafeUserVo safeUserVo = userTokenService.baseUserMessage(token);
        if (safeUserVo == null){
            return CommonResult.error("数据非法/数据过期");
        }
        return CommonResult.success().add("user",safeUserVo);
    }
}
