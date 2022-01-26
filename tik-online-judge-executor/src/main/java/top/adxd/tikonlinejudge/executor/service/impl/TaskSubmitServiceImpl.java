package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

import java.time.LocalDateTime;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult submit(Submit submit, Long raceId) {
        Long uid = UserInfoUtil.getUid();
        submit.setCreateTime(LocalDateTime.now());
        submit.setUid(uid);
        //保存到数据库
        submitService.save(submit);
        //比赛信息对应提交数据
        TaskSubmit taskSubmit = new TaskSubmit();
        taskSubmit.setSubmitId(submit.getId());
        //发送到消息队列评判
        submitSender.send(submit);
        return CommonResult.success("提交成功");
    }
}
