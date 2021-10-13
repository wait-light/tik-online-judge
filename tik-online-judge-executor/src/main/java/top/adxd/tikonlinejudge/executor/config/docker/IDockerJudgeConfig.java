package top.adxd.tikonlinejudge.executor.config.docker;

/**
 * 用于标记是否docker 相关语言的配置信息
 * 可以根据此配置创建容器
 *
 */
public interface IDockerJudgeConfig {
    String INPUT = "/input";
    String STDOUT = "/stdout";
    String STDERR = "/stderr";
    String getContainerName();
    String getPath();
    String getImageName();
    String getWorkDir();
    String getStdout();
    String getStderr();
    String getInput();
    String getSourcePath();
}
