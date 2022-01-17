package top.adxd.tikonlinejudge.auth.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.dto.LoginEmailDto;
import top.adxd.tikonlinejudge.auth.dto.LoginUsernameDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByEmailDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByPasswordDto;
import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import top.adxd.tikonlinejudge.auth.util.RegexUtil;
import top.adxd.tikonlinejudge.common.annotation.Role;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;

import java.time.Duration;

/*
 * @author wait-light
 * @date 2021/11/1
 */
@Role(name = "anonymous")
@RestController
@RequestMapping("/auth/anonymous/index")
public class AnonymousController {
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IUserService userService;
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

    @PutMapping("/password/email")
    public CommonResult updatePassword(@RequestBody @Validated PasswordUpdateByEmailDto passwordUpdateByEmailDto) {
        return userService.updatePassword(passwordUpdateByEmailDto);
    }

    @PutMapping("/password/username")
    public CommonResult updatePassword(@RequestBody @Validated PasswordUpdateByPasswordDto passwordUpdateByPasswordDto) {
        return userService.updatePassword(passwordUpdateByPasswordDto);
    }

    @GetMapping("/{id}")
    public CommonResult userTitle(@PathVariable("id") Long uid) {
        SafeUserDto safeUser = userService.getSafeUser(uid);
        return safeUser == null ? CommonResult.error() : CommonResult.success().singleData(safeUser);
    }
}
