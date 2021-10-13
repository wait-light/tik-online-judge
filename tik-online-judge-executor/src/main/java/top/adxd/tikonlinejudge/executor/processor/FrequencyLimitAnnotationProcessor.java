package top.adxd.tikonlinejudge.executor.processor;

import cn.hutool.extra.servlet.ServletUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.common.util.ServletUtils;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.annotation.FrequencyLimit;
import top.adxd.tikonlinejudge.executor.exception.UnsupportedValue;
import top.adxd.tikonlinejudge.user.api.Token2User;

import java.util.concurrent.TimeUnit;

/**
 * 频次处理类
 * @author light
 */
@Aspect
@Component
public class FrequencyLimitAnnotationProcessor {
    private final Logger logger = LoggerFactory.getLogger(FrequencyLimitAnnotationProcessor.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @DubboReference
    private Token2User token2User;
    @Around("@annotation(frequencyLimit)")
    public Object frequencyLimit(ProceedingJoinPoint joinPoint, FrequencyLimit frequencyLimit) throws Throwable {
        double value = frequencyLimit.value();
        if (value <= 0){
            throw new UnsupportedValue("@FrequencyLimit value must be positive number");
        }
        String name = frequencyLimit.name();
        String token = ServletUtils.getHeader("token");
        String id = null;
        if (token == null){
            id = name + ServletUtil.getClientIP(ServletUtils.getRequest());
        }else {
            Long uid = token2User.uid(token);
            if (uid == null){
                id = name +ServletUtil.getClientIP(ServletUtils.getRequest());
            }else {
                id = name + uid;
            }
        }
        String limitString = redisTemplate.opsForValue().get(id);
        //说明还在限制期内
        if (limitString != null){
            return CommonResult.permissionDeny("访问过于频繁，请稍后再尝试");
        }
        redisTemplate.opsForValue().setIfAbsent(id, "1", (long) (60L/value), TimeUnit.SECONDS);
        return joinPoint.proceed();
    }
}
