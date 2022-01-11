package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.entity.Menu;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
public interface IPathMatcher {
    String LOGGED = "logged";
    String ANONYMOUS = "anon";
    Menu match(String path, RequestMethod requestMethod);
}
