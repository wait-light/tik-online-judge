package top.adxd.tikonlinejudge.auth.service.impl;

import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:09
 */
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Override
    public CommonResult usernameLogin(String username, String password) {
        return null;
    }

    @Override
    public CommonResult emailLogin(String email, String code) {
        return null;
    }
}
