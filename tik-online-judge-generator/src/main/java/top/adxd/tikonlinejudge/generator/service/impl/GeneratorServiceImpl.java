package top.adxd.tikonlinejudge.generator.service.impl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.generator.config.GeneratorConfig;
import top.adxd.tikonlinejudge.generator.service.GeneratorService;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@Service("generatorService")
@Import(GeneratorConfig.class)
public class GeneratorServiceImpl implements GeneratorService, InitializingBean {
    @Autowired
    private AutoGenerator autoGenerator;
    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void generate() {
        System.out.println("----------准备生成------------");
        System.out.println("生成路径：" + autoGenerator.getGlobalConfig().getOutputDir());
        System.out.println("是否覆盖原文件：" + generatorConfig.globalProperties.fileOverride);
        autoGenerator.execute();
        System.out.println("----------生成结束------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (generatorConfig.onceRunningAutoGenerate){
            generate();
        }
    }
}
