package top.adxd.tikonlinejudge.executor.constant;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * 语言类型常量
 */
public class LanguageConstant {
    public static final int JAVA_LANGUAGE = 1;
    public static final int C_LANGUAGE = 2;
    public static final int CPP_LANGUAGE = 3;
    public static final int PYTHON_LANGUAGE = 4;
    private static final Set<Integer> languageSet;

    /**
     * 将属性名以 _LANGUAGE结尾，且类型为Integer的属性值加入set中，用于判定是否可用的语言类型
     */
    static {
        languageSet = new HashSet<>();
        Class languageConstantClass = LanguageConstant.class;
        Field[] declaredFields = languageConstantClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().endsWith("_LANGUAGE")) {
                try {
                    Object value = field.get(LanguageConstant.class);
                    if (value instanceof Integer) {
                        languageSet.add((int) value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断是否已经实现的语言类型
     *
     * @param type
     * @return
     */
    public static boolean isDefinedLanguage(int type) {
        return languageSet.contains(type);
    }
}
