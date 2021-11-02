package top.adxd.tikonlinejudge.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.auth.dto.LoginEmailDto;
import top.adxd.tikonlinejudge.auth.dto.LoginUsernameDto;
import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.auth.service.IAuthorizationService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/*
 * @author wait-light
 * @date 2021/11/1
 */
@RestController
@RequestMapping("/auth/anonymous/index")
public class AnonymousController {
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IAuthorizationService authorizationService;

    @PostMapping("/email_login")
    public CommonResult emailLogin(@RequestBody LoginEmailDto loginEmailDto) {
        return authenticationService.emailLogin(loginEmailDto.getEmail(), loginEmailDto.getCode());
    }
    @PostMapping("/username_login")
    public CommonResult passwordLogin(@RequestBody LoginUsernameDto loginUsernameDto) {
        return authenticationService.usernameLogin(loginUsernameDto.getUsername(), loginUsernameDto.getPassword());
    }
}
