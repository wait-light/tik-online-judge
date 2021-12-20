package top.adxd.tikonlinejudge.common.util;

import org.springframework.core.annotation.AnnotatedElementUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TikAnnotationUtil {
    /**
     * 获取当前类的方法上的合并后的注解
     *
     * @param cla              方法
     * @param targetAnnotation 目标注解
     * @return 注解列表
     */
    public static <T> List<T> getClassMethodAnnotations(Class cla, Class targetAnnotation) {
        Method[] declaredMethods = cla.getDeclaredMethods();
        List<T> annotations = new ArrayList<>();
        for (Method method : declaredMethods) {
            T mergedAnnotation = (T) AnnotatedElementUtils.findMergedAnnotation(method, targetAnnotation);
            if (mergedAnnotation != null) {
                annotations.add(mergedAnnotation);
            }
        }
        return annotations;
    }
}
