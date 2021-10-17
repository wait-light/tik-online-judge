package top.adxd.tikonlinejudge.executor.service.impl;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;

/**
 * @author light
 */
public class DockerJavaCodeJudge extends AbstractDockerJudgeTemplate<JavaDockerConfig> {

    public DockerJavaCodeJudge(JavaDockerConfig dockerJavaCodeJudge) {
        super(dockerJavaCodeJudge);
    }
}
