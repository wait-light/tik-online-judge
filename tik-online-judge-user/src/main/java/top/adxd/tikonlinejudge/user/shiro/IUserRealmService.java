package top.adxd.tikonlinejudge.user.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.shiro.LoginToken;

import java.util.Map;

/**
 * @author light
 */
public interface IUserRealmService {
    void emailLogin(LoginToken loginToken);
    CommonResult sendCode(String email,String code);
    AuthorizationInfo getUserAuthorizationInfo(User user);
}
