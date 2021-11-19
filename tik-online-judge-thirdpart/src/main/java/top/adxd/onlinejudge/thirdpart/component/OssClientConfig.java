package top.adxd.onlinejudge.thirdpart.component;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssClientConfig {
    @Value("${oss.endpoint}")
    public String endPoint;
    @Value("${oss.access-key}")
    public String accessKey;
    @Value("${oss.secret-key}")
    public String secretKey;

    @Bean
    public OSS oss() {
        return new OSSClientBuilder()
                .build(endPoint, accessKey, secretKey);
    }
}
