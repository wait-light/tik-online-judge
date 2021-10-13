package top.adxd.tikonlinejudge.user.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IUserService;

/**
 * @author light
 **/
public class PasswordRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    public PasswordRealm(CredentialsMatcher credentialsMatcher){
        super(credentialsMatcher);
    }
    /**
     * 鉴权
     * @param authenticationToken 账号密码等信息
     * @return 用户实体等
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null){
            throw new AuthenticationException("账号密码为空");
        }
        Object principal = authenticationToken.getPrincipal();
        User user = userService.getUserByUsername((String) authenticationToken.getPrincipal());
        if (user == null){
            throw new AuthenticationException("账号或密码不正确");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                principal,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),
                getName()
        );
        return simpleAuthenticationInfo;
    }

    /**
     * 根据已经登录的用户进行权限授权
     * @param principalCollection 用户信息
     * @return 返回授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return (token instanceof LoginToken);
    }

    @Override
    public String getName() {
        return LoginType.PASSWORD.name();
    }
}
