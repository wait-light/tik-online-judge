package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.entity.TaskSubmit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2022-01-24
 */
public interface ITaskSubmitService extends IService<TaskSubmit> {
    CommonResult submit(Submit submit,Long raceId);
    CommonResult submissionList(Long raceId);
}
