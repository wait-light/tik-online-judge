package top.adxd.tikonlinejudge.executor.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * docker信息配置
 */
@Configuration
public class DockerConfig {

    @Bean("dockerClientConfig")
    public DockerClientConfig dockerClientConfig(){
        DefaultDockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        return dockerClientConfig;
    }
    @Bean("dockerHttpClient")
    public DockerHttpClient dockerHttpClient(@Autowired DockerClientConfig dockerClientConfig){
        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        return dockerHttpClient;
    }

    @Bean("dockerClient")
    public DockerClient dockerClient(@Autowired DockerClientConfig dockerClientConfig,
                                     @Autowired DockerHttpClient dockerHttpClient){
        DockerClient dockerClient = DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
        return dockerClient;
    }
}
