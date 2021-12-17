package top.adxd.tikonlinejudge.executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;
import top.adxd.tikonlinejudge.executor.service.docker.env.DockerEnvService;

import java.io.File;

@SpringBootTest
public class BuildServiceTest {
    @Autowired
    private DockerEnvService dockerEnvService;
    @Autowired
    private IDockerJudgeConfig javaDockerConfig;

    @Test
    public void buildTest() {
        File file = new File("/home/wait-light/code/tik-online-judge/tik-online-judge-executor/src/main/resources/docker-image/javadocker");
        dockerEnvService.build(file, javaDockerConfig);
    }

    @Test
    public void getTar() {
//        String path = DockerEnvService.class.getClassLoader().getResource("").getPath();
//        System.out.printf(path);
    }
}
