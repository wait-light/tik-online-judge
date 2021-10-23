package top.adxd.tikonlinejudge.user.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * @author wait-light
 * @date 2021/10/18 下午10:14
 */
public interface PermissionServer {
    /**
     *
     * @param token 登录用户的Token
     * @param url 请求路径
     * @param Method 请求方法
     * @return 是否有访问某个路径的权利
     */
    boolean hasPermission(@Nullable String token, @NotNull String url,@NotNull String Method);
}
