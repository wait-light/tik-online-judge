package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.entity.TaskSubmit;
import top.adxd.tikonlinejudge.executor.mapper.TaskSubmitMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemService;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.service.ITaskSubmitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.mq.SubmitSender;
import top.adxd.tikonlinejudge.executor.vo.SubmitInfo;
import top.adxd.tikonlinejudge.social.api.ITaskServiceApi;
import top.adxd.tikonlinejudge.social.api.vo.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2022-01-24
 */
@Service
public class TaskSubmitServiceImpl extends ServiceImpl<TaskSubmitMapper, TaskSubmit> implements ITaskSubmitService {
    @Autowired
    private ISubmitService submitService;
    @Autowired
    private SubmitSender submitSender;
    @Autowired
    private IProblemService problemService;
    @DubboReference
    private IUserInfoService userInfoService;
    @DubboReference
    private ITaskServiceApi taskServiceApi;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult submit(Submit submit, Long raceId) {
        Task task = taskServiceApi.getTask(raceId);
        LocalDateTime now = LocalDateTime.now();
        //未开始，或者已经结束
        if (task.getEndTime().isBefore(now)) {
            return CommonResult.permissionDeny("已结束,禁止提交");
        }
        if (task.getBeginTime().isAfter(now)) {
            return CommonResult.permissionDeny("未开始,禁止提交");
        }

        Long uid = UserInfoUtil.getUid();
        submit.setCreateTime(now);
        submit.setUid(uid);
        //保存到数据库
        submitService.save(submit);
        //比赛信息对应提交数据
        TaskSubmit taskSubmit = new TaskSubmit();
        taskSubmit.setSubmitId(submit.getId());
        taskSubmit.setTaskId(raceId);
        save(taskSubmit);
        //发送到消息队列评判
        submitSender.send(submit);
        return CommonResult.success("提交成功");
    }

    @Override
    public CommonResult submissionList(Long raceId) {
        PageUtils.makePage(false);
        List<Long> submissionIds = list(new QueryWrapper<TaskSubmit>().eq("task_id", raceId).select("submit_id"))
                .stream()
                .map(TaskSubmit::getSubmitId)
                .collect(Collectors.toList());
        if (submissionIds.size() == 0) {
            return CommonResult.success().add("submissions", new ArrayList<>());
        }
        PageUtils.MakeOrder();
        List<SubmitInfo> collect = submitService.list(new QueryWrapper<Submit>().in("id", submissionIds)
                .select("id", "uid", "language_type", "create_time", "status", "problem_id", "runtime", "runtime_memory", "score"))
                .stream()
                .map(item -> {
                    SubmitInfo submitInfo = new SubmitInfo();
                    BeanUtils.copyProperties(item, submitInfo);
                    submitInfo.setProblemName(problemService.problemName(item.getProblemId()));
                    submitInfo.setUserName(userInfoService.userName(submitInfo.getUid()));
                    return submitInfo;
                })
                .collect(Collectors.toList());
        return CommonResult.success().listData(collect);
    }
}
