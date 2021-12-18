package top.adxd.tikonlinejudge.common.util;

import cn.hutool.core.util.ClassUtil;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TikAnnotationUtil {
    public static List<Annotation> getClassMethodAnnotations(Class cla,Class targetAnnotation){
        Method[] methods = cla.getMethods();
        Method[] declaredMethods = cla.getDeclaredMethods();
        for (Method method:declaredMethods){
            System.out.println(method);
        }
        System.out.println("0000000000000000000");
        for (Method m : methods){
            System.out.println(m);
        }
//        System.out.println(Arrays.toString());
        return null;
    }
}
