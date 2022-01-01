package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.single.AskStatus;
import top.adxd.tikonlinejudge.common.vo.CommonResult;


public interface IRoleAskAdminService {
    CommonResult list();
    CommonResult examine(Long id, AskStatus askStatus);

}
