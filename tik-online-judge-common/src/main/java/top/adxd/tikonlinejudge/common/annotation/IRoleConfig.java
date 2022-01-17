package top.adxd.tikonlinejudge.common.annotation;

/**
 * 对应注解@Role
 * 用于配置类注入Role，而不是将注解@Role放在Controller上
 *
 * @See also @Role
 */
public interface IRoleConfig {
    String getName();

    Class[] getTarget();

    String[] getExclude();

    Boolean isAppend();
}
