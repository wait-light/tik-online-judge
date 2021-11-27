package top.adxd.tikonlinejudge.gateway;

//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

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
