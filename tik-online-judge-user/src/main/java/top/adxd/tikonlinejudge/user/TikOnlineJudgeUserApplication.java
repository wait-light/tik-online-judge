package top.adxd.tikonlinejudge.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;


/**
 * @author wait_light
 * @create 2021/9/5
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("top.adxd.tikonlinejudge.user.mapper")
public class TikOnlineJudgeUserApplication {
    public static void main(String[] args)
    {

        SpringApplication.run(TikOnlineJudgeUserApplication.class,args);
    }
}
