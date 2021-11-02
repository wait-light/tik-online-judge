package top.adxd.tikonlinejudge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodMatcher;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodResolver;
import top.adxd.tikonlinejudge.common.singleton.RequestMethod;

import java.util.List;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Service("requestMethodMatcher")
public class RequestMethodMatcher implements IRequestMethodMatcher {
    @Autowired
    private IRequestMethodResolver requestMethodResolver;

    @Override
    public boolean match(RequestMethod requestMethod, String requestMethodString) {
        List<RequestMethod> requestMethods = requestMethodResolver.string2RequestMethods(requestMethodString);
        for (RequestMethod method : requestMethods) {
            if (method == requestMethod) {
                return true;
            }
        }
        return false;
    }
}