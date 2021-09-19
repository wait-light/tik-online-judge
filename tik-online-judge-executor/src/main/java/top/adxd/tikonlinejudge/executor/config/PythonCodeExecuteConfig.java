package top.adxd.tikonlinejudge.executor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.exception.runtime.CodePathIllegalException;

@Component
@ConfigurationProperties("tik-online-judge.executor.python")
public class PythonCodeExecuteConfig implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(PythonCodeExecuteConfig.class);
    private String codeFullPath;
    private Boolean open = false;
    //除了具体文件类型的路径
    private String targetPath;
    public String getTargetPath() {
        return targetPath;
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
        if (null == codeFullPath || "".equals(codeFullPath.trim()) || !codeFullPath.endsWith(".py")) {
            log.error("tik-online-judge.executor.python.codeFullPath is illegal! It's need end with .py");
            throw new CodePathIllegalException("tik-online-judge.executor.python.codeFullPath is illegal! It's need end with .py");
        }
        int lastInclinedRodIndex = codeFullPath.lastIndexOf("\\");
        targetPath = codeFullPath.substring(0,lastInclinedRodIndex);

    }
}
