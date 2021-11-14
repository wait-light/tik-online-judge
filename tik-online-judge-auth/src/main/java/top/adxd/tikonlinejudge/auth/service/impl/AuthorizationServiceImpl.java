package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.api.dto.AuthorizationResult;
import top.adxd.tikonlinejudge.auth.entity.*;
import top.adxd.tikonlinejudge.auth.service.*;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;
import top.adxd.tikonlinejudge.common.singleton.RequestMethod;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:09
 */
@Service("authorizationServiceImpl")
@DubboService
public class AuthorizationServiceImpl implements IAuthorizationService {

    private static final String ADMIN_PERMISSION = "*";
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
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private IPathMatcher pathMatcher;
    @Autowired
    private IRequestMethodMatcher requestMethodMatcher;

    @Override
    public AuthorizationResult authorization(String token, String path, RequestMethod requestMethod) {
        Long uid = jwtUtil.uid(token);
        if (uid == null) {
            return new AuthorizationResult(false, null, "未登录或登录已过期");
        }
        Menu matchMenu = pathMatcher.match(path);
        if (matchMenu == null) {
            return new AuthorizationResult(false, null, "拒绝访问");
        }
        Set<String> permissionSet = userAuthorization(uid);
        if (permissionSet.contains("*") || (permissionSet.contains(matchMenu.getPerms())) && requestMethodMatcher.match(requestMethod, matchMenu.getRequestMethod())) {
            return new AuthorizationResult(false, uid, "权限校验成功");
        }
        return new AuthorizationResult(false, null, "拒绝访问");
    }

    public Set<String> userAuthorization(Long uid) {
        User user = userService.getById(uid);
        Set<String> permissionSet = new HashSet<>();
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
        List<String> userRoleNames = roleService.list(new QueryWrapper<Role>()
                        .in("id", userRoleIds)
                        .eq("status", true)
                        .select("name"))
                .stream()
                .map(item -> item.getName())
                .collect(Collectors.toList());
        //添加角色
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
