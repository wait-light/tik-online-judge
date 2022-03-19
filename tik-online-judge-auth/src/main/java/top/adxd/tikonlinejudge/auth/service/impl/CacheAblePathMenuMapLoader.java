package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.RequestMethod;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import top.adxd.tikonlinejudge.auth.service.IPathMatcher;
import top.adxd.tikonlinejudge.auth.service.IPathMenuMapLoader;
import top.adxd.tikonlinejudge.auth.service.IRequestMethodResolver;
import top.adxd.tikonlinejudge.auth.single.MenuType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service("cacheAblePathMenuMapLoader")
public class CacheAblePathMenuMapLoader implements IPathMenuMapLoader {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRequestMethodResolver requestMethodResolver;

    public static final String PATH_MENU_CACHE_VALUE = "path_menu";

    @Cacheable(value = PATH_MENU_CACHE_VALUE)
    @Override
    public ConcurrentMap<String, Menu> getFilterChainDefinitionMap() {
        ConcurrentMap<String, Menu> menuMap = new ConcurrentHashMap<>();
        menuService
                .list(new QueryWrapper<Menu>().eq("type", MenuType.INTERFACE).isNotNull("url").orderByDesc("`order`"))
                .stream()
                .forEach((menu -> {
                    menuMap.put(menu.getUrl(), menu);
                }));
        Menu logged = new Menu();
        logged.setPerms(IPathMatcher.LOGGED);
        logged.setRequestMethod(requestMethodResolver.RequestMethods2String(RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.OPTION));
        menuMap.put("/**", logged);
        return menuMap;
    }
}
