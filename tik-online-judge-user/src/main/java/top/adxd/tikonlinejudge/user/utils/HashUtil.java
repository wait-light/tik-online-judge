package top.adxd.tikonlinejudge.user.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.user.shiro.EncryptionConfig;

import javax.xml.ws.Action;
import java.util.HashMap;

/**
 * @author light
 */
@Component
public class HashUtil {
    private static EncryptionConfig encryptionConfig;
    @Autowired
    private void setEncryptionConfig(EncryptionConfig encryptionConfig){
        HashUtil.encryptionConfig = encryptionConfig;
    }
    public static String hash(String needHashString){
        SimpleHash simpleHash = new SimpleHash(encryptionConfig.getHashAlgorithmName(), needHashString, needHashString, encryptionConfig.getHashIterations());
        return simpleHash.toHex();
    }
}
