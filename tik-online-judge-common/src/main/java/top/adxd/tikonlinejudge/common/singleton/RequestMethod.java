package top.adxd.tikonlinejudge.common.singleton;

/*
 * @author wait-light
 * @date 2021/10/31.
 */

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 请求方法类型
 */
@Component
public enum RequestMethod {
    GET,
    POST,
    OPTION,
    DELETE,
    PUT;
    private static volatile Map<String, RequestMethod> requestMethodMap;

    private static void initialization() {
        requestMethodMap = new HashMap<>();
        RequestMethod[] values = RequestMethod.values();
        for (RequestMethod requestMethod : values) {
            requestMethodMap.put(requestMethod.toString(), requestMethod);
        }
    }
    public static String RequestMethods2String(RequestMethod... requestMethods) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RequestMethod requestMethod : requestMethods) {
            stringBuilder.append(requestMethod.toString()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
    public static List<RequestMethod> string2RequestMethods(String requestMethodString) {
        if (requestMethodMap == null){
            synchronized(RequestMethod.class){
                if (requestMethodMap == null){
                    initialization();
                }
            }
        }
        String[] splitRequestMethodStrings = requestMethodString.split(",");
        List<RequestMethod> requestMethods = new ArrayList<>(splitRequestMethodStrings.length);
        for (String s : splitRequestMethodStrings) {
            String upperCase = s.toUpperCase(Locale.ROOT);
            if (requestMethodMap.containsKey(upperCase)) {
                RequestMethod requestMethod = requestMethodMap.get(upperCase);
                requestMethods.add(requestMethod);
            }
        }
        return requestMethods;
    }
}
