package top.adxd.tikonlinejudge.common.util;

public class UserInfoUtil {
    public static final String USER_KEY = "uid";
    public static final String ADMIN_KEY = "isAdmin";

    public static Long getUid() {
        return ServletUtils.getHeader2Long(USER_KEY);
    }

    public static Boolean isAdmin() {
        return Boolean.parseBoolean(ServletUtils.getHeader(ADMIN_KEY));
    }
}
