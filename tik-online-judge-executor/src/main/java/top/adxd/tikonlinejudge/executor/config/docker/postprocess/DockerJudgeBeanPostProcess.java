package top.adxd.tikonlinejudge.executor.config.docker.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.common.exeption.CommonException;
import top.adxd.tikonlinejudge.executor.config.LanguageConfig;
import top.adxd.tikonlinejudge.executor.config.SystemLanguageConfig;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;
import top.adxd.tikonlinejudge.executor.service.docker.judge.AbstractDockerJudgeTemplate;
import top.adxd.tikonlinejudge.executor.single.Language;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wait-light
 * @date 2021/10/23 下午2:28
 */
@Component
public class DockerJudgeBeanPostProcess implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware , EnvironmentAware, BeanPostProcessor {

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
                //指定实现类
                String languageCodeJudgeImpl = value.getJudgeImplementClass();
                if (languageCodeJudgeImpl == null || "".equals(languageCodeJudgeImpl.trim())){
                    languageCodeJudgeImpl = getLanguageCodeJudgeImpl(key);
                }
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(languageCodeJudgeImpl).getBeanDefinition();
                for (int i =0;i<availableProcessors;i++){
                    String threadName = "thread" + i;
                    registry.registerBeanDefinition(key.name()+"dockerCodeJudge"+ threadName,beanDefinition);
                }
                hasOne = true;
            }
        }
        //要求至少支持一种语言，否则系统的运行无意义
        if(!hasOne){
            throw new CommonException("至少需要支持一个语言的评判");
        }
    }

    private String getLanguageCodeJudgeImpl(Language language){
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

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private IDockerJudgeConfig createIDockerJudgeConfig(Class<? extends IDockerJudgeConfig> cla){
        IDockerJudgeConfig instance = null;
        try {
            instance = cla.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void checkPathPermission(String path){
        File file = new File(path);
        if (!file.exists()){
            if(!file.mkdirs()){
                throw new CommonException("创建路径失败，请检查对应的路径权限");
            }
        }else {
            if (!file.canExecute() || !file.canRead()){
                throw new CommonException("对于提供的路径没有权限");
            }
        }
    }

    /**
     * 每个AbstractDockerJudgeTemplate新建一个对应的配置信息
     * @param bean 已经创建了的bean
     * @param beanName bean名称
     * @return 添加了新的对应配置信息的AbstractDockerJudgeTemplate
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractDockerJudgeTemplate){
            AbstractDockerJudgeTemplate dockerCodeJudge = (AbstractDockerJudgeTemplate) bean;
            IDockerJudgeConfig dockerJudgeConfig = dockerCodeJudge.getDockerJudgeConfig();
            //使用linux的路径分割符
            String path = dockerJudgeConfig.getPath() + "/" + beanName;
            checkPathPermission(path);
            IDockerJudgeConfig newDockerJudgeConfig = createIDockerJudgeConfig(dockerJudgeConfig.getClass());
            newDockerJudgeConfig.newConfig(path, beanName);
            dockerCodeJudge.setDockerJudgeConfig(newDockerJudgeConfig);
        }
        return bean;
    }
}
