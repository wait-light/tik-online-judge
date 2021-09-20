package top.adxd.tikonlinejudge.executor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.exception.runtime.ClassFullPathIllegalException;
import top.adxd.tikonlinejudge.executor.exception.runtime.CodePathIllegalException;

@Component
@ConfigurationProperties("tik-online-judge.executor.cpp")
public class CppCodeExecuteConfig implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(CppCodeExecuteConfig.class);
    /**
     * 文件全路径，保持文件名，且只能以 .cpp 结尾
     * 例如 D:/main.cpp
     */
    private String codeFullPath;
    /**
     * 代码文件名
     * 例如 codeFullPath = D:/main.cpp
     * 则 codeFileName = main.cpp
     */
    private String codeFileName;
    /**
     * 是否开启 cpp的编译
     */
    private Boolean open = false;
    /**
     * 被执行的文件的全路径
     * 例如 codeFullPath = D:/main.cpp
     * 则 targetProcessPath = D:/a.exe
     */
    private String targetProcessPath;
    /**
     * 代码所在目录
     * 例如 classFullPath = D:/main.cpp
     * 则 targetPath = D:/
     */
    private String targetPath;
    /**
     * 编译器路径
     */
    private String gppPath;

    public String getCodeFileName() {
        return codeFileName;
    }

    public String getGppPath() {
        return gppPath;
    }

    public void setGppPath(String gppPath) {
        this.gppPath = gppPath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getTargetProcessPath() {
        return targetProcessPath;
    }

    public void setCodeFullPath(String codeFullPath) {
        this.codeFullPath = codeFullPath;
    }

    public String getCodeFullPath() {
        return codeFullPath;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!open) {
            return;
        }
        if (null == codeFullPath || "".equals(codeFullPath.trim()) || !codeFullPath.endsWith(".cpp")) {
            log.error("tik-online-judge.executor.cpp.codeFullPath is illegal! It's need end with .cpp");
            throw new CodePathIllegalException("tik-online-judge.executor.cpp.codeFullPath is illegal! It's need end with .cpp");
        }
        if (null == gppPath || "".equals(gppPath.trim()) || !gppPath.endsWith("g++")) {
            log.error("tik-online-judge.executor.cpp.gppPath is illegal! It's need end with g++");
            throw new CodePathIllegalException("tik-online-judge.executor.cpp.gppPath is illegal! It's need end with g++");
        }
        String[] split = codeFullPath.split("\\\\");
        int withoutFileNameIndex = codeFullPath.lastIndexOf(split[split.length - 1]);
        targetPath = codeFullPath.substring(0, withoutFileNameIndex);
        targetProcessPath = targetPath + "a.exe";
        codeFileName = split[split.length - 1];
    }
}
