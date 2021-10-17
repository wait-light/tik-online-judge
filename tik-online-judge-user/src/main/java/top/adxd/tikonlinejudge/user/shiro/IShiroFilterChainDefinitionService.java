package top.adxd.tikonlinejudge.user.shiro;

import java.util.Map;

/**
 * @author light
 */
public interface IShiroFilterChainDefinitionService {
    Map<String,String> loadFilterChainDefinitionMap();
}
