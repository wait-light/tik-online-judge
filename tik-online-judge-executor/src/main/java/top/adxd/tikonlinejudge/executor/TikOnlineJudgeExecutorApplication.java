package top.adxd.tikonlinejudge.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wait_light
 * @create 2021/9/9
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TikOnlineJudgeExecutorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeExecutorApplication.class,args);
    }
}
