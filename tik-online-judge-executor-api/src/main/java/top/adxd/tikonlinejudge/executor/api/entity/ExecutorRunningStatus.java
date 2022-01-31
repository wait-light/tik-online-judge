package top.adxd.tikonlinejudge.executor.api.entity;

import java.io.Serializable;
import java.util.List;

public class ExecutorRunningStatus implements Serializable {
    public String name;
    public List<LanguageRunningStatus> runningStatuses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LanguageRunningStatus> getRunningStatuses() {
        return runningStatuses;
    }

    public void setRunningStatuses(List<LanguageRunningStatus> runningStatuses) {
        this.runningStatuses = runningStatuses;
    }
}
