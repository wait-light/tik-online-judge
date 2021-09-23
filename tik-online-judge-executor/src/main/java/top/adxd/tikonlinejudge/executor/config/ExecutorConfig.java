package top.adxd.tikonlinejudge.executor.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 执行基础信息配置
 */
@Component
public class ExecutorConfig implements InitializingBean {
    @Autowired
    private Environment environment;
    /**
     * 最长执行时间
     */
    private Long maxExecuteTime = 10L;
    /**
     * 全局基础执行信息
     */
    private static ExecutorConfig globalBaseConfig;
    /**
     * 最长执行时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private Charset defaultCharset;

    public Charset getDefaultCharset() {
        return defaultCharset;
    }

    public void setDefaultCharset(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

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
        globalBaseConfig = this;
        defaultCharset = Charset.forName("gbk");
    }

    /**
     * 将一个【ExecuteInput】对象转换成【ExecutorConfig】对象
     * @param executeInput
     * @return ExecutorConfig
     */
    public static ExecutorConfig convert(ExecuteInput executeInput) {
        if (executeInput == null) {
            return globalBaseConfig;
        }
        ExecutorConfig config = new ExecutorConfig();
        config.setMaxExecuteTime(executeInput.getTime());
        config.setTimeUnit(globalBaseConfig.getTimeUnit());
        return config;
    }
}
