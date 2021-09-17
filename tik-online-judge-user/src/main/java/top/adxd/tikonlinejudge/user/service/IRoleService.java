package top.adxd.tikonlinejudge.user.service;

import top.adxd.tikonlinejudge.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
public interface IRoleService extends IService<Role> {
    public boolean deleteRoleWithMenu(Long roleId);
    public boolean deleteRoleBatchWithMenu(Long[] roleIds);
}
