package top.adxd.tikonlinejudge.common.annotation.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import top.adxd.tikonlinejudge.common.annotation.IRoleConfig;
import top.adxd.tikonlinejudge.common.annotation.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理自动注入
 */
/*
TODO 完善自动生成的步骤，效果
 */
//@Component
public class RolePostProcessor implements BeanPostProcessor, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(RolePostProcessor.class);
    private ApplicationContext applicationContext;
    private List<Class> roleClass = new ArrayList<>();
    private List<IRoleConfig> roleConfigs = new ArrayList<>();
    private List<IProcessAble> processAbles = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanType = bean.getClass();
        if (AnnotatedElementUtils.hasAnnotation(beanType, Role.class) && AnnotatedElementUtils.hasAnnotation(beanType, Controller.class)) {
            logger.info("检测到Role：{}", beanName);
            roleClass.add(beanType);
        }
        if (bean instanceof IRoleConfig) {
            logger.info("检测到RoleConfig:{}", beanName);
            roleConfigs.add((IRoleConfig) bean);
        }
        if (bean instanceof IRoleProcessor) {
            logger.info("检测到Role处理器：{}", beanName);
            ((IRoleProcessor) bean).setPendingClass(roleClass);
        }
        if (bean instanceof IRoleConfigProcessor) {
            logger.info("检测到RoleConfig处理器:{}", beanName);
            ((IRoleConfigProcessor) bean).setPendingConfig(roleConfigs);
        }
        if (bean instanceof IProcessAble) {
            processAbles.add((IProcessAble) bean);
        }
        return bean;
    }

    /**
     * 初始化完成，处理Process
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (IProcessAble process : processAbles) {
            process.process();
        }
    }
}
