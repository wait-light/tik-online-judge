package top.adxd.tikonlinejudge.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.dto.ChangeAvatarDto;
import top.adxd.tikonlinejudge.auth.dto.ChangeEmailDto;
import top.adxd.tikonlinejudge.auth.dto.Nickname;
import top.adxd.tikonlinejudge.auth.service.IVerifiedService;
import top.adxd.tikonlinejudge.common.util.ServletUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
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
    public CommonResult userTitleInfo(@RequestHeader("token") String token) {
        SafeUserDto safeUserDto = verifiedService.baseMessage(token);
        if (safeUserDto == null) {
            return CommonResult.error("数据非法/数据过期");
        }
        return CommonResult.success().add("user", safeUserDto);
    }

    @GetMapping("/nickname")
    public CommonResult nickname() {
        return verifiedService.accountMessage(UserInfoUtil.getUid());
    }

    @PutMapping("/nickname")
    public CommonResult changeNickName(@RequestBody Nickname nickname, @RequestHeader("uid") Long uid) {
        return verifiedService.changeNickName(uid, nickname.getNickname());
    }

    @PutMapping("/email")
    public CommonResult changeEmail(@RequestBody ChangeEmailDto changeEmailDto) {
        return verifiedService.changeEmail(changeEmailDto);
    }

    @PutMapping("/avatar")
    public CommonResult changeAvatar(@Validated @RequestBody ChangeAvatarDto changeAvatarDto){
        return verifiedService.changeAvatar(changeAvatarDto);
    }
}
