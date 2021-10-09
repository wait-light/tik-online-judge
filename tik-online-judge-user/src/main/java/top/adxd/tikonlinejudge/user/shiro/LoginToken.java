package top.adxd.tikonlinejudge.user.shiro;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

import javax.validation.constraints.NotNull;

/**
 * @author light
 */
public class LoginToken implements RememberMeAuthenticationToken {
    @NotNull
    private LoginType loginType;
    @NotNull
    private Object principal;
    @NotNull
    private Object credentials;
    private boolean rememberMe = false;


    public LoginType getLoginType() {
        return loginType;
    }

    public LoginToken(LoginType loginType, Object principal, Object credentials) {
        this.loginType = loginType;
        this.principal = principal;
        this.credentials = credentials;
    }

    public LoginToken(LoginType loginType, Object principal, Object credentials, boolean rememberMe) {
        this.loginType = loginType;
        this.principal = principal;
        this.credentials = credentials;
        this.rememberMe = rememberMe;
    }

    private LoginToken(){

    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
