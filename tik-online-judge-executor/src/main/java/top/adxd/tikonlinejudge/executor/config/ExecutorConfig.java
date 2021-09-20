package top.adxd.tikonlinejudge.executor.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;

import java.util.concurrent.TimeUnit;

/**
 * 执行基础信息配置
 */
@Component
public class ExecutorConfig implements InitializingBean {
    @Autowired
    Environment environment;
    /**
     * 最长执行时间
     */
    private Long maxExecuteTime = 10L;
    private static ExecutorConfig staticConfig;
    /**
     * 最长执行时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public Long getMaxExecuteTime() {
        return maxExecuteTime;
    }

    public void setMaxExecuteTime(Long maxExecuteTime) {
        this.maxExecuteTime = maxExecuteTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String maxExecuteTime = environment.getProperty("tik-online-judge.executor.maxExecuteTime");
        if (maxExecuteTime != null) {
            setMaxExecuteTime(Long.parseLong(maxExecuteTime));
        }
        staticConfig = this;
    }

    public static ExecutorConfig convert(ExecuteInput executeInput) {
        if (executeInput == null) {
            return staticConfig;
        }
        ExecutorConfig config = new ExecutorConfig();
        config.setMaxExecuteTime(executeInput.getTime());
        config.setTimeUnit(staticConfig.getTimeUnit());
        return config;
    }
}
