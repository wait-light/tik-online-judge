package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.executor.vo.ProblemSurvey;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
public interface IProblemCollectionService extends IService<ProblemCollection> {
    List<ProblemSurvey> collectionsItem(Long collectionId,boolean all);
    CommonResult addProblem(Problem problem,Long collectionId);
    boolean isInCollection(Long collectionId,Long problemId);
    CommonResult collectionProblems(Long collectionId);
}
