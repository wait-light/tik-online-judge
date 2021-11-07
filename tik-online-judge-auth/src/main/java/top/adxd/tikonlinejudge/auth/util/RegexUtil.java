package top.adxd.tikonlinejudge.auth.util;

/**
 * @author wait-light
 * @date 2021/11/5
 */

public class RegexUtil {
    private static final String EMAIL_REG = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
    private RegexUtil(){};
    public static boolean isEmail(String email){
       return email.matches(EMAIL_REG);
    }
}
