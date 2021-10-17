package top.adxd.tikonlinejudge.user.shiro.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.config.property.DefaultUserInfo;
import top.adxd.tikonlinejudge.user.entity.*;
import top.adxd.tikonlinejudge.user.service.*;
import top.adxd.tikonlinejudge.user.service.impl.UserTokenService;
import top.adxd.tikonlinejudge.user.shiro.IUserRealmService;
import top.adxd.tikonlinejudge.user.shiro.LoginToken;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author light
 */
@Service
public class UserRealmServiceImpl implements IUserRealmService {
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserService userService;
    private static final Long EMAIL_EXPIRED_TIME = 3L;

    @Autowired
    private IMailService mailService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private DefaultUserInfo defaultUserInfo;
    @Autowired
    private UserTokenService userTokenService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void emailLogin(LoginToken loginToken) {
        /**
         * 验证成功
         * 若是用户以存在，则登录
         * 不存在则创建一个用户
         */
        if (codeVerify((String) loginToken.getPrincipal(), (String) loginToken.getCredentials())) {
            User user = userService.getUser((String) loginToken.getPrincipal());
            if (user == null) {
                user = new User();
                user.setEmail((String) loginToken.getPrincipal());
                LocalDateTime now = LocalDateTime.now();

                user.setCreateTime(now);
                user.setUpdateTime(now);
                user.setAvatar(defaultUserInfo.getAvatar());
                user.setStatus(true);
                user.setUsername(user.getEmail());
                user.setNickname(RandomUtil.randomString(15));
                userService.save(user);
            }
            userTokenService.issueRenewalToken(user);
        } else {
            throw new AuthenticationException("验证码错误");
        }
    }

    @Override
    public CommonResult sendCode(String email,String code) {
        if (redisTemplate.opsForValue().setIfAbsent(email,code , EMAIL_EXPIRED_TIME, TimeUnit.MINUTES)) {
            return mailService.sendCode(email);
        } else {
            return CommonResult.error("请求过于频繁");
        }
    }

    @Override
    public AuthorizationInfo getUserAuthorizationInfo(User user) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
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
        simpleAuthorizationInfo.addStringPermissions(permsList);
        return simpleAuthorizationInfo;
    }


    public boolean codeVerify(String to, String code) {
        String remoteCode = redisTemplate.opsForValue().get(to);
        if (remoteCode == null) {
            return false;
        }
        return remoteCode.equals(code);
    }
}
