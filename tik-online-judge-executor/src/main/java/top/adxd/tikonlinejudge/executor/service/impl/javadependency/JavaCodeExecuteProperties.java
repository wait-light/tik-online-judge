package top.adxd.tikonlinejudge.executor.service.impl.javadependency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.exception.runtime.ClassFullPathIllegalException;

@Component
public class JavaCodeExecuteProperties implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(JavaCodeExecuteProperties.class);
    /**
     * 要编译的类的全路径，包括类名
     */
    @Value("${tik-online-judge.executor.java.classFullPath}")
    public String classFullPath;
    private String classFullNamePath;

    public String getClassFullNamePath() {
        return classFullNamePath;
    }

    public String getClassFullPath() {
        return classFullPath;
    }

    public void setClassFullPath(String classFullPath) {
        this.classFullPath = classFullPath;
    }

    /**
     * 检出传入的类全路径是否符合规则
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == classFullPath || "".equals(classFullPath) || !classFullPath.endsWith(".java")) {
            log.error("tik-online-judge.executor.java.classFullPath is illegal! It's end with .java");
            throw new ClassFullPathIllegalException("tik-online-judge.executor.java.classFullPath is illegal! It's end with .java");
        }
        int index = classFullPath.lastIndexOf(".java");
        classFullNamePath = classFullPath.substring(0, index);
    }
}
