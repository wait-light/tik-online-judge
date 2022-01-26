package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IProblemServiceApi;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.entity.TaskItem;
import top.adxd.tikonlinejudge.social.service.IRaceService;
import top.adxd.tikonlinejudge.social.service.ITaskItemService;
import top.adxd.tikonlinejudge.social.service.ITaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements IRaceService {
    @Autowired
    private ITaskItemService taskItemService;
    @Autowired
    private ITaskService taskService;
    @DubboReference
    private IProblemServiceApi problemService;

    @Override
    public CommonResult survey(Long raceId) {
        Task task = taskService.getById(raceId);
        if (task == null) {
            return CommonResult.permissionDeny("禁止访问");
        }
        LocalDateTime now = LocalDateTime.now();
        if (task.getBeginTime().isAfter(now)) {
            return CommonResult.error("还未开始");
        }
        return CommonResult.success().add("race", task);
    }

    @Override
    public CommonResult problems(Long raceId) {
        Boolean hasTask = taskService.count(new QueryWrapper<Task>().eq("id",raceId)) > 0;
        if (!hasTask) {
            return CommonResult.error("非法访问");
        }
        List<Long> problems = taskItemService.list(new QueryWrapper<TaskItem>()
                .eq("task_id", raceId)
                .select("problem_id"))
                .stream()
                .map(TaskItem::getProblemId)
                .collect(Collectors.toList());
        return CommonResult.success()
                .add("problems", problemService.problemInfoList(problems, "id", "name", "secret_key","update_time"));
    }
}
