package top.adxd.tikonlinejudge.executor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.exception.runtime.ClassFullPathIllegalException;

import java.util.Arrays;

@Component
@ConfigurationProperties("tik-online-judge.executor.java")
public class JavaCodeExecuteConfig implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(JavaCodeExecuteConfig.class);
    /**
     * 要编译的类的全路径，包括类名
     */
    public String classFullPath;
    /**
     * 除了文件类型的全路径
     * 例如 classFullPath = D:/Main.java
     * 则 classFullNamePath = D:/Main
     */
    private String classFullNamePath;
    /**
     * 类名
     */
    private String className;
    /**
     * 是否开启java代码执行器
     */
    private Boolean open;
    /**
     * 代码所在目录
     * 例如 classFullPath = D:/Main.java
     * 则 targetPath = D:/
     */
    private String targetPath;

    public String getTargetPath() {
        return targetPath;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getClassFullNamePath() {
        return classFullNamePath;
    }

    public String getClassFullPath() {
        return classFullPath;
    }

    public void setClassFullPath(String classFullPath) {
        this.classFullPath = classFullPath;
    }

    public String getClassName() {
        return className;
    }

    /**
     * 检出传入的类全路径是否符合规则
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (!open) {
            return;
        }
        if (null == classFullPath || "".equals(classFullPath) || !classFullPath.endsWith(".java")) {
            log.error("tik-online-judge.executor.java.classFullPath is illegal! It's end with .java");
            throw new ClassFullPathIllegalException("tik-online-judge.executor.java.classFullPath is illegal! It's end with .java");
        }
        int index = classFullPath.lastIndexOf(".java");
        classFullNamePath = classFullPath.substring(0, index);
        String[] split = classFullNamePath.split("\\\\");
        int withoutFileNameIndex = classFullNamePath.lastIndexOf(split[split.length - 1]);
        className = split[split.length - 1];
        targetPath = classFullPath.substring(0,withoutFileNameIndex);
    }
}
