package top.adxd.tikonlinejudge.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wait-light
 * @date 2021/10/19 下午6:42
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("top.adxd.tikonlinejudge.auth.mapper")
public class TikOnlineJudgeAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeAuthApplication.class,args);
    }
}
