package top.adxd.tikonlinejudge.user.service.impl;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.user.api.Token2User;
import top.adxd.tikonlinejudge.user.config.TokenConfig;
import top.adxd.tikonlinejudge.user.api.Vo.SafeUserVo;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IUserService;
import top.adxd.tikonlinejudge.user.service.api.impl.Token2UserImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserTokenService extends Token2UserImpl {
//    private static final String USER_TOKEN_KEY = "login:";
    public static final String SESSION_TOKEN = "token";
    public static final String SESSION_USER = "user";
    @Autowired
    private TokenConfig tokenConfig;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private IUserService userService;

    public void issueRenewalToken(User user){
        Map<String,Object> map = new HashMap<>();
        //签发时长
        Instant expireTime = Instant.now().plus(timeUnit2Duration(tokenConfig.getType(),tokenConfig.getDefaultIssueRenewalTime()));
        map.put("uid",user.getUid());
        map.put("expire_time",expireTime);
        String token = JWTUtil.createToken(map, tokenConfig.getSecretKey().getBytes());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(SESSION_USER,session);
        session.setAttribute(SESSION_TOKEN,token);
    }

    public Duration timeUnit2Duration(TimeUnit timeUnit,Long length){
        Duration result = null;
        if (TimeUnit.DAYS == timeUnit){
            result = Duration.ofDays(length);
        }else if (TimeUnit.HOURS == TimeUnit.HOURS){
            result = Duration.ofHours(length);
        }else if (TimeUnit.SECONDS == TimeUnit.SECONDS){
            result = Duration.ofSeconds(length);
        }
        return result;
    }
}
