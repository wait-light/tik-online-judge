package top.adxd.tikonlinejudge.executor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wait_light
 * @create 2021/9/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("top.adxd.tikonlinejudge.executor.mapper")
public class TikOnlineJudgeExecutorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeExecutorApplication.class, args);
    }
}
