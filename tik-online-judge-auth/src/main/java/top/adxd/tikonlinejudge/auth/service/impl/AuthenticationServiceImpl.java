package top.adxd.tikonlinejudge.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.config.UserDefaultConfig;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.service.IAuthenticationService;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.Email;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;
import top.adxd.tikonlinejudge.message.api.VerifyCodeStatus;

import java.time.LocalDateTime;

/**
 * @author wait-light
 * @date 2021/10/26 下午5:09
 */
@Service("authenticationServiceImpl")
public class AuthenticationServiceImpl implements IAuthenticationService {
    public static final String EMAIL_VERIFICATION_CODE_KEY = "email:";
    @Autowired
    private IUserService userService;
    @Autowired
    private SecureConfig secureConfig;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserDefaultConfig userDefaultConfig;
    @Autowired
    private JWTUtil jwtUtil;
    @DubboReference
    private IVerificationCodeService emailVerificationCodeService;

    @Override
    public CommonResult usernameLogin(String username, String password) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null || user.getPassword() == null) {
            return CommonResult.error("用户名不存在或者密码错误");
        }
        String hexPassword = secureConfig.getSymmetricCrypto().encryptHex(password);
        if (hexPassword.equals(user.getPassword())) {
            String token = jwtUtil.generate(user);
            return CommonResult.success("登录成功").add("token", token);
        }
        return CommonResult.error("用户名不存在或者密码错误");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult emailLogin(String email, String code) {
        if(code == null){
            return CommonResult.error("验证码为空");
        }
        VerifyCodeStatus verifyCodeStatus = emailVerificationCodeService.verifyCode(email, code);
        if (verifyCodeStatus == VerifyCodeStatus.success) {
            User user = saveUserIfNotExist(email);
            String token = jwtUtil.generate(user);
            return CommonResult.success("登录成功").add("token", token);
        }
        return CommonResult.error("验证码错误");
    }

    private User saveUserIfNotExist(String email) {
        User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
        if (user == null) {
            user = new User();
            LocalDateTime now = LocalDateTime.now();
            user.setAdmin(false);
            user.setCreateTime(now);
            user.setUpdateTime(now);
            user.setEmail(email);
            user.setNickname(RandomUtil.randomString(8));
            user.setUsername(email);
            user.setStatus(true);
            user.setAvatar(userDefaultConfig.getAvatar());
            userService.save(user);
        }
        return user;
    }
}
