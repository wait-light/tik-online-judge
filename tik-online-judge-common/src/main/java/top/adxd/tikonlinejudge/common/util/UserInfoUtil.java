package top.adxd.tikonlinejudge.common.util;

public class UserInfoUtil {
    public static final String USER_KEY = "uid";
    public static Long getUid() {
        return ServletUtils.getHeader2Long(USER_KEY);
    }
}
