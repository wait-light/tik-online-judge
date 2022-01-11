package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import top.adxd.tikonlinejudge.auth.service.IPathMatcher;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodMatcher;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodResolver;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Service("pathMatcher")
public class PathMatcher implements IPathMatcher {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRequestMethodResolver requestMethodResolver;
    private Map<String, Menu> menus;
    private AntPathMatcher antPathMatcher;
    @Autowired
    private IRequestMethodMatcher requestMethodMatcher;

    @PostConstruct
    private void initialization() {
        antPathMatcher = new AntPathMatcher();
        menus = loadFilterChainDefinitionMap();
    }

    private Map<String, Menu> loadFilterChainDefinitionMap() {
        Map<String, Menu> menuMap = new HashMap<>();
        menuService
                .list(new QueryWrapper<Menu>().eq("type", 2).isNotNull("url").orderByDesc("`order`"))
                .stream()
                .forEach((menu -> {
                    menuMap.put(menu.getUrl(), menu);
                }));
        Menu logged = new Menu();
        logged.setPerms(LOGGED);
        logged.setRequestMethod(requestMethodResolver.RequestMethods2String(RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.OPTION));
        menuMap.put("/**", logged);
        return menuMap;
    }

    @Override
    public Menu match(String path, RequestMethod requestMethod) {
        Iterator<Map.Entry<String, Menu>> iterator = menus.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Menu> next = iterator.next();
            String key = next.getKey();
            Menu value = next.getValue();
            if (antPathMatcher.match(key, path) && requestMethodMatcher.match(requestMethod, value.getRequestMethod())) {
                return next.getValue();
            }
        }
        return null;
    }


}
