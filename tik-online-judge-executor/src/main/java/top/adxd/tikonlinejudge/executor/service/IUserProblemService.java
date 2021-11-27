package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.List;

public interface IUserProblemService {
    /**
     * 当前登录用户指定问题的完成情况
     * @param pid 指定问题列表
     * @return 当前登录用户指定问题的完成情况
     */
    CommonResult userProblemFinishSituations(Long... pid);
    /**
     * 指定用户指定问题的完成情况
     * @param uid 指定用户列表
     * @param pid 指定问题列表
     * @return 指定用户指定问题的完成情况
     */
    CommonResult userProblemFinishSituations(List<Long> uid,List<Long> pid);
}
