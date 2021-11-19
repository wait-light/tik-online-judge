package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;

public interface IUserProblemService {
    CommonResult userProblemFinishSituations(Long... pid);
}
