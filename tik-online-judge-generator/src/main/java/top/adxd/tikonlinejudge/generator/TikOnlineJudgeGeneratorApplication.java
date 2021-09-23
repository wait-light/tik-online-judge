package top.adxd.tikonlinejudge.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TikOnlineJudgeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeGeneratorApplication.class, args);
    }
}
