package top.adxd.tikonlinejudge.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author light
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TikOnlineJudgeGatewayApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(TikOnlineJudgeGatewayApplication.class,args);
    }
}
