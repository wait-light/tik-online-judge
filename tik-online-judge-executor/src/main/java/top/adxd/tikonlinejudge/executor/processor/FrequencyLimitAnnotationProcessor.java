package top.adxd.tikonlinejudge.executor.processor;

import cn.hutool.extra.servlet.ServletUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.common.util.ServletUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.annotation.FrequencyLimit;
import top.adxd.tikonlinejudge.executor.exception.UnsupportedValue;

import java.util.concurrent.TimeUnit;

/**
 * 频次处理类
 *
 * @author light
 */
@Aspect
@Component
public class FrequencyLimitAnnotationProcessor {
    private final Logger logger = LoggerFactory.getLogger(FrequencyLimitAnnotationProcessor.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String FREQUENCY_LIMIT_KEY = "f:";

    @Around("@annotation(frequencyLimit)")
    public Object frequencyLimit(ProceedingJoinPoint joinPoint, FrequencyLimit frequencyLimit) throws Throwable {
        double value = frequencyLimit.value();
        if (value <= 0) {
            throw new UnsupportedValue("@FrequencyLimit value must be positive number");
        }
//        String name = frequencyLimit.name();
//        String token = ServletUtils.getHeader("id");
//        String id = null;
//        if (token == null){
//            id = name + ServletUtil.getClientIP(ServletUtils.getRequest());
//        }else {
//            Long uid = UserInfoUtil.getUid();
//            if (uid == null){
//                id = name +ServletUtil.getClientIP(ServletUtils.getRequest());
//            }else {
//                id = name + uid;
//            }
//        }
        Long uid = UserInfoUtil.getUid();
        String limitString = redisTemplate.opsForValue().get(FREQUENCY_LIMIT_KEY + uid);
        //说明还在限制期内
        if (limitString != null) {
            return CommonResult.permissionDeny("访问过于频繁，请稍后再尝试");
        }
        redisTemplate.opsForValue().setIfAbsent(FREQUENCY_LIMIT_KEY + uid, "1", (long) (60L / value), TimeUnit.SECONDS);
        return joinPoint.proceed();
    }
}
