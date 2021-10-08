package top.adxd.tikonlinejudge.executor.config.docker.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.executor.config.docker.ICompileAbleConfig;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;

@Configuration
@ConfigurationProperties("tik-online-judge.executor.docker-java")
public class JavaDockerConfig implements ICompileAbleConfig, IDockerJudgeConfig,InitializingBean {
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
    private String output;
    private String input;
    private String sourcePath;
    private String compileTime;
    private String compileInfo;
    private String needCompile;


    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getImageName() {
        if (imageName == null || "".equals(imageName.trim())){
            imageName = "judge-java";
        }
        return imageName;
    }


    @Override
    public String getWorkDir() {
        if (workDir == null || "".equals(workDir.trim())){
            workDir = "/usr/src/judge";
        }
        return workDir;
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
    public String getSourcePath(){
        if (sourcePath == null){
            sourcePath = path + "/Main.java";
        }
        return sourcePath;
    }


    @Override
    public String getCompileTime() {
        if (compileTime == null){
            compileTime = path + ICompileAbleConfig.COMPILE_TIME;
        }
        return compileTime;
    }

    @Override
    public String getCompileInfo() {
        if (compileInfo == null){
            compileInfo = path + ICompileAbleConfig.COMPILE_INFO;
        }
        return compileInfo;
    }

    @Override
    public String needCompile() {
        if (needCompile == null){
            needCompile = path + ICompileAbleConfig.NEED_COMPILE;
        }
        return needCompile;
    }

    @Override
    public String getOutput() {
        if (output == null){
            output = path + IDockerJudgeConfig.OUTPUT;
        }
        return output;
    }

    @Override
    public String getInput() {
        if (input == null){
            input = path + IDockerJudgeConfig.INPUT;
        }
        return input;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //处理路径背后的 /
        if (path == null || "".equals(path.trim())){
            return;
        }
        if (path.endsWith("/") || path.endsWith("\\")){
            path = path.substring(0,path.length()-1);
        }
    }

    @Override
    public String getContainerName() {
        if (containerName == null || "".equals(containerName.trim())){
            containerName = "judge-java";
        }
        return containerName;
    }
}
