package top.adxd.tikonlinejudge.executor.service.docker.judge;

import top.adxd.tikonlinejudge.executor.config.docker.impl.CDockerConfig;

/**
 * @author wait-light
 * @date 2021/10/23 下午7:33
 */
public class DockerCCodeJudge extends AbstractDockerJudgeTemplate<CDockerConfig>{
    public DockerCCodeJudge(CDockerConfig dockerConfig) {
        super(dockerConfig);
    }
}
