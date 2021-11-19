package top.adxd.onlinejudge.thirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class TikOnlineJudgeThirdPartApplication {
    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeThirdPartApplication.class, args);
    }
}
