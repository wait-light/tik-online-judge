package top.adxd.tikonlinejudge.executor.config.perperty;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.common.config.SystemConfig;

/**
 * 配置类
 */
@Component
@ConfigurationProperties("tik-online-judge.executor.threadpool")
@Import(SystemConfig.class)
public class ThreadPoolExecutorProperties implements InitializingBean {
    @Autowired
    private SystemConfig systemConfig;
    public Integer corePoolSize,maxNumberPoolSize,keepAliveTime;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public Integer getMaxNumberPoolSize() {
        return maxNumberPoolSize;
    }

    public void setMaxNumberPoolSize(Integer maxNumberPoolSize) {
        this.maxNumberPoolSize = maxNumberPoolSize;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (corePoolSize == null || corePoolSize == 0 || corePoolSize > systemConfig.coreSize * 5){
            corePoolSize = systemConfig.coreSize;;
        }
        if (maxNumberPoolSize == null || maxNumberPoolSize == 0 || maxNumberPoolSize > corePoolSize * 5){
            maxNumberPoolSize = corePoolSize * 2;
        }
        if (keepAliveTime == null || keepAliveTime <= 0 ){
            keepAliveTime = 10;
        }
    }


}
