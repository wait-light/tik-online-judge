package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.entity.Menu;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public interface IPathMenuMapLoader {
    ConcurrentMap<String, Menu> getFilterChainDefinitionMap();
}
