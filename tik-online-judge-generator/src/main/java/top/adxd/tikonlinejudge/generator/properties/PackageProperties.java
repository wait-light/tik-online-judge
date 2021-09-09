package top.adxd.tikonlinejudge.generator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@Component
@ConfigurationProperties(prefix = "tik.online.judge.generator.package")
public class PackageProperties {
    public String moduleName,parent;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
