package top.adxd.tikonlinejudge.user.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author light
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private EncryptionConfig encryptionConfig;

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//
//        // logged in users with the 'admin' role
//        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
//
//        // logged in users with the 'document:read' permission
//        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    /**
     * @return 返回一个hash凭据匹配者
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(encryptionConfig.getHashAlgorithmName());
        hashedCredentialsMatcher.setHashIterations(encryptionConfig.getHashIterations());
        return hashedCredentialsMatcher;
    }

    @Bean
    public ModularRealmAuthenticator tikModularRealmAuthenticator() {
        return new TikModularRealmAuthenticator();
    }

    /**
     * 若是实现了自定义的CredentialsMatcher 需要将CredentialsMatcher注入到里面，否则在密码校验时不会调用自定义的Matcher
     *
     * @param hashedCredentialsMatcher hash凭据匹配者
     * @return 认证、授权者
     */
    @Bean
    public Realm passwordRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        Realm passwordRealm = new PasswordRealm(hashedCredentialsMatcher);
        return passwordRealm;
    }

    @Bean
    public Realm emailRealm() {
        EmailRealm emailRealm = new EmailRealm();
        return emailRealm;
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        List<Realm> realms = new ArrayList<>();
        realms.add(emailRealm());
        realms.add(passwordRealm(hashedCredentialsMatcher()));
        ModularRealmAuthenticator modularRealmAuthenticator = tikModularRealmAuthenticator();
        modularRealmAuthenticator.setRealms(realms);
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setCacheManager(cacheManager());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);
        defaultWebSecurityManager.setRealms(realms);
        return defaultWebSecurityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        return defaultWebSessionManager;
    }

    @Bean
    public CacheManager cacheManager() {
        MemoryConstrainedCacheManager memoryConstrainedCacheManager = new MemoryConstrainedCacheManager();
        return memoryConstrainedCacheManager;
    }
}
