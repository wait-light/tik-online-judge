package top.adxd.tikonlinejudge.executor.config.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.adxd.tikonlinejudge.executor.service.docker.judge.AbstractDockerJudgeTemplate;

import java.util.List;

/**
 * 用于重置容器
 */
@Configuration
@EnableScheduling
public class AutoRescueTask {
    private static final Logger logger = LoggerFactory.getLogger(AutoRescueTask.class);
    @Autowired(required = false)
    private List<AbstractDockerJudgeTemplate> abstractDockerJudgeTemplates;

    /**
     * 每天凌晨一点重置容器
     */
    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        logger.info("自动重置容器");
        for (AbstractDockerJudgeTemplate template : abstractDockerJudgeTemplates) {
            template.rescureContainer();
        }
    }
}
