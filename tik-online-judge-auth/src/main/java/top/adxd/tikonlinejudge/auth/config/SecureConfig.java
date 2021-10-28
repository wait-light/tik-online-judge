package top.adxd.tikonlinejudge.auth.config;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
@ConfigurationProperties("tik-online-judge-auth.secure")
@Component
public class SecureConfig {
    private String secretKey;
    private SymmetricAlgorithm algorithm;
    private SymmetricCrypto symmetricCrypto;

    public SymmetricCrypto getSymmetricCrypto() {
        return symmetricCrypto;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public SymmetricAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SymmetricAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @PostConstruct
    private void initialization() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, keyBytes);
    }
}
