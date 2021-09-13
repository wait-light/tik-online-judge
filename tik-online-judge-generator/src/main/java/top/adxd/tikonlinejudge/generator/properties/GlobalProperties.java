package top.adxd.tikonlinejudge.generator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@Component
@ConfigurationProperties(prefix = "tik.online.judge.generator.global")
public class GlobalProperties {
    public String outputDir = "D:", author, moduleDir = "", classDir = "/src/main/java", xmlDir = "/src/main/resources/mapper";
    public boolean open, kotlin, swagger2, activeRecord, baseResultMap, enableCache, fileOverride, baseDirInProject;

    public String getClassDir() {
        return classDir;
    }

    public void setClassDir(String classDir) {
        this.classDir = classDir;
    }

    public String getXmlDir() {
        return xmlDir;
    }

    public void setXmlDir(String xmlDir) {
        this.xmlDir = xmlDir;
    }

    public String getModuleDir() {
        return moduleDir;
    }

    public void setModuleDir(String moduleDir) {
        this.moduleDir = moduleDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isKotlin() {
        return kotlin;
    }

    public void setKotlin(boolean kotlin) {
        this.kotlin = kotlin;
    }

    public boolean isSwagger2() {
        return swagger2;
    }

    public void setSwagger2(boolean swagger2) {
        this.swagger2 = swagger2;
    }

    public boolean isActiveRecord() {
        return activeRecord;
    }

    public void setActiveRecord(boolean activeRecord) {
        this.activeRecord = activeRecord;
    }

    public boolean isBaseResultMap() {
        return baseResultMap;
    }

    public void setBaseResultMap(boolean baseResultMap) {
        this.baseResultMap = baseResultMap;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public boolean isBaseDirInProject() {
        return baseDirInProject;
    }

    public void setBaseDirInProject(boolean baseDirInProject) {
        this.baseDirInProject = baseDirInProject;
    }
}
