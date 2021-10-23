package top.adxd.tikonlinejudge.executor.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.data.config.BeanComponentDefinitionBuilder;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.single.Language;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wait-light
 * @date 2021/10/23 下午2:28
 */
@Component
public class DockerJudgeBeanPostProcess implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware , EnvironmentAware {

    private ApplicationContext applicationContext;
    private SystemLanguageConfig systemLanguageConfig;
    /**
     * 根据 availableProcessors 创建执行器个数
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        boolean hasOne = false;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Iterator<Map.Entry<Language, LanguageConfig>> iterator = systemLanguageConfig.map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Language, LanguageConfig> entry = iterator.next();
            Language key = entry.getKey();
            LanguageConfig value = entry.getValue();
            if (value.isOpen()){
                String languageCodeJudgeImple = getLanguageCodeJudgeImple(key);
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(languageCodeJudgeImple).getBeanDefinition();
                for (int i =0;i<availableProcessors;i++){
                    String threadName = "thread" + i;
                    registry.registerBeanDefinition(key.name()+"dockerCodeJudge"+ threadName,beanDefinition);
                }
                hasOne = true;
            }
        }
        if (!hasOne){
            String languageCodeJudgeImple = getLanguageCodeJudgeImple(Language.JAVA);
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(languageCodeJudgeImple).getBeanDefinition();
            for (int i =0;i<availableProcessors;i++){
                String threadName = "thread" + i;
                registry.registerBeanDefinition("JAVAdockerCodeJudge"+ threadName,beanDefinition);
            }
            hasOne = true;
        }
    }

    private String getLanguageCodeJudgeImple(Language language){
        if (language == Language.JAVA){
            return "top.adxd.tikonlinejudge.executor.service.docker.judge.DockerJavaCodeJudge";
        }else if (language == Language.CPP){
            return "top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCppCodeJudge";
        }else if (language == Language.C){
            return "top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCCodeJudge";
        }
        return null;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void setEnvironment(Environment environment) {
        Binder binder = Binder.get(environment);
        BindResult<SystemLanguageConfig> bind = binder.bind("tik-online-judge.executor.language", Bindable.of(SystemLanguageConfig.class));
        SystemLanguageConfig systemLanguageConfig = new SystemLanguageConfig();
        systemLanguageConfig.setMap(new ConcurrentHashMap<>());
        this.systemLanguageConfig = bind.orElse(systemLanguageConfig);
    }
}
