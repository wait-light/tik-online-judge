package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IProblemServiceApi;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.entity.TaskItem;
import top.adxd.tikonlinejudge.social.mapper.TaskMapper;
import top.adxd.tikonlinejudge.social.service.ITaskItemService;
import top.adxd.tikonlinejudge.social.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    @Autowired
    private ITaskItemService taskItemService;
    @DubboReference
    private IProblemServiceApi problemService;


    @Override
    public CommonResult taskDetail(Long groupId, Long taskId) {
        //todo 权限校验
        Task task = getById(taskId);
        if (task == null) {
            return CommonResult.error("非法访问");
        }
        LocalDateTime now = LocalDateTime.now();
        if (task.getBeginTime().isAfter(now)) {
            return CommonResult.error("任务还未开始");
        }
        List<Long> problems = taskItemService.list(new QueryWrapper<TaskItem>()
                .eq("task_id", taskId)
                .select("problem_id"))
                .stream()
                .map(TaskItem::getProblemId)
                .collect(Collectors.toList());
        return CommonResult.success()
                .add("task", task)
                .add("problems", problemService.problemInfoList(problems, "id", "name", "secret_key"));
    }

    @Override
    public CommonResult managerTaskDetail(Long groupId, Long taskId) {
        //todo 权限校验
        Task task = getById(taskId);
        if (task == null) {
            return CommonResult.error("非法访问");
        }
        List<Long> problems = taskItemService.list(new QueryWrapper<TaskItem>()
                .eq("task_id", taskId)
                .select("problem_id"))
                .stream()
                .map(TaskItem::getProblemId)
                .collect(Collectors.toList());
        return CommonResult.success()
                .add("task", task)
                .add("problems", problemService.problemInfoList(problems, "id", "name", "secret_key"));
    }
}
