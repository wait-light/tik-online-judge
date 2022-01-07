package top.adxd.tikonlinejudge.executor.service.docker.env;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class DockerEnvService {
    private static final Logger logger = LoggerFactory.getLogger(DockerEnvService.class);
    private static final String TAG_LATEST = "latest";
    private static final Long MAX_WAIT_TIME = 10L;
    private static final TimeUnit WAIT_TIME_UNIT = TimeUnit.MINUTES;
    private DockerEnvService() {
    }

    @Autowired
    private DockerClient dockerClient;

    /**
     * 根据tar包通过api创建image镜像
     *
     * @param tar tar包文件
     */
    public void build(File tar, IDockerJudgeConfig dockerJudgeConfig) {
        Assert.notNull(tar, "tar包不能为空");
        Assert.notNull(dockerJudgeConfig, "配置信息不能为空");
        Set<String> set = new HashSet<>();
        set.add(dockerJudgeConfig.getImageName());
        String imageId = dockerClient.buildImageCmd(tar).withTags(set).start().awaitImageId(MAX_WAIT_TIME, WAIT_TIME_UNIT);
        logger.info("构建镜像成功：" + imageId);
    }

    public void build(IDockerJudgeConfig dockerJudgeConfig) {
        build(dockerJudgeConfig.getDockerfileDir(), dockerJudgeConfig);
    }

    /**
     * 根据tar包通过api创建image镜像
     *
     * @param tarPath tar包路径
     */
    public void build(String tarPath, IDockerJudgeConfig dockerJudgeConfig) {
        File tar = new File(tarPath);
        build(tar, dockerJudgeConfig);
    }

    /**
     * 是否存在某个Image
     *
     * @param imageName image名称
     * @param tag       标签，例如：latest ， v1.1
     * @return
     */
    public boolean hasImage(String imageName, String tag) {
        Assert.notNull(imageName, "镜像名不能为空");
        if (tag == null) {
            tag = TAG_LATEST;
        }
        String mergeName = imageName + ":" + tag;
        try {
            List<Image> exec = dockerClient.listImagesCmd().exec();
            return exec.stream().anyMatch(image -> {
                String[] repoTags = image.getRepoTags();
                for (String name : repoTags) {
                    if (mergeName.equalsIgnoreCase(name)) {
                        return true;
                    }
                }
                return false;
            });
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }
}
