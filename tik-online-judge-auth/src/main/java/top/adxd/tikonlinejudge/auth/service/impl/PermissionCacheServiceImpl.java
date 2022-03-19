package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.entity.*;
import top.adxd.tikonlinejudge.auth.service.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("authorizationServiceCacheImpl")
public class PermissionCacheServiceImpl {
    private static final String ADMIN_PERMISSION = "*";
    public static final String USER_CACHE_VALUE = "userAuthorization";
    public static final String USER_CACHE_KEY = "#uid";
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    @Cacheable(value = USER_CACHE_VALUE,key = USER_CACHE_KEY)
    public Set<String> userAuthorization(Long uid) {
        User user = userService.getById(uid);
        Set<String> permissionSet = new LinkedHashSet<>();
        if (user == null) {
            return permissionSet;
        }
        //管理员，拥有所有权限
        if (user.getAdmin()) {
            permissionSet.add(ADMIN_PERMISSION);
            return permissionSet;
        }
        if (!user.getStatus()) {
            return permissionSet;
        }
        //查询用户对应角色id
        List<Long> userRoleIds = userRoleService.list(new QueryWrapper<UserRole>().eq("uid", user.getUid()))
                .stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());
        if (userRoleIds.size() == 0) {
            return permissionSet;
        }
//        List<String> userRoleNames = roleService.list(new QueryWrapper<Role>()
//                        .in("id", userRoleIds)
//                        .eq("status", true)
//                        .select("name"))
//                .stream()
//                .map(item -> item.getName())
//                .collect(Collectors.toList());
        //获取角色对应的权限
        List<Long> userMenuIds = roleMenuService.list(new QueryWrapper<RoleMenu>().in("role_id", userRoleIds))
                .stream()
                .map(item -> item.getMenuId())
                .collect(Collectors.toList());
        if (userMenuIds.size() == 0) {
            return permissionSet;
        }
        menuService.list(new QueryWrapper<Menu>().in("id", userMenuIds))
                .stream()
                .forEach(item -> {
                    permissionSet.add(item.getPerms());
                });
        permissionSet.add(IPathMatcher.LOGGED);
        return permissionSet;
    }


}
