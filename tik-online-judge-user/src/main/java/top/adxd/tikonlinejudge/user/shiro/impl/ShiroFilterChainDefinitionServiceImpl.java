package top.adxd.tikonlinejudge.user.shiro.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.user.entity.Menu;
import top.adxd.tikonlinejudge.user.service.IMenuService;
import top.adxd.tikonlinejudge.user.shiro.IShiroFilterChainDefinitionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author light
 */
@Service
public class ShiroFilterChainDefinitionServiceImpl implements IShiroFilterChainDefinitionService {
    @Autowired
    private IMenuService menuService;
    private static final String PERMS = "perms";

    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = menuService
                .list(new QueryWrapper<Menu>().eq("type",2).isNotNull("url").orderByDesc("`order`"))
                .stream()
                .collect(Collectors.toMap((menu) -> {
                    return menu.getUrl();
                }, (menu) -> {
                    return PERMS +"[" + menu.getPerms() + "]";
                },(x,y)->{return x;}
                ));
        return filterChainDefinitionMap;
    }
}
