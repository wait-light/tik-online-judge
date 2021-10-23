package top.adxd.tikonlinejudge.executor.service.docker.judge;

import top.adxd.tikonlinejudge.executor.config.docker.impl.CppDockerConfig;

/**
 * @author wait-light
 * @date 2021/10/23 下午7:33
 */
public class DockerCppCodeJudge extends AbstractDockerJudgeTemplate<CppDockerConfig>{
    public DockerCppCodeJudge(CppDockerConfig dockerJavaCodeJudge) {
        super(dockerJavaCodeJudge);
    }
}
