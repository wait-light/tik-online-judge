package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.ITaskRankServiceApi;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.entity.TaskRank;
import top.adxd.tikonlinejudge.executor.entity.TaskSubmit;
import top.adxd.tikonlinejudge.executor.entity.TaskSubmitItem;
import top.adxd.tikonlinejudge.executor.mapper.TaskRankMapper;
import top.adxd.tikonlinejudge.executor.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;
import top.adxd.tikonlinejudge.social.api.ITaskServiceApi;
import top.adxd.tikonlinejudge.social.api.vo.Task;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2022-01-27
 */
@Service
@DubboService(interfaceClass = ITaskRankServiceApi.class)
public class TaskRankServiceImpl extends ServiceImpl<TaskRankMapper, TaskRank> implements ITaskRankService, ITaskRankServiceApi {
    @Autowired
    private ITaskSubmitItemService taskSubmitItemService;
    @Autowired
    private ISubmitService submitService;
    @DubboReference
    private ITaskServiceApi taskServiceApi;
    @DubboReference
    private IUserInfoService userInfoService;
    @Autowired
    private IProblemService problemService;
    @Autowired
    private ITaskSubmitService taskSubmitService;
    private static final Long PENALTY = 60L * 60L * 5L;

    @Override
    public CommonResult rank(Long raceId) {
        PageUtils.makePage();
        List<TaskRank> taskRanks = baseMapper.rank(raceId);

//        List<TaskRank> taskRanks = list(new QueryWrapper<TaskRank>().orderByDesc("score").orderByAsc("penalty")
//                .select("score", "taskId", "uid", "penalty", ""));
        taskRanks.stream()
                .forEach((item) -> {
                    Map<Long, Integer> problemScore =
                            taskSubmitItemService.list(new QueryWrapper<TaskSubmitItem>()
                                    .eq("task_id", item.getTaskId())
                                    .eq("uid", item.getUid())).stream()
                                    .collect(Collectors.toMap(TaskSubmitItem::getProblemId, TaskSubmitItem::getScore));
                    item.setProblemScores(problemScore);
                    item.setUserNickname(userInfoService.userName(item.getUid()));
                });
        return CommonResult.success().listData(taskRanks);
    }


    @Override
    public void scoreUpdate(Submit submit, Long taskId) {
        TaskSubmitItem taskSubmitItem = taskSubmitItemService.getOne(new QueryWrapper<TaskSubmitItem>()
                .eq("task_id", taskId)
                .eq("uid", submit.getUid())
                .eq("problem_id", submit.getProblemId())
                .select("score"));
        //第一次提交，保存数据
        if (taskSubmitItem == null) {
            taskSubmitItem = new TaskSubmitItem();
            taskSubmitItem.setTaskId(taskId);
            taskSubmitItem.setUid(submit.getUid());
            taskSubmitItem.setProblemId(submit.getProblemId());
            taskSubmitItem.setScore(submit.getScore());
            taskSubmitItemService.save(taskSubmitItem);
        } else {
            if (taskSubmitItem.getScore() < submit.getScore()) {
                taskSubmitItem.setScore(submit.getScore());
                taskSubmitItemService.update(new UpdateWrapper<TaskSubmitItem>()
                        .eq("task_id", taskId)
                        .eq("uid", submit.getUid())
                        .eq("problem_id", submit.getProblemId())
                        .set("score", submit.getScore()));
//                taskSubmitItemService.updateById(taskSubmitItem);
            }
        }
        TaskRank taskRank = getOne(new QueryWrapper<TaskRank>()
                .eq("task_id", taskId)
                .eq("uid", submit.getUid()));
        Long penalty = 0L;
        Task task = taskServiceApi.getTask(taskId);
        Duration between = Duration.between(task.getBeginTime(), submit.getCreateTime());
        penalty = between.getSeconds();

        if (taskRank == null) {
            taskRank = new TaskRank();
            taskRank.setTaskId(taskId);
            taskRank.setUid(submit.getUid());
            taskRank.setScore(taskSubmitItem.getScore());
            taskRank.setPenalty(penalty);
            save(taskRank);
        } else {
            if (!isAccepted(taskId, submit.getUid(), submit.getProblemId())) {
                penalty += PENALTY;
            }
            Integer newSum = taskScore(taskId, submit.getUid());
            if (newSum > taskRank.getScore()) {
                taskRank.setScore(newSum);
                taskRank.setPenalty(taskRank.getPenalty() + penalty);
                update(new UpdateWrapper<TaskRank>()
                        .eq("task_id", taskId)
                        .eq("uid", submit.getUid())
                        .set("score", taskRank.getScore()));
            }
        }
    }

    @Override
    public CommonResult taskProblemNames(Long raceId) {
        List<Long> problems = taskServiceApi.taskProblems(raceId);
        if (problems.size() == 0) {
            return CommonResult.success().singleData(new HashMap<>());
        }
        return problemService.problemNames(problems.toArray(new Long[problems.size()]));
    }

    /**
     * @param taskId
     * @param uid
     * @return 用户的总分
     */
    private Integer taskScore(Long taskId, Long uid) {
        return taskSubmitItemService.list(new QueryWrapper<TaskSubmitItem>()
                .eq("task_id", taskId)
                .eq("uid", uid))
                .stream()
                .mapToInt(TaskSubmitItem::getScore)
                .sum();
    }

    /**
     * @param taskId
     * @param uid
     * @param problemId
     * @return 此比赛的指定题目指定用户是否有成功的提交，没有则罚时
     */
    private boolean isAccepted(Long taskId, Long uid, Long problemId) {
        List<Long> accept = submitService.list(new QueryWrapper<Submit>()
                .eq("problem_id", problemId)
                .eq("uid", uid)
                .eq("status", JudgeStatus.ACCEPT.getValue())
                .select("id"))
                .stream().map(Submit::getId)
                .collect(Collectors.toList());
        if (accept.size() <= 0) {
            return false;
        }
        return taskSubmitService.count(new QueryWrapper<TaskSubmit>()
                .eq("task_id", taskId)
                .in("submit_id", accept)) > 0;
    }

    @Override
    public boolean initializationTaskRank(Long taskId, List<Long> uids) {
        if (taskId == null) {
            return false;
        }
        if (uids == null || uids.size() == 0) {
            return true;
        }
        List<TaskRank> taskRanks = new ArrayList<>();
        uids.forEach((item) -> {
            TaskRank taskRank = new TaskRank();
            taskRank.setTaskId(taskId);
            taskRank.setUid(item);
            taskRank.setPenalty(0L);
            taskRank.setScore(0);
            taskRanks.add(taskRank);
        });
        return saveBatch(taskRanks);
    }
}
