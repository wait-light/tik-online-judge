package top.adxd.tikonlinejudge.auth.service;


import top.adxd.tikonlinejudge.auth.api.RequestMethod;

import java.util.List;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
public interface IRequestMethodResolver {
    String RequestMethods2String(RequestMethod... requestMethods);
    List<RequestMethod> string2RequestMethods(String requestMethodString);
}
