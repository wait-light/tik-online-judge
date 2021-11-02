package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.common.singleton.RequestMethod;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:03
 */
public interface IAuthorizationService {
    /**
     * 利用通过认证的认证令牌获取权限授权
     * @param token 认证令牌
     * @return 返回授权结果
     */
    CommonResult authorization(String token, String path, RequestMethod requestMethod);
}
