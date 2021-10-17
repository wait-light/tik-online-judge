package top.adxd.tikonlinejudge.user.shiro.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.user.entity.Menu;
import top.adxd.tikonlinejudge.user.service.IMenuService;
import top.adxd.tikonlinejudge.user.shiro.IShiroFilterChainDefinitionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author light
 */
public class ShiroFilterChainDefinitionServiceImpl implements IShiroFilterChainDefinitionService {
    @Autowired
    private IMenuService menuService;

    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = menuService
                .list()
                .stream()
                .collect(Collectors.toMap((menu) -> {
                    menu.getUrl();
                    return "";
                }, (menu) -> {
                    return "";
                }));
        return null;
    }
}
