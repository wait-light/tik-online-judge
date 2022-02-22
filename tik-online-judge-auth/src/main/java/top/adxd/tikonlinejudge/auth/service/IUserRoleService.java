package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface IUserRoleService extends IService<UserRole> {
    CommonResult userUnRoledRole(Long uid);
    CommonResult removeUserRole(Long uid,Long roleId);
}
