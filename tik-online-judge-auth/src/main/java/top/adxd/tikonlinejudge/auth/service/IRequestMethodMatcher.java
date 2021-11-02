package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.common.singleton.RequestMethod;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
public interface IRequestMethodMatcher {
    boolean match(RequestMethod requestMethod,String requestMethodString);
}
