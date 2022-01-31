package top.adxd.tikonlinejudge.executor.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.executor.api.IExecutorServiceApi;
import top.adxd.tikonlinejudge.executor.api.entity.ExecutorRunningStatus;
import top.adxd.tikonlinejudge.executor.api.entity.LanguageRunningStatus;
import top.adxd.tikonlinejudge.executor.service.docker.judge.AbstractDockerJudgeTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = IExecutorServiceApi.class)
public class ExecutorServiceApiImpl implements IExecutorServiceApi {
    @Autowired
    private List<AbstractDockerJudgeTemplate> abstractDockerJudgeTemplates;
    private ExecutorRunningStatus executorRunningStatus;
    List<ExecutorRunningStatus> executorRunningStatuses = new ArrayList<>();

    @PostConstruct
    public void init() {

        executorRunningStatus = new ExecutorRunningStatus();
        List<LanguageRunningStatus> languageRunningStatuses = new ArrayList<>();
        executorRunningStatus.setName("executor-" + RandomUtil.randomString(20));
        executorRunningStatus.setRunningStatuses(languageRunningStatuses);
        for (AbstractDockerJudgeTemplate judgeTemplate : abstractDockerJudgeTemplates) {
            LanguageRunningStatus languageRunningStatus = new LanguageRunningStatus();
            languageRunningStatus.setRunning(judgeTemplate.isRunning());
            languageRunningStatus.setAvaluable(judgeTemplate.isReady());
            languageRunningStatus.setLanguageType(judgeTemplate.getLanguage().name());
            languageRunningStatuses.add(languageRunningStatus);
        }
        executorRunningStatuses.add(executorRunningStatus);
    }

    @Override
    public List<ExecutorRunningStatus> executorList() {
        int index = 0;
        List<LanguageRunningStatus> runningStatuses = executorRunningStatus.getRunningStatuses();
        for (AbstractDockerJudgeTemplate judgeTemplate : abstractDockerJudgeTemplates) {
            LanguageRunningStatus languageRunningStatus = runningStatuses.get(index++);
            languageRunningStatus.setRunning(judgeTemplate.isRunning());
            languageRunningStatus.setAvaluable(judgeTemplate.isReady());
            languageRunningStatus.setLanguageType(judgeTemplate.getLanguage().name());
            languageRunningStatus.setId(judgeTemplate.getId());
        }
        return executorRunningStatuses;
    }

    @Override
    public String restart(String id) {
        return null;
    }
}
