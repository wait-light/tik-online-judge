package top.adxd.tikonlinejudge.common.annotation.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import top.adxd.tikonlinejudge.common.util.TikAnnotationUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoleCommonProcess {
    @Autowired
    private MappingProcess mappingProcess;
    private Map<Class, List<MappingProcess.MethodMapping>> methodMappingCacheMap = new ConcurrentHashMap<>();

    /**
     * 处理单个注解或者RoleConfig配置
     *
     * @param targetClasses 目标
     * @param exclude       排除路径
     * @return 单个类或注解最终生成的路径信息
     */
    public List<MappingProcess.MethodMapping> process(Class[] targetClasses, String[] exclude) {
        List<MappingProcess.MethodMapping> roleMethodMapping = new ArrayList<>();
        for (Class target : targetClasses) {
            List<MappingProcess.MethodMapping> methodMappings = methodMappingCacheMap.get(target);
            if (methodMappings == null) {
                RequestMapping prefix = AnnotationUtils.getAnnotation(target, RequestMapping.class);
                List<RequestMapping> classMethodAnnotations = TikAnnotationUtil.getClassMethodAnnotations(target, RequestMapping.class);
                methodMappings = mappingProcess.mergeRequestMapping(prefix, classMethodAnnotations);
                methodMappingCacheMap.put(target, methodMappings);
            }
            roleMethodMapping.addAll(methodMappings);
        }
        mappingProcess.excludeSpecific(exclude, roleMethodMapping);
        for (MappingProcess.MethodMapping mapping : roleMethodMapping) {
            System.out.println(mapping);
        }
        return roleMethodMapping;
    }

    public List<MappingProcess.MethodMapping> process(List<Class> targetClasses, List<String> exclude) {
        if (targetClasses == null) {
            targetClasses = new ArrayList<>();
        }
        if (exclude == null) {
            exclude = new ArrayList<>();
        }
        return process(targetClasses.toArray(new Class[targetClasses.size()]), exclude.toArray(new String[exclude.size()]));
    }

}
