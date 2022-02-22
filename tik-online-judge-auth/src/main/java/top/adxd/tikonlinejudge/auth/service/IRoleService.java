package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.dto.RoleDto;
import top.adxd.tikonlinejudge.auth.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface IRoleService extends IService<Role> {
    CommonResult save(RoleDto entity);
    CommonResult updateById(RoleDto roleDto);
    Role role(String name);
    Set<String> rolePermissions(String roleName);
    CommonResult userRoles(Long uid);
}
