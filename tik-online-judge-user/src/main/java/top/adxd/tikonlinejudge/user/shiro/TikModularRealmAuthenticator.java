package top.adxd.tikonlinejudge.user.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author light
 */
public class TikModularRealmAuthenticator extends ModularRealmAuthenticator {
    private Map<String, Realm> loginTypeRealmMap;

    public TikModularRealmAuthenticator() {
    }

    @Override
    public void setRealms(Collection<Realm> realms) {
        super.setRealms(realms);
        HashMap<String, Realm> loginTypeRealmMap = new HashMap<>();
        for (Realm realm:realms) {
            loginTypeRealmMap.put(realm.getName(),realm);
        }
        this.loginTypeRealmMap = loginTypeRealmMap;
    }


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        if (authenticationToken instanceof LoginToken) {
            LoginType loginType = ((LoginToken) authenticationToken).getLoginType();
            Realm realm = loginTypeRealmMap.get(loginType.toString());
            if (realm == null) {
                throw new AuthenticationException("Specified LoginType unsupported");
            }
            return doSingleRealmAuthentication(realm, authenticationToken);
        } else {
            throw new AuthenticationException("Unsupported AuthenticationToken");
        }
    }
}
