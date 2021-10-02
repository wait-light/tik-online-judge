package top.adxd.tikonlinejudge.executor;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.async.ResultCallbackTemplate;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;

public class DockerApiTest {
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
