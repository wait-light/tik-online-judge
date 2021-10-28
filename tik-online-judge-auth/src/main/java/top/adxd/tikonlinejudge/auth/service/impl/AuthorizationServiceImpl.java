package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.auth.service.IAuthorizationService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:09
 */
public class AuthorizationServiceImpl implements IAuthorizationService {
    @Override
    public CommonResult authorization(String token) {
        return null;
    }

    public void aaa(Long uid){
        //管理员，拥有所有权限
        if (user.getAdmin()){
            simpleAuthorizationInfo.addRole("*");
            simpleAuthorizationInfo.addStringPermission("*");
            return simpleAuthorizationInfo;
        }
        //查询用户对应角色id
        List<Long> userRoleIds = userRoleService.list(new QueryWrapper<UserRole>().eq("uid", user.getUid()))
                .stream()
                .map(item -> {
                    return item.getRoleId();
                })
                .collect(Collectors.toList());
        if (userRoleIds.size() == 0){
            return simpleAuthorizationInfo;
        }
        List<String> userRoleNames = roleService.list(new QueryWrapper<Role>()
                        .in("id", userRoleIds)
                        .eq("status", true)
                        .select("name"))
                .stream()
                .map(item -> {
                    return item.getName();
                })
                .collect(Collectors.toList());
        //添加角色
        simpleAuthorizationInfo.addRoles(userRoleNames);
        List<Long> userMenuIds = roleMenuService.list(new QueryWrapper<RoleMenu>().in("role_id", userRoleIds))
                .stream()
                .map(item -> {
                    return item.getMenuId();
                })
                .collect(Collectors.toList());
        if (userMenuIds.size() == 0){
            return simpleAuthorizationInfo;
        }
        List<String> permsList = menuService.list(new QueryWrapper<Menu>().in("id", userMenuIds))
                .stream()
                .map(item -> {
                    return item.getPerms();
                })
                .collect(Collectors.toList());
    }
}
