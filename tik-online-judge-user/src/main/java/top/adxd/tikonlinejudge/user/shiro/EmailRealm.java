package top.adxd.tikonlinejudge.user.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IUserService;

/**
 * @author light
 */
public class EmailRealm extends AuthorizingRealm {
    @Autowired
    private IUserRealmService userRealmService;
    @Autowired
    private IUserService userService;

    /**
     * 鉴权
     *
     * @param authenticationToken 账号密码等信息
     * @return 用户实体等
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        userRealmService.emailLogin((LoginToken) authenticationToken);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 根据已经登录的用户进行权限授权
     * @param principalCollection 用户信息
     * @return 返回授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object email = principalCollection.getPrimaryPrincipal();
        User user = userService.getUser((String) email);
        AuthorizationInfo userAuthorizationInfo = userRealmService.getUserAuthorizationInfo(user);
        return userAuthorizationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return (token instanceof LoginToken);
    }

    @Override
    public String getName() {
        return LoginType.EMAIL.name();
    }
}
