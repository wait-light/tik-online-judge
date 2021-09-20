package top.adxd.tikonlinejudge.executor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.exception.runtime.CodePathIllegalException;

@Component
@ConfigurationProperties("tik-online-judge.executor.clanguage")
public class CLanguageCodeExecuteConfig implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(CLanguageCodeExecuteConfig.class);
    /**
     * 文件全路径，保持文件名，且只能以 .c 结尾
     * 例如 D:/main.c
     */
    private String codeFullPath;
    /**
     * 是否开启 C语言编译执行
     */
    private Boolean open = false;
    /**
     * 被执行的文件的全路径
     * 例如 codeFullPath = D:/main.c
     * 则 targetProcessPath = D:/a.exe
     */

    private String targetProcessPath;
    /**
     * 代码所在目录
     * 例如 classFullPath = D:/main.c
     * 则 targetPath = D:/
     */
    private String targetPath;

    /**
     * 代码文件名
     * 例如 codeFullPath = D:/main.c
     * 则 codeFileName = main.c
     */
    private String codeFileName;

    public String getCodeFileName() {
        return codeFileName;
    }

    public String getTargetPath() {
        return targetPath;
    }

    /**
     * 编译器路径
     */
    private String gccPath;

    public String getGccPath() {
        return gccPath;
    }


    public void setGccPath(String gccPath) {
        this.gccPath = gccPath;
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
        if (null == codeFullPath || "".equals(codeFullPath.trim()) || !codeFullPath.endsWith(".c")) {
            log.error("tik-online-judge.executor.CLanguage.codeFullPath is illegal! It's need end with .c");
            throw new CodePathIllegalException("tik-online-judge.executor.CLanguage.codeFullPath is illegal! It's need end with .c");
        }
        if (null == gccPath || "".equals(gccPath.trim()) || !gccPath.endsWith("gcc")){
            log.error("tik-online-judge.executor.CLanguage.gccPath is illegal! It's need end with gcc");
            throw new CodePathIllegalException("tik-online-judge.executor.CLanguage.gccPath is illegal! It's need end with gcc");
        }
        String[] split = codeFullPath.split("\\\\");
        int withoutFileNameIndex = codeFullPath.lastIndexOf(split[split.length - 1]);
        targetPath = codeFullPath.substring(0, withoutFileNameIndex);
        targetProcessPath = targetPath + "a.exe";
        codeFileName = split[split.length-1];
    }
}
