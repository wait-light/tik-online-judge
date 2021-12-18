package top.adxd.tikonlinejudge.common.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import top.adxd.tikonlinejudge.common.annotation.processor.RolePostProcessor;

import java.lang.annotation.*;

/**
 *  用于标注需要自动注入到认证中心的角色，必须标注在Controller或者RestController上
 *  根据  @RequestMapping @GetMapping 等的映射自动生成角色权限
 *
 * @see RequestMapping, RolePostProcessor
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Role {
    /**
     * 自动生产的角色名称
     */
    String name();
    /**
     * 读取target类中的@RequestMapping，被此注解标注的Controller也算作target数组中的类
     */
    Class[] target() default {};

    /**
     *  排除指定的方法及路径
     *  字符串格式：  (method):(path)
     *  例如： get:/common/user/name
     */
    String[] exclude() default {};
}
