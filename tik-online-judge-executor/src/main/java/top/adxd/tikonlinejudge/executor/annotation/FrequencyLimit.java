package top.adxd.tikonlinejudge.executor.annotation;

import top.adxd.tikonlinejudge.executor.processor.FrequencyLimitAnnotationProcessor;
import java.lang.annotation.*;

/**
 * 方法访问频率限制
 * @author light
 * @see FrequencyLimitAnnotationProcessor
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FrequencyLimit {
     //每分钟可以访问的次数
     //必须为正数
     double value() default 1d;
     //相同的名称将会互相影响
     String name();
}
