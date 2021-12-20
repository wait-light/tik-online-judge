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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
