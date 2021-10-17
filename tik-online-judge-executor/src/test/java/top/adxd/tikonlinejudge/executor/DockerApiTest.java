package top.adxd.tikonlinejudge.executor;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@SpringBootTest
public class DockerApiTest {
    @Autowired
    private JavaDockerConfig javaDockerConfig;
//    @BeforeAll
//    public void beforeAll(){
//        System.out.printf("--------------------");
//    }
//    @AfterAll
//    public void  afterAll(){
//        System.out.printf("-----------");
//    }
    @Autowired
    private DockerClient dockerClient;
    private static final String TRIM_END_REGEX = "[\\s]*$";
    private String trimEndsS(String src) {
        return src.replaceFirst(TRIM_END_REGEX, "");
    }
    @Test
    public void trimTest(){
        String str = "\r\nasdaasdasd\r\n";
        String s = trimEndsS(str);
        System.out.println(s);
    }
    @Test
    public void jjj(){
//        DockerClientConfig dockerClientConfig = dockerConfig.dockerClientConfig();
//        List<JudgeResult> judge = dockerJavaCodeJudge.judge(submit);
//        Volume volume = new Volume();
//        InspectContainerResponse exec = dockerClient.inspectContainerCmd("judge-java").exec();
//        VolumeBind[] volumes = exec.getVolumes();
//        System.out.println(Arrays.toString(volumes));
//        Volume volume = new Volume(javaDockerConfig.getPath()+"/:/usr/src/judge/");
//        HostConfig hostConfig = new HostConfig();
//        Volume inner = new Volume("/usr/src/judge/");
//        Bind bind = new Bind(javaDockerConfig.getPath(), inner);
//        hostConfig.setBinds(bind);
//        try {
//            CreateContainerResponse exec = dockerClient.createContainerCmd("judge-java")
//                    .withHostConfig(hostConfig)
//                    .withName("judge-java")
//                    .exec();
//        }catch (Exception ep){
//            System.out.println(ep.getLocalizedMessage());
//        }
        Instant parse = Instant.parse("2021-10-07T13:06:58.089735728Z");
        Instant parse2 = Instant.parse("2021-10-07T13:06:58.502332363Z");
        int nano = Duration.between(parse, parse2).getNano();
        long seconds = Duration.between(parse, parse2).getSeconds();
        System.out.println(seconds);
        System.out.println(nano);

    }
    @Test
    public void realTest() {
        System.out.println("开始");
        DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory().withReadTimeout(10000)
                .withConnectTimeout(10000).withMaxTotalConnections(100).withMaxPerRouteConnections(10);
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(true)
                .withDockerCertPath("D:\\study\\docker\\certs")
                .withDockerHost("tcp://192.168.181.128:2376")
                .withDockerConfig("D:\\study\\docker\\certs")
                .withApiVersion("1.41")
                .withRegistryUrl("https://index.docker.io/v1/")
                .withRegistryUsername("username")
                .withRegistryPassword("password")
                .withRegistryEmail("email").build();
//根据自己的情况，按需填写

        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(dockerCmdExecFactory).build();


        CreateContainerResponse container1 = dockerClient.createContainerCmd("jaja:latest").exec();
        dockerClient.startContainerCmd(container1.getId()).exec();
        System.out.println("结束");
    }

    @Test
    public void aaa() {
        System.out.println("开始测试");
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://192.168.181.128:2376")
                .withDockerTlsVerify(true)
                .withDockerCertPath("D:\\study\\docker\\certs")
                .withRegistryUsername("admin")
                .withRegistryPassword("admin")
                .withRegistryEmail("admin")
                .withRegistryUrl("admin")
                .build();


        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

//        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
//        LogContainerCmd myjava = dockerClient.logContainerCmd("myjava");


//
//        StartContainerCmd startContainerCmd = dockerClient.startContainerCmd("myjava");
//        startContainerCmd.exec();
//        System.out.println(startContainerCmd.getContainerId());

        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.GET)
                .path("/containers/myjava/json")
                .build();
//
        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            System.out.println("_________________________");
            System.out.println(IOUtils.toString(response.getBody()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//                .withDockerCertPath("/home/user/.docker")
//                .withRegistryUsername(registryUser)
//                .withRegistryPassword(registryPass)
//                .withRegistryEmail(registryMail)
//                .withRegistryUrl(registryUrl)

    }
}
