package top.adxd.tikonlinejudge.auth.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.auth.dto.LoginEmailDto;
import top.adxd.tikonlinejudge.auth.dto.LoginUsernameDto;
import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.auth.util.RegexUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;

import java.time.Duration;

/*
 * @author wait-light
 * @date 2021/11/1
 */
@RestController
@RequestMapping("/auth/anonymous/index")
public class AnonymousController {
    @Autowired
    private IAuthenticationService authenticationService;
    @DubboReference
    private IVerificationCodeService emailVerificationCodeService;

    @PostMapping("/email_login")
    public CommonResult emailLogin(@RequestBody LoginEmailDto loginEmailDto) {
        return authenticationService.emailLogin(loginEmailDto.getEmail(), loginEmailDto.getCode());
    }

    @PostMapping("/username_login")
    public CommonResult passwordLogin(@RequestBody LoginUsernameDto loginUsernameDto) {
        return authenticationService.usernameLogin(loginUsernameDto.getUsername(), loginUsernameDto.getPassword());
    }

    @PostMapping("/verificationCode/{email}")
    public CommonResult VerificationCode(@PathVariable("email") String email) {
        if (!RegexUtil.isEmail(email)) {
            return CommonResult.error("邮箱格式不符合要求");
        }
        emailVerificationCodeService.sendVerificationCode(email, Duration.ofMinutes(5L));
        return CommonResult.success("已发送，有效期【5分钟】");
    }
}
