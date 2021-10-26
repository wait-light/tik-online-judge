package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author wait-light
 * @date 2021/10/26 下午4:57
 */
public interface IAuthenticationService {
    /**
     * 使用用户名登录获取认证令牌
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    CommonResult usernameLogin(String username,String password);

    /**
     * 使用邮箱登录获取认证令牌
     * @param email 邮箱
     * @param code 验证码
     * @return 登录结果
     */
    CommonResult emailLogin(String email,String code);
}
