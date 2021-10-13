package top.adxd.tikonlinejudge.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties("tik-online-judge.user.token")
public class TokenConfig {
    public String secretKey = "admin";
    public Long defaultIssueRenewalTime = 7L;
    public TimeUnit type = TimeUnit.DAYS;

    public TimeUnit getType() {
        return type;
    }

    public void setType(TimeUnit type) {
        this.type = type;
    }

    public Long getDefaultIssueRenewalTime() {
        return defaultIssueRenewalTime;
    }

    public void setDefaultIssueRenewalTime(Long defaultIssueRenewalTime) {
        this.defaultIssueRenewalTime = defaultIssueRenewalTime;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
