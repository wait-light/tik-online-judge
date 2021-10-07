package top.adxd.tikonlinejudge.executor.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.DockerConfig;
import top.adxd.tikonlinejudge.executor.config.docker.impl.JavaDockerConfig;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ICodeJudge;
import top.adxd.tikonlinejudge.executor.service.IFileReaderWriter;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
@Service("dockerJavaCodeJudge")
public class DockerJavaCodeJudge extends AbstractDockerJudgeTemplate<JavaDockerConfig> {

}
