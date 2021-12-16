package top.adxd.tikonlinejudge.executor.service.docker.env;

import cn.hutool.core.util.StrUtil;
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

@Service
public class DockerEnvService {
    private static final Logger logger = LoggerFactory.getLogger(DockerEnvService.class);
    private static final String TAG_LATEST = "latest";

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
//        Assert.notNull(tar, "tar包不能为空");
//        Assert.notNull(dockerJudgeConfig, "配置信息不能为空");
        Set<String> set = new HashSet<>();
        set.add(dockerJudgeConfig.getImageName());
        String s = dockerClient.buildImageCmd(tar).withTags(set).start().awaitImageId();
        System.out.printf(s + "-----------------------");
    }

    /**
     * 根据tar包通过api创建image镜像
     *
     * @param tarPath tar包路径
     */
    public void build(String tarPath) {
        File tar = new File(tarPath);
//        build(tar);
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
            tag = "latest";
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
