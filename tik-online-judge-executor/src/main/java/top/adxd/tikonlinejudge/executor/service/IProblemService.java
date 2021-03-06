package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
public interface IProblemService extends IService<Problem> {
    String NAME_SEARCH_KEY = "search";
    CommonResult delete(Long problemId);
    CommonResult getAvailableProblem(Long collectionId);
    CommonResult problemNames(Long... problemIds);
    String problemName(Long problemId);
    CommonResult problemListByName(String problemName);
    CommonResult userFinishedProblem(Long uid, JudgeStatus status);
}
