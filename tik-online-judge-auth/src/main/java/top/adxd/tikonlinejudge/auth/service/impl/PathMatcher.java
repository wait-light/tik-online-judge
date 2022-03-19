package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.service.*;
import top.adxd.tikonlinejudge.auth.single.MenuType;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * @author wait-light
 * @date 2021/10/31.
 */
@Service("pathMatcher")
public class PathMatcher implements IPathMatcher {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private IRequestMethodMatcher requestMethodMatcher;
    @Autowired
    private IPathMenuMapLoader pathMenuMapLoader;

    @Override
    public Menu match(String path, RequestMethod requestMethod) {
        Iterator<Map.Entry<String, Menu>> iterator = pathMenuMapLoader
                .getFilterChainDefinitionMap()
                .entrySet()
                .iterator();
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
