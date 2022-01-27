package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import com.baomidou.mybatisplus.extension.service.IService;

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
    CommonResult delete(Long problemId);
    CommonResult getAvailableProblem(Long collectionId);
    CommonResult problemNames(Long... problemIds);
    String problemName(Long problemId);
}
