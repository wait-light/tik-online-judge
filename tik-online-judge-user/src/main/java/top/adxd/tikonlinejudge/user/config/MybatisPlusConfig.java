package top.adxd.tikonlinejudge.user.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wait_light
 * @create 2021/9/12
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisConfiguration mybatisConfiguration(){
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        //日志实现
        mybatisConfiguration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        //驼峰命名
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        return mybatisConfiguration;
    }
}
