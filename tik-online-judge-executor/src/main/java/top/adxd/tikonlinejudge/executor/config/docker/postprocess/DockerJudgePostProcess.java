package top.adxd.tikonlinejudge.executor.config.docker.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.config.docker.impl.CDockerConfig;
import top.adxd.tikonlinejudge.executor.config.docker.impl.CppDockerConfig;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCCodeJudge;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerCppCodeJudge;
import top.adxd.tikonlinejudge.executor.service.docker.judge.DockerJavaCodeJudge;

import java.io.File;

/**
 * @author light
 */
@Component
public class DockerJudgePostProcess implements BeanPostProcessor {

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
        }else if (bean instanceof DockerCppCodeJudge){
            DockerCppCodeJudge dockerJavaCodeJudge = ((DockerCppCodeJudge) bean);
            CppDockerConfig dockerJudgeConfig = dockerJavaCodeJudge.getDockerJudgeConfig();
            CppDockerConfig config =
                    new CppDockerConfig(dockerJudgeConfig.getPath() + File.separator + beanName,beanName,dockerJudgeConfig.getImageName(),dockerJudgeConfig.getWorkDir());
            dockerJavaCodeJudge.setDockerJudgeConfig(config);
        }else if (bean instanceof  DockerCCodeJudge){
            DockerCCodeJudge dockerCCodeJudge = ((DockerCCodeJudge) bean);
            CDockerConfig dockerJudgeConfig = dockerCCodeJudge.getDockerJudgeConfig();
            CDockerConfig config =
                    new CDockerConfig(dockerJudgeConfig.getPath() + File.separator + beanName,beanName,dockerJudgeConfig.getImageName(),dockerJudgeConfig.getWorkDir());
            dockerCCodeJudge.setDockerJudgeConfig(config);
        }
        return bean;
    }
}
