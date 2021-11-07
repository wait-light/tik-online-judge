package top.adxd.tikonlinejudge.social;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wait-light
 * @date 2021/11/4.
 */
@EnableDiscoveryClient
@MapperScan("top.adxd.tikonlinejudge.social.mapper")
@SpringBootApplication
public class TikOnlineJudgeSocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeSocialApplication.class, args);
    }
}
