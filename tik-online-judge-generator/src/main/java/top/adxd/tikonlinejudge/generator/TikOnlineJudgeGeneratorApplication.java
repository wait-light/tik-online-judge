package top.adxd.tikonlinejudge.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.adxd.tikonlinejudge.generator.properties.DatasourceProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wait_light
 * @create 2021/9/5
 */
@SpringBootApplication
public class TikOnlineJudgeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikOnlineJudgeGeneratorApplication.class, args);
    }
}
