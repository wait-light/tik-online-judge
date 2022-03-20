package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.entity.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IPathMenuMapLoader {
    Map<String, Menu> getFilterChainDefinitionMap();

}
