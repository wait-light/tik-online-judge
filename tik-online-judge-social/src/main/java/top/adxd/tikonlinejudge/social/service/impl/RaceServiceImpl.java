package top.adxd.tikonlinejudge.social.service.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IProblemServiceApi;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.service.IRaceService;
import top.adxd.tikonlinejudge.social.service.ITaskItemService;
import top.adxd.tikonlinejudge.social.service.ITaskService;

import java.time.LocalDateTime;

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
}
