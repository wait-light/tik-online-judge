package top.adxd.tikonlinejudge.executor.config.docker.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.executor.config.docker.ICompileAbleConfig;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;

import java.io.File;

/**
 * @author wait-light
 * @date 2021/10/23 下午6:54
 */
@Configuration
@ConfigurationProperties("tik-online-judge.executor.docker-c")
public class CDockerConfig implements ICompileAbleConfig, IDockerJudgeConfig, InitializingBean {
    /**
     * 以下文件可配置
     * 源码文件地址
     */
    private String path;
    private String containerName;
    private String imageName;
    private String workDir;

    /**
     * 以下文件不建议配置
     */
    private String stdout;
    private String stderr;
    private String input;
    private String sourcePath;
    private String compileTime;
    private String compileInfo;
    private String needCompile;
    private volatile String dockerfileDir;
    private String runtimeInfo;

    public CDockerConfig() {
    }

    public CDockerConfig(String path, String containerName, String imageName, String workDir) {
        this.path = path;
        this.containerName = containerName;
        this.imageName = imageName;
        this.workDir = workDir;
    }

    @Override
    public IDockerJudgeConfig newConfig(String path, String containerName) {
        this.path = path;
        this.containerName = containerName;
        this.stderr = null;
        this.stdout = null;
        this.input = null;
        this.sourcePath = null;
        this.compileTime = null;
        this.compileInfo = null;
        this.needCompile = null;
        return this;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getImageName() {
        if (imageName == null || "".equals(imageName.trim())) {
            imageName = "judge-c";
        }
        return imageName;
    }


    @Override
    public String getWorkDir() {
        if (workDir == null || "".equals(workDir.trim())) {
            workDir = "/home/tik-online-judge";
        }
        return workDir;
    }

    @Override
    public String getStdout() {
        if (stdout == null || "".equals(stdout.trim())) {
            stdout = path + STDOUT;
        }
        return stdout;
    }

    @Override
    public String getStderr() {
        if (stderr == null || "".equals(stderr.trim())) {
            stderr = path + STDERR;
        }
        return stderr;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String getSourcePath() {
        if (sourcePath == null) {
            sourcePath = path + "/Main.c";
        }
        return sourcePath;
    }

    @Override
    public String getDockerfileDir() {
        if (dockerfileDir == null) {
            synchronized (this) {
                dockerfileDir = getClass().getClassLoader().getResource("").getPath()
                        + "docker-image"
                        + File.separator
                        + "cdocker";
            }
        }
        return dockerfileDir;
    }

    @Override
    public String getRuntimeAndRuntimeMemory() {
        if(runtimeInfo == null){
            runtimeInfo = path + "/time_memory";
        }
        return runtimeInfo;
    }


    @Override
    public String getCompileTime() {
        if (compileTime == null) {
            compileTime = path + ICompileAbleConfig.COMPILE_TIME;
        }
        return compileTime;
    }

    @Override
    public String getCompileInfo() {
        if (compileInfo == null) {
            compileInfo = path + ICompileAbleConfig.COMPILE_INFO;
        }
        return compileInfo;
    }

    @Override
    public String needCompile() {
        if (needCompile == null) {
            needCompile = path + ICompileAbleConfig.NEED_COMPILE;
        }
        return needCompile;
    }


    @Override
    public String getInput() {
        if (input == null) {
            input = path + IDockerJudgeConfig.INPUT;
        }
        return input;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //处理路径背后的 /
        if (path == null || "".equals(path.trim())) {
            return;
        }
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }
    }

    @Override
    public String getContainerName() {
        if (containerName == null || "".equals(containerName.trim())) {
            containerName = "judge-c";
        }
        return containerName;
    }
}
