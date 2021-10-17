package top.adxd.tikonlinejudge.executor.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;
import top.adxd.tikonlinejudge.executor.service.impl.DockerJavaCodeJudge;

import java.io.File;

/**
 * @author light
 */
@Component
public class JavaDockerJudgePostProcess implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor {

    /**
     * 根据 availableProcessors 创建执行器个数
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        for (int i =0;i<availableProcessors;i++){
            String threadName = "thread" + i;
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                    .rootBeanDefinition("top.adxd.tikonlinejudge.executor.service.impl.DockerJavaCodeJudge")
                    .getBeanDefinition();
            registry.registerBeanDefinition("dockerCodeJudge"+ threadName,beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DockerJavaCodeJudge){
            DockerJavaCodeJudge dockerJavaCodeJudge = ((DockerJavaCodeJudge) bean);
            JavaDockerConfig dockerJudgeConfig = dockerJavaCodeJudge.getDockerJudgeConfig();
            JavaDockerConfig javaDockerConfig =
                    new JavaDockerConfig(dockerJudgeConfig.getPath() + File.separator + beanName,beanName,dockerJudgeConfig.getImageName(),dockerJudgeConfig.getWorkDir());
            dockerJavaCodeJudge.setDockerJudgeConfig(javaDockerConfig);
        }
        return bean;
    }
}
