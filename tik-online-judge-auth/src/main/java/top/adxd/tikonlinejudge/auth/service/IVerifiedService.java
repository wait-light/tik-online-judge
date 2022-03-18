package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.dto.ChangeAvatarDto;
import top.adxd.tikonlinejudge.auth.dto.ChangeEmailDto;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author wait-light
 * @date 2021/11/1.
 */
public interface IVerifiedService {
    /**
     * 获取用户的基础信息(头像、昵称)
     *
     * @param token 根据登录的用户获取用户信息
     * @return 用户的基础信息
     */
    SafeUserDto baseMessage(String token);

    /**
     * 更换用户昵称
     *
     * @param uid      用户id
     * @param nickname 用户昵称
     * @return 更换结果
     */
    CommonResult changeNickName(Long uid, String nickname);

    CommonResult accountMessage(Long uid);

    CommonResult changeEmail(ChangeEmailDto changeEmailDto);

    CommonResult changeAvatar(ChangeAvatarDto changeAvatarDto);
}
