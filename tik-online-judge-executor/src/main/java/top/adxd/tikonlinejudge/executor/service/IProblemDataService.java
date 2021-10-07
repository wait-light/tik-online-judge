package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.executor.entity.Submit;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-21
 */
public interface IProblemDataService extends IService<ProblemData> {
    List<ProblemData> getProblemDataList(Long problemId);
    List<ProblemData> getProblemDataList(Submit submit);
}
