package top.adxd.tikonlinejudge.auth.service.impl;

import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodResolver;

import javax.annotation.PostConstruct;
import java.util.*;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Service("requestMethodResolver")
public class RequestMethodResolver implements IRequestMethodResolver {
    private Map<String, RequestMethod> requestMethodMap;

    @PostConstruct
    private void initialization() {
        requestMethodMap = new HashMap<>();
        RequestMethod[] values = RequestMethod.values();
        for (RequestMethod requestMethod : values) {
            requestMethodMap.put(requestMethod.toString(), requestMethod);
        }
    }

    @Override
    public String RequestMethods2String(RequestMethod... requestMethods) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RequestMethod requestMethod : requestMethods) {
            stringBuilder.append(requestMethod.toString()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    @Override
    public List<RequestMethod> string2RequestMethods(String requestMethodString) {
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
