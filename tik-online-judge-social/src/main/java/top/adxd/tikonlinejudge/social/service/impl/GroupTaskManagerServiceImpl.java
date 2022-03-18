package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.ITaskRankServiceApi;
import top.adxd.tikonlinejudge.social.dto.GroupTaskDto;
import top.adxd.tikonlinejudge.social.entity.GroupTask;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.entity.TaskItem;
import top.adxd.tikonlinejudge.social.service.*;
import top.adxd.tikonlinejudge.social.single.GroupUserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("groupTaskManagerServiceImpl")
public class GroupTaskManagerServiceImpl implements IGroupTaskManagerService {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IGroupTaskService groupTaskService;
    @Autowired
    private ITaskItemService taskItemService;
    @Autowired
    private IGroupUserService groupUserService;
    @DubboReference
    private ITaskRankServiceApi taskRankServiceApi;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult addTask(Long groupId, GroupTaskDto taskDto) {
        return addTaskInner(groupId, taskDto, true) ?
                CommonResult.success("任务发布成功") :
                CommonResult.error("任务发布失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateTask(Long groupId, Long taskId, GroupTaskDto taskDto) {
        //todo 权限校验
        boolean deleteTaskSuccess = deleteTaskInner(groupId, taskId, false);
        boolean addTaskSuccess = addTaskInner(groupId, taskDto, false);
        return deleteTaskSuccess & addTaskSuccess ?
                CommonResult.success("更新成功") :
                CommonResult.error("更新失败");
    }

    private boolean addTaskInner(Long groupId, GroupTaskDto taskDto, boolean needCheckAuthority) {
        if (needCheckAuthority) {
            //todo 权限校验
        }
        Long uid = UserInfoUtil.getUid();
        LocalDateTime now = LocalDateTime.now();
        //任务
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        task.setCreateTime(now);
        task.setStatus(true);
        task.setCreateUserId(uid);
        taskService.save(task);

        //群组任务
        GroupTask groupTask = new GroupTask();
        groupTask.setGroupId(groupId);
        groupTask.setTaskId(task.getId());
        groupTaskService.save(groupTask);

        //任务项
        taskDto.getProblems()
                .stream()
                .distinct() //去重
                .forEach((item) -> {
                    TaskItem taskItem = new TaskItem();
                    taskItem.setTaskId(task.getId());
                    taskItem.setProblemId(item);
                    taskItemService.save(taskItem);
                });

        List<Long> uids = groupUserService
                .list(new QueryWrapper<GroupUser>()
                        .eq("group_id", groupId)
                        .eq("user_type", GroupUserType.COMMON)
                        .select("uid"))
                .stream()
                .map(GroupUser::getUid)
                .collect(Collectors.toList());
        return taskRankServiceApi.initializationTaskRank(task.getId(), uids);
    }

    private boolean deleteTaskInner(Long groupId, Long taskId, boolean needCheckAuthority) {

        if (needCheckAuthority) {
            //todo 权限校验
        }
        //任务删除
        taskService.removeById(taskId);
        //任务项删除
        taskItemService.remove(new QueryWrapper<TaskItem>()
                .eq("task_id", taskId));
        //群组任务删除
        groupTaskService.remove(new QueryWrapper<GroupTask>()
                .eq("group_id", groupId)
                .eq("task_id", taskId));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteTask(Long groupId, Long taskId) {
        boolean success = deleteTaskInner(groupId, taskId, true);
        return success ? CommonResult.success("删除成功") : CommonResult.error("删除失败");
    }

    @Override
    public CommonResult tasks(Long groupId) {
        //todo 权限校验
        List<Long> tasks = groupTaskService.list(new QueryWrapper<GroupTask>()
                .eq("group_id", groupId)
                .select("task_id"))
                .stream()
                .map(item -> item.getTaskId())
                .collect(Collectors.toList());
        if (tasks.size() <= 0) {
            return CommonResult.success().singleData(tasks);
        }
        return CommonResult.success().singleData(taskService.listByIds(tasks));
    }
}
