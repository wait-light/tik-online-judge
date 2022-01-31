package top.adxd.tikonlinejudge.executor.service.docker.judge;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;
import top.adxd.tikonlinejudge.executor.single.Language;

/**
 * @author light
 */
public class DockerJavaCodeJudge extends AbstractDockerJudgeTemplate<JavaDockerConfig> {

    public DockerJavaCodeJudge(JavaDockerConfig dockerJavaCodeJudge) {
        super(dockerJavaCodeJudge);
    }

    @Override
    public Language getLanguage() {
        return Language.JAVA;
    }
}
