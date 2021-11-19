package top.adxd.tikonlinejudge.social.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class GroupTaskDto implements Serializable {

    /**
     * 任务介绍
     */
    private String taskIntroduce;

    /**
     * 任务名称
     */
    @NotNull
    private String name;
    /**
     * 状态
     */
    private Boolean status;

    /**
     * 开始时间
     */
    @NotNull
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @NotNull
    private LocalDateTime endTime;

    /**
     * 题目
     */
    @NotNull
    private List<Long> problems;


    public String getTaskIntroduce() {
        return taskIntroduce;
    }

    public void setTaskIntroduce(String taskIntroduce) {
        this.taskIntroduce = taskIntroduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getProblems() {
        return problems;
    }

    public void setProblems(List<Long> problems) {
        this.problems = problems;
    }
}
