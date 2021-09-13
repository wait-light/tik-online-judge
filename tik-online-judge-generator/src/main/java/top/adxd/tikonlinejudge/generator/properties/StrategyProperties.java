package top.adxd.tikonlinejudge.generator.properties;

import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@Component
@ConfigurationProperties(prefix = "tik.online.judge.generator.strategy")
public class StrategyProperties {
    public String superEntityClass, superControllerClass, superEntityColumns, include,tablePrefix;
    public Boolean restControllerStyle, entityLombokModel, controllerMappingHyphenStyle;
    public NamingStrategy naming, columnNaming;

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public void setSuperEntityClass(String superEntityClass) {
        this.superEntityClass = superEntityClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String getSuperEntityColumns() {
        return superEntityColumns;
    }

    public void setSuperEntityColumns(String superEntityColumns) {
        this.superEntityColumns = superEntityColumns;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public Boolean getRestControllerStyle() {
        return restControllerStyle;
    }

    public void setRestControllerStyle(Boolean restControllerStyle) {
        this.restControllerStyle = restControllerStyle;
    }

    public Boolean getEntityLombokModel() {
        return entityLombokModel;
    }

    public void setEntityLombokModel(Boolean entityLombokModel) {
        this.entityLombokModel = entityLombokModel;
    }

    public Boolean getControllerMappingHyphenStyle() {
        return controllerMappingHyphenStyle;
    }

    public void setControllerMappingHyphenStyle(Boolean controllerMappingHyphenStyle) {
        this.controllerMappingHyphenStyle = controllerMappingHyphenStyle;
    }

    public NamingStrategy getNaming() {
        return naming;
    }

    public void setNaming(NamingStrategy naming) {
        this.naming = naming;
    }

    public NamingStrategy getColumnNaming() {
        return columnNaming;
    }

    public void setColumnNaming(NamingStrategy columnNaming) {
        this.columnNaming = columnNaming;
    }
}
