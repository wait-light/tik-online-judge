package top.adxd.tikonlinejudge.auth.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.api.dto.AuthorizationResult;
import top.adxd.tikonlinejudge.auth.entity.*;
import top.adxd.tikonlinejudge.auth.service.*;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:09
 */
@Service("authorizationServiceImpl")
@DubboService
@EnableCaching
public class AuthorizationServiceImpl implements IAuthorizationService {

    private static final String ADMIN_PERMISSION = "*";
    @Autowired
    private PermissionCacheServiceImpl authorizationServiceCache;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private IPathMatcher pathMatcher;
    @Autowired
    private IRoleService roleService;

    @Override
    public AuthorizationResult authorization(String token, String path, RequestMethod requestMethod) {
        Long uid = jwtUtil.uid(token);
        Menu matchMenu = pathMatcher.match(path, requestMethod);
        if (matchMenu == null) {
            return new AuthorizationResult(false, null, "拒绝访问", false);
        }
        Set<String> permissionSet = null;
        if (uid != null) {
            permissionSet = loginedAuthorization(uid);
            //匿名权限也要拥有
            permissionSet.addAll(unloginAuthorization());
        } else {
            permissionSet = unloginAuthorization();
        }
        if (permissionSet.contains(ADMIN_PERMISSION) || (permissionSet.contains(matchMenu.getPerms()))) {
            return new AuthorizationResult(true, uid, "权限校验成功", permissionSet.contains(ADMIN_PERMISSION));
        }
        return new AuthorizationResult(false, null, "拒绝访问", false);
    }

    @Override
    public boolean hasPower(String power) {
        Set<String> powers = authorizationServiceCache.userAuthorization(UserInfoUtil.getUid());
        return powers.contains(power) || powers.contains(ADMIN_PERMISSION);
    }

    public Set<String> loginedAuthorization(Long uid) {
        Set<String> permissionSet = authorizationServiceCache.userAuthorization(uid);
        permissionSet.add(IPathMatcher.LOGGED);
        return permissionSet;
    }

    public Set<String> unloginAuthorization() {
        return roleService.rolePermissions(ANONYMOUS_ROLE);
    }
}
