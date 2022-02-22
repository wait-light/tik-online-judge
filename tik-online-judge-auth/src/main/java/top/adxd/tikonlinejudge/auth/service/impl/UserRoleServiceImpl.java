package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.auth.dto.UserRoleWithRemarkVo;
import top.adxd.tikonlinejudge.auth.entity.Role;
import top.adxd.tikonlinejudge.auth.entity.UserRole;
import top.adxd.tikonlinejudge.auth.mapper.UserRoleMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import top.adxd.tikonlinejudge.auth.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private IRoleService roleService;

    @Override
    public CommonResult userUnRoledRole(Long uid) {
        if (uid == null) {
            return CommonResult.error("用户不存在");
        }
        List<Long> roleIds = list(new QueryWrapper<UserRole>().eq("uid", uid).select("role_id"))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        if (roleIds.size() == 0) {
            roleIds.add(0L);
        }
        return CommonResult.success().singleData(
                roleService.list(new QueryWrapper<Role>()
                        .notIn("id", roleIds)
                        .select("id", "name", "remark"))
                        .stream()
                        .map(UserRoleServiceImpl::role2UserRoleWithRemarkVo)
                        .collect(Collectors.toList()));
    }

    @Override
    public CommonResult removeUserRole(Long uid, Long roleId) {
        UserRole one = getOne(new QueryWrapper<UserRole>().eq("uid", uid).eq("role_id", roleId).last("limit 1"));
        if (one == null) {
            return CommonResult.error("关系不存在");
        }
        return removeById(one.getId()) ? CommonResult.success("移除成功") : CommonResult.error("移除失败");
    }

    private static UserRoleWithRemarkVo role2UserRoleWithRemarkVo(Role role) {
        UserRoleWithRemarkVo userRoleWithRemarkVo = new UserRoleWithRemarkVo();
        BeanUtils.copyProperties(role, userRoleWithRemarkVo);
        return userRoleWithRemarkVo;
    }
}
