package top.adxd.tikonlinejudge.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.user.config.property.DefaultUserInfo;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.*;
import top.adxd.tikonlinejudge.user.shiro.LoginToken;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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

    public boolean codeVerify(String to, String code) {
        String remoteCode = redisTemplate.opsForValue().get(to);
        if (remoteCode == null) {
            return false;
        }
        return remoteCode.equals(code);
    }
}
