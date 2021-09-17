package top.adxd.tikonlinejudge.executor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.executor.config.perperty.ThreadPoolExecutorProperties;

import java.util.concurrent.*;

/**
 * 线程池配置类
 */
@Configuration
public class ThreadPoolConfig {
    @Autowired
    private ThreadPoolExecutorProperties threadPoolExecutorProperties;
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadPoolExecutorProperties.getCorePoolSize(),
                threadPoolExecutorProperties.getMaxNumberPoolSize(),
                threadPoolExecutorProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        return threadPoolExecutor;
    }
}
