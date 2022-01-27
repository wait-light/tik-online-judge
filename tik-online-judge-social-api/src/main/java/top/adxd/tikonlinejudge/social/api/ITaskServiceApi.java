package top.adxd.tikonlinejudge.social.api;

import top.adxd.tikonlinejudge.social.api.vo.Task;

import java.util.List;

public interface ITaskServiceApi {
    Task getTask(Long taskId);
    List<Long> taskProblems(Long taskId);
}
