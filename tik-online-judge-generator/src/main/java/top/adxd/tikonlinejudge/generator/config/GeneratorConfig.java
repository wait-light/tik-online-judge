package top.adxd.tikonlinejudge.generator.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import top.adxd.tikonlinejudge.generator.properties.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@Configuration
public class GeneratorConfig {
    public static Logger log = LoggerFactory.getLogger(GeneratorConfig.class);
    @Autowired
    public DatasourceProperties datasourceProperties;
    @Autowired
    public PackageProperties packageProperties;
    @Autowired
    public StrategyProperties strategyProperties;
    @Autowired
    public GlobalProperties globalProperties;
    @Autowired
    public TemplateProperties templateProperties;


    @Value("${tik.online.judge.generator.onceRunningAutoGenerate}")
    public Boolean onceRunningAutoGenerate = true;

    public Boolean getOnceRunningAutoGenerate() {
        return onceRunningAutoGenerate;
    }

    public void setOnceRunningAutoGenerate(Boolean onceRunningAutoGenerate) {
        this.onceRunningAutoGenerate = onceRunningAutoGenerate;
    }

    //数据源配置
    @Bean("dataSourceConfig")
    public DataSourceConfig dataSourceConfig(DatasourceProperties datasourceProperties) {
        if (!StringUtils.hasText(datasourceProperties.getDriverName())) {
            log.error("[tik.online.judge.generator.datasource]  Must configure driverName.");
        }
        if (!StringUtils.hasText(datasourceProperties.getSchemaName())) {
            log.error("[tik.online.judge.generator.datasource]  Must configure schemaName.");
        }
        if (!StringUtils.hasText(datasourceProperties.getUrl())) {
            log.error("[tik.online.judge.generator.datasource]  Must configure url.");
        }
        if (!StringUtils.hasText(datasourceProperties.getUsername())) {
            log.error("[tik.online.judge.generator.datasource]  Must configure username.");
        }
        if (!StringUtils.hasText(datasourceProperties.getPassword())) {
            log.error("[tik.online.judge.generator.datasource]  Must configure password.");
        }
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        BeanUtils.copyProperties(datasourceProperties, dataSourceConfig);
        return dataSourceConfig;
    }

    //包配置
    @Bean("packageConfig")
    public PackageConfig packageConfig(PackageProperties packageProperties) {
        if (!StringUtils.hasText(packageProperties.getModuleName())) {
            log.error("[tik.online.judge.generator.package]  Must configure moduleName.");
        }
        if (!StringUtils.hasText(packageProperties.getParent())) {
            log.error("[tik.online.judge.generator.package]  Must configure parent.");
        }
        PackageConfig packageConfig = new PackageConfig();
        BeanUtils.copyProperties(packageProperties, packageConfig);
        return packageConfig;
    }

    //代码生成策略
    @Bean("strategyConfig")
    public StrategyConfig strategyConfig(StrategyProperties strategyProperties) {
        StrategyConfig strategyConfig = new StrategyConfig();
        BeanUtils.copyProperties(strategyProperties, strategyConfig);
        return strategyConfig;
    }

    @Bean("globalConfig")
    public GlobalConfig globalConfig(GlobalProperties globalProperties) {
        GlobalConfig globalConfig = new GlobalConfig();
        BeanUtils.copyProperties(globalProperties, globalConfig);
        //输出路径为本项目下
        if (globalProperties.isBaseDirInProject()) {
            String projectPath = System.getProperty("user.dir");
            globalConfig.setOutputDir(projectPath + globalProperties.getOutputDir());
        }
        return globalConfig;
    }

    @Bean("templateConfig")
    public TemplateConfig templateConfig(TemplateProperties templateProperties) {
        TemplateConfig templateConfig = new TemplateConfig();
        BeanUtils.copyProperties(templateProperties, templateConfig);
        return templateConfig;
    }

    @Bean("injectionConfig")
    public InjectionConfig injectionConfig(@Autowired TemplateProperties templateProperties,
                                           @Autowired PackageProperties packageProperties) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        String projectPath = System.getProperty("user.dir");
        String templatePath = StringUtils.hasLength(templateProperties.getTemplatePath())
                ? templateProperties.getTemplatePath() : "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageProperties.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        injectionConfig.setFileOutConfigList(focList);

        return injectionConfig;
    }

    //自动生成器
    @Bean("autoGenerator")
    public AutoGenerator autoGenerator(@Autowired DataSourceConfig dataSourceConfig,
                                       @Autowired GlobalConfig globalConfig,
                                       @Autowired PackageConfig packageConfig,
                                       @Autowired StrategyConfig strategyConfig,
                                       @Autowired InjectionConfig injectionConfig) {
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setCfg(injectionConfig);

        return autoGenerator;
    }
}
