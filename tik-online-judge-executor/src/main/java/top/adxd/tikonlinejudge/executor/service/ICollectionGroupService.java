package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.CollectionGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.executor.entity.Problem;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-17
 */
public interface ICollectionGroupService extends IService<CollectionGroup> {
    List<Problem> groupProblems(Long groupId);
    CommonResult addProblem(Long groupId, Problem problem);
    boolean deleteProblem(Long groupId, Long problemId);
    CommonResult updateProblem(Long groupId,Problem problem);
    CommonResult groupCollections(Long groupId);

}
