package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.entity.TaskRank;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2022-01-27
 */
public interface ITaskRankService extends IService<TaskRank> {
    CommonResult rank(Long raceId);
    void scoreUpdate(Submit submit,Long taskId);
    CommonResult taskProblemNames(Long raceId);
}
