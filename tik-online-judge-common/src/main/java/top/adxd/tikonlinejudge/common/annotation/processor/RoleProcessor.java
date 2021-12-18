package top.adxd.tikonlinejudge.common.annotation.processor;


import cn.hutool.core.annotation.AnnotationUtil;
import org.apache.dubbo.common.utils.AnnotationUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import top.adxd.tikonlinejudge.auth.api.IRoleService;
import top.adxd.tikonlinejudge.common.annotation.Role;
import top.adxd.tikonlinejudge.common.util.TikAnnotationUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoleProcessor implements IRoleProcessor, InitializingBean {
    @DubboReference
    private IRoleService roleService;
    private List<Class> pendingClass;

    @Override
    public void setPendingClass(List<Class> pendingClass) {
        this.pendingClass = pendingClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        process();
    }

    public void process() {
        for (Class c : pendingClass) {
//            Role annotation = AnnotationUtils.getAnnotation(c, Role.class);

            Annotation[] annotations = AnnotationUtil.getAnnotations(c, false);
            List<Annotation> allDeclaredAnnotations = AnnotationUtils.getAllDeclaredAnnotations(c);
            TikAnnotationUtil.getClassMethodAnnotations(c,c);
//            System.out.println(getOneRole(annotations));
//            System.out.println(getRequestMappingList(annotations));
//            System.out.println(annotation);
        }
    }

    private Role getOneRole(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Role) {
                return (Role) annotation;
            }
        }
        return null;
    }

    private List<RequestMapping> getRequestMappingList(Annotation[] annotations) {
        List<RequestMapping> requestMappings = new ArrayList<>();
        for (Annotation annotation : annotations) {
            if (annotation instanceof RequestMapping){
                requestMappings.add((RequestMapping) annotation);
            }
        }
        return requestMappings;
    }
}
