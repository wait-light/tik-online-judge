package top.adxd.tikonlinejudge.common.annotation.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import top.adxd.tikonlinejudge.common.annotation.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理自动注入
 */
@Component
public class RolePostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(RolePostProcessor.class);
    private ApplicationContext applicationContext;
    private List<Class> roleClass = new ArrayList<>();

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
        if (bean instanceof IRoleProcessor) {
            logger.info("检测到Role处理器：{}", beanName);
            ((IRoleProcessor) bean).setPendingClass(roleClass);
        }
        return bean;
    }
}
