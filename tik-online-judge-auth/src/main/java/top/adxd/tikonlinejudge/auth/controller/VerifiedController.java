package top.adxd.tikonlinejudge.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.service.IVerifiedService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/*
 * @author wait-light
 * @date 2021/11/1.
 */
@RestController
@RequestMapping("/auth/verified")
public class VerifiedController {
    @Autowired
    private IVerifiedService verifiedService;

    @GetMapping("/title-info")
    public CommonResult userTitleInfo(@RequestHeader("token") String token){
        SafeUserDto safeUserDto = verifiedService.baseMessage(token);
        if (safeUserDto == null){
            return CommonResult.error("数据非法/数据过期");
        }
        return CommonResult.success().add("user", safeUserDto);
    }
}
