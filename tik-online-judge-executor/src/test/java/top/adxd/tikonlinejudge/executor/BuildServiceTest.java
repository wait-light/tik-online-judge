package top.adxd.tikonlinejudge.executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;
import top.adxd.tikonlinejudge.executor.service.docker.env.DockerEnvService;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
public class BuildServiceTest {
    @Autowired
    private DockerEnvService dockerEnvService;
    @Autowired
    private IDockerJudgeConfig javaDockerConfig;

//    @Test
    public void dosPath2unixPath(){
        Pattern compile = Pattern.compile("^[a-zA-Z]");
        Matcher matcher = compile.matcher("a:\\\\asdasdasd");
        String s = matcher
                .replaceFirst("a:\\\\asdasdasd".charAt(0) + "")
                .replaceAll("\\\\", "/");

        System.out.println(s);
    }
//    @Test
    public void windowsPathTest(){
        boolean matches = "a:\\\\\\".matches("^[a-zA-Z]:\\\\.*");
        System.out.println(matches);
    }

//    @Test
    public void antPathtest(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/executor/problem", "/executor/problem/100");
        System.out.println(match);
    }

//    @Test
    public void buildTest() {

        File file = new File("/home/wait-light/code/tik-online-judge/tik-online-judge-executor/src/main/resources/docker-image/javadocker");
        dockerEnvService.build(file, javaDockerConfig);
    }

//    @Test
    public void getTar() {
//        String path = DockerEnvService.class.getClassLoader().getResource("").getPath();
//        System.out.printf(path);
    }
}
