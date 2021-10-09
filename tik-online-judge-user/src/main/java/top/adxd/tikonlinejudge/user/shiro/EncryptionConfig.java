package top.adxd.tikonlinejudge.user.shiro;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author light
 */
@ConfigurationProperties("tik-online-judge.encryption")
@Component
public class EncryptionConfig {
    public String hashAlgorithmName = "md5";
    public Integer hashIterations = 3;

    public String getHashAlgorithmName() {
        return hashAlgorithmName;
    }

    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithmName = hashAlgorithmName;
    }

    public Integer getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(Integer hashIterations) {
        this.hashIterations = hashIterations;
    }
}
