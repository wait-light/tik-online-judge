package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.GroupTaskDto;

import javax.validation.constraints.NotNull;

public interface IGroupTaskManagerService {
    CommonResult addTask(Long groupId,GroupTaskDto taskDto);

    CommonResult updateTask(Long groupId,Long taskId, GroupTaskDto taskDto);

    CommonResult deleteTask(Long groupId, Long taskId);
}
