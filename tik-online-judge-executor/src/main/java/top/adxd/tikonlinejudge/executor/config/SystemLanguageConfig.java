package top.adxd.tikonlinejudge.executor.config;

import top.adxd.tikonlinejudge.executor.single.Language;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wait-light
 * @date 2021/10/23 下午4:48
 */
public class SystemLanguageConfig {
    public ConcurrentHashMap<Language,LanguageConfig> map;

    public ConcurrentHashMap<Language, LanguageConfig> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<Language, LanguageConfig> map) {
        this.map = map;
    }
}
