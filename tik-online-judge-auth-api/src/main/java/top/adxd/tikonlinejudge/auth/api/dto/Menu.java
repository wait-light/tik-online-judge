package top.adxd.tikonlinejudge.auth.api.dto;


import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方法类型
     */
    private String requestMethod;

    public String getName() {
        return name;
    }

    public String getPerms() {
        return perms;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }
}
