package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.entity.RoleAsk;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-12-27
 */
public interface IRoleAskService extends IService<RoleAsk> {
    CommonResult ask(RoleAsk roleAsk);
}
