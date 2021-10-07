package top.adxd.tikonlinejudge.executor.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.WaitContainerResultCallback;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Volume;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.executor.config.DockerConfig;
import top.adxd.tikonlinejudge.executor.config.docker.ICompileAbleConfig;
import top.adxd.tikonlinejudge.executor.config.docker.IDockerJudgeConfig;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ICodeJudge;
import top.adxd.tikonlinejudge.executor.service.IFileReaderWriter;
import top.adxd.tikonlinejudge.executor.service.IProblemDataService;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.vo.JudgeStatus;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用Docker进行编译、运行的模板类
 */
@Slf4j
public abstract class AbstractDockerJudgeTemplate<T extends IDockerJudgeConfig> implements ICodeJudge {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDockerJudgeTemplate.class);
    private static final String TRIM_END_REGEX = "[\\s]*$";
    //防止直接与默认Long最小值操作的时与所预期的想法不同。（越界）
    private static final Long MIN_TIME = Long.MIN_VALUE /2;
    @Autowired
    protected T dockerJudgeConfig;
    @Autowired
    protected IFileReaderWriter fileReaderWriter;
    @Autowired
    protected DockerConfig dockerConfig;
    @Autowired
    protected DockerClient dockerClient;
    @Autowired
    protected IProblemDataService problemDataService;
    @Autowired
    protected ISubmitService submitService;

    @Override
    public List<JudgeResult> judge(Submit submit) {
        writeSource(submit,true);
        List<ProblemData> problemDataList = problemDataService.getProblemDataList(submit);
        //保证不报空指针异常
        if (problemDataList.size() == 0){
            ProblemData problemData = new ProblemData();
            problemData.setInput("");
            problemDataList.add(problemData);
        }
        List<JudgeResult> results = new ArrayList<>();
        for (ProblemData problemData : problemDataList){
            writeInputOutput(problemData);
            JudgeResult judge = judge(problemData,submit.getId());
            results.add(judge);
            //编译错误，后续不在运行
            if (judge.getJudgeStatus() == JudgeStatus.COMPILE_ERROR){
                return results;
            }
        }
        return results;
    }

    /**
     *
     * @param problemData 问题数据项
     */
    protected JudgeResult judge(ProblemData problemData,Long submitId){
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setSubmitId(submitId);
        //启动程序
        startContainer();
        //等待程序运行
        Integer exitCode = waitProcess(1500L);
        //非正常退出
        if (exitCode != 0){
            String compileErrorMessage = getCompileErrorMessage();
            //编译错误
            if (!"".equals(compileErrorMessage.trim())){
                judgeResult.setJudgeStatus(JudgeStatus.COMPILE_ERROR);
                judgeResult.setErrorOutput(compileErrorMessage);
                judgeResult.setSuccess(false);
            }else {
                String output = getOutput();
                judgeResult.setErrorOutput(output);
                judgeResult.setJudgeStatus(JudgeStatus.RUNTIME_ERROR);
                judgeResult.setSuccess(false);
            }
            return judgeResult;
        }

        Long executeTime = getExecuteTime();
        judgeResult.setExecutionTime(executeTime);
        //TODO 时间限制
        if (executeTime > 1500L){
            judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        String output = getOutput();
        if (output.equals(problemData.getOutput())) {
            judgeResult.setSuccess(true);
            judgeResult.setJudgeStatus(JudgeStatus.ACCEPT);
            return judgeResult;
        }
        judgeResult.setSuccess(false);
        //判断是否格式错误
        //策略：判断是否因为尾部的空格等不可见的符号导致错误
        if (trimEnds(output).equals(problemData.getOutput()) || trimEnds(problemData.getOutput()).equals(output)){
            judgeResult.setJudgeStatus(JudgeStatus.PRESENTATION_ERROR);
        }else {
            judgeResult.setJudgeStatus(JudgeStatus.WRONG_ANSWER);
        }
        return judgeResult;
    }

    private String trimEnds(String src) {
        return src.replaceFirst(TRIM_END_REGEX, "");
    }

    /**
     * 等待程序执行完成
     * @param time 等待时间，通常为程序允许运行的最大时间
     * @return
     */
    protected Integer waitProcess(Long time){
        WaitContainerResultCallback start = dockerClient
                .waitContainerCmd(dockerJudgeConfig.getContainerName())
                .start();
        return start.awaitStatusCode(time, TimeUnit.MILLISECONDS);
    }

    protected String startContainer(){
        return dockerClient
                .startContainerCmd(dockerJudgeConfig.getContainerName())
                .getContainerId();
    }
    protected String getCompileErrorMessage(){
        if (dockerJudgeConfig instanceof ICompileAbleConfig){
            String compileInfoPath = ((ICompileAbleConfig) dockerJudgeConfig).getCompileInfo();
            return fileReaderWriter.Reader(compileInfoPath);
        }
        return "";
    }
    /**
     * 根据配置信息 尝试创建容器
     */
    protected void createContainer(){
        HostConfig hostConfig = new HostConfig();
        Volume inner = new Volume(dockerJudgeConfig.getWorkDir());
        Bind bind = new Bind(dockerJudgeConfig.getPath(),inner);
        hostConfig.setBinds(bind);
        CreateContainerResponse exec = null;
        boolean success = true;
        try {
            exec = dockerClient
                    .createContainerCmd(dockerJudgeConfig.getImageName())
                    .withName(dockerJudgeConfig.getContainerName())
                    .withHostConfig(hostConfig)
                    .exec();
        }catch (Exception e){
            success = false;
            logger.error(e.getLocalizedMessage());
        }
        if (success && null != exec ){
            log.trace("成功创建容器：" + exec.getId());
        }
    }

    /**
     * 获取文件中程序执行的输出或者错误
     * @return
     */
    protected String getOutput(){
        return fileReaderWriter.Reader(dockerJudgeConfig.getOutput());
    }
    /**
     * @return 返回可编译语言的编译时间
     */
    protected Long getCompileTime(){
        if (!(dockerJudgeConfig instanceof ICompileAbleConfig)){
            return 0L;
        }
        String compileTimePath = ((ICompileAbleConfig) dockerJudgeConfig).getCompileTime();
        String compileTime = fileReaderWriter.Reader(compileTimePath);
        try {
            return Long.parseLong(compileTime);
        }catch (Exception e){
            return MIN_TIME ;
        }
    }

    /**
     * 获取程序执行时间
     * 计算公式：程序执行时间 = 容器退出时间 - 容器开始时间 - 编译时间 - 10ms;
     * 其中10ms为容器的开销
     * @return
     */
    protected Long getExecuteTime(){
        Long compileTime = getCompileTime();
        InspectContainerResponse.ContainerState state = dockerClient
                .inspectContainerCmd(dockerJudgeConfig.getContainerName())
                .exec()
                .getState();
        String startedAt = state.getStartedAt();
        String finishedAt = state.getFinishedAt();
        Instant startInstant = Instant.parse(startedAt);
        Instant finishedInstant = Instant.parse(finishedAt);
        Duration between = Duration.between(startInstant, finishedInstant);
        Long executeTime = -compileTime + between.getSeconds() + between.getNano() / 1000000 - 100000;
        if (executeTime < 0)
        {
            executeTime = 0L;
        }
        return executeTime;
    }
    /**
     * 写入输入、输出
     */
    protected void writeInputOutput(ProblemData problemData){
        if (problemData == null){
            return;
        }
        String outputPath = dockerJudgeConfig.getOutput();
        String inputPath = dockerJudgeConfig.getInput();
        fileReaderWriter.writer(inputPath,problemData.getInput(),false);
        //输出文件置空
        fileReaderWriter.writer(outputPath,"",false);
    }
    /**
     * 写入源文件
     */
    protected void writeSource(Submit submit,Boolean needWriteCode){
        if (submit == null){
            return;
        }
        if (needWriteCode == null){
            needWriteCode = true;
        }
        String sourcePath = dockerJudgeConfig.getSourcePath();
        fileReaderWriter.writer(sourcePath,submit.getContent(),false);
    }
}
