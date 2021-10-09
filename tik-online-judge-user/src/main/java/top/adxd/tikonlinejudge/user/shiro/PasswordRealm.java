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
/**
 * @author light
 **/
public class PasswordRealm extends AuthorizingRealm {

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
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                principal,
                "3fed7a346e430ea4c2aa10250928f4de",
                ByteSource.Util.bytes("admin"),
                getName()

        );
//        simpleAuthenticationInfo.setPrincipals();
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
