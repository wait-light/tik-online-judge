package top.adxd.tikonlinejudge.auth.api;

import top.adxd.tikonlinejudge.auth.api.dto.AuthorizationResult;
import top.adxd.tikonlinejudge.common.singleton.RequestMethod;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

public interface IAuthorizationService {
    /**
     * 利用通过认证的认证令牌获取权限授权
     * @param token 认证令牌
     * @return 返回授权结果
     */
    AuthorizationResult authorization(String token, String path, RequestMethod requestMethod);

    /**
     * 是否有能力做某事
     * @param power
     * @return
     */
    boolean hasPower(String power);
}
