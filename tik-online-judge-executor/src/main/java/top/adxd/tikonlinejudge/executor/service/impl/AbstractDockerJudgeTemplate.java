package top.adxd.tikonlinejudge.executor.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.executor.config.docker.DockerConfig;
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

/**
 * 使用Docker进行编译、运行的模板类
 * @author light
 */
public abstract class AbstractDockerJudgeTemplate<T extends IDockerJudgeConfig> implements ICodeJudge {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDockerJudgeTemplate.class);
    private static final String TRIM_END_REGEX = "[\\s]*$";
    //防止直接与默认Long最小值操作的时与所预期的想法不同。（越界）
    protected T dockerJudgeConfig;
    public AbstractDockerJudgeTemplate(T dockerJavaCodeJudge){
        this.dockerJudgeConfig = dockerJavaCodeJudge;
    }
    private static final Long MIN_TIME = Long.MIN_VALUE /2;

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
    public void setDockerJudgeConfig(T dockerJudgeConfig) {
        this.dockerJudgeConfig = dockerJudgeConfig;
    }
    public T getDockerJudgeConfig() {
        return dockerJudgeConfig;
    }
    /**
     * 当容器被创建完成即准备好了.
     */
    protected boolean ready;
    /**
     * 防止多个线程同时访问某个语言的评判
     * @param submit
     * @return
     */
    @Override
    public synchronized List<JudgeResult> judge(Submit submit) {
        if (!ready){
            rescureContainer();
        }
        writeSource(submit);
        List<ProblemData> problemDataList = problemDataService.getProblemDataList(submit);
        //保证不报空指针异常
        if (problemDataList.size() == 0){
            ProblemData problemData = new ProblemData();
            problemData.setInput("");
            problemDataList.add(problemData);
        }
        List<JudgeResult> results = new ArrayList<>();
        int count = 0;
        for (ProblemData problemData : problemDataList){
            writeInputOutput(problemData);
            if (count == 0){
                //第一次需要编译
                setNeedCompile(true);
                clearCompileInfo();
            }else if (count == 1){
                setNeedCompile(false);
            }
            JudgeResult judge = null;
            try {
                judge = judge(problemData,submit.getId());
                results.add(judge);
            }catch (Exception e){
                logger.error(e.getLocalizedMessage());
                e.printStackTrace();
                judge = new JudgeResult();
                judge.setSuccess(false);
                judge.setJudgeStatus(JudgeStatus.SYSTEM_ERROR);
                judge.setSubmitId(submit.getId());
            }
            //编译错误，后续不在运行
            if (judge.getJudgeStatus() == JudgeStatus.COMPILE_ERROR){
                return results;
            }
            count++;
        }
        return results;
    }

    public boolean isReady() {
        return ready;
    }

    protected void clearCompileInfo() {
        if (dockerJudgeConfig instanceof ICompileAbleConfig) {
            fileReaderWriter.writer(((ICompileAbleConfig) dockerJudgeConfig).getCompileInfo(), "", false);
        }
    }

    /**
     *
     * @param problemData 问题数据项
     */
    protected JudgeResult judge(ProblemData problemData,Long submitId){
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setSubmitId(submitId);
        //启动程序
        try {
            startContainer();
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
        //TODO 等待程序强行停止的时间
        try {
            stopProcess(3);
        }catch (NotModifiedException e){

        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
        String compileErrorMessage = getCompileErrorMessage();
        if (!"".equals(compileErrorMessage.trim())){
            judgeResult.setJudgeStatus(JudgeStatus.COMPILE_ERROR);
            judgeResult.setErrorOutput(compileErrorMessage);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        //非正常退出
        String stderr = getStderr();
        if (!"".equals(stderr.trim())){
            judgeResult.setErrorOutput(stderr);
            judgeResult.setJudgeStatus(JudgeStatus.RUNTIME_ERROR);
            judgeResult.setSuccess(false);
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
        String output = getStdout();
        if (output.equals(problemData.getOutput())) {
            judgeResult.setSuccess(true);
            judgeResult.setJudgeStatus(JudgeStatus.ACCEPT);
            return judgeResult;
        }
        judgeResult.setSuccess(false);
        //判断是否格式错误
        //策略：判断是否因为尾部的空格等不可见的符号导致错误
        if (trimEnds(output).equals(problemData.getOutput()) || output.equals(trimEnds(problemData.getOutput()))){
            judgeResult.setJudgeStatus(JudgeStatus.PRESENTATION_ERROR);
        }else {
            judgeResult.setJudgeStatus(JudgeStatus.WRONG_ANSWER);
        }
        return judgeResult;
    }

    private String trimEnds(String src) {
        if (src == null){
            return src;
        }
        return src.replaceFirst(TRIM_END_REGEX, "");
    }

    /**
     * 强行停止容器
     * @param time 等待时间
     */
    protected void stopProcess(Integer time){
        dockerClient.stopContainerCmd(dockerJudgeConfig.getContainerName()).withTimeout(time).exec();
    }

    /**
     * 设置本次启动是否需要编译
     * @param needCompile
     */
    protected void setNeedCompile(boolean needCompile){
        if (dockerJudgeConfig instanceof ICompileAbleConfig){
            String needCompilePath = ((ICompileAbleConfig) dockerJudgeConfig).needCompile();
            if (needCompile){
                fileReaderWriter.writer(needCompilePath,"1",false);
            }else {
                fileReaderWriter.writer(needCompilePath,"0",false);
            }
        }
    }

    protected void startContainer(){
        dockerClient
                .startContainerCmd(dockerJudgeConfig.getContainerName())
                .exec();
    }
    protected String getCompileErrorMessage(){
        if (dockerJudgeConfig instanceof ICompileAbleConfig){
            String compileInfoPath = ((ICompileAbleConfig) dockerJudgeConfig).getCompileInfo();
            return fileReaderWriter.Reader(compileInfoPath);
        }
        return "";
    }
    /**
     * 获取文件中程序执行的输出
     * @return 程序执行的输出
     */
    protected String getStdout(){
        return fileReaderWriter.Reader(dockerJudgeConfig.getStdout());
    }

    protected String getStderr(){
        return fileReaderWriter.Reader(dockerJudgeConfig.getStderr());
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
     * 其中100ms为容器的开销 不稳定开销
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
        Long executeTime = -compileTime + between.getSeconds() * 1000 + between.getNano() / 1000000 - 100;
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
        fileReaderWriter.writer(dockerJudgeConfig.getInput(),problemData.getInput(),false);
        //输出文件置空
        fileReaderWriter.writer( dockerJudgeConfig.getStdout(),"",false);
        //错误输出文件置空
        fileReaderWriter.writer(dockerJudgeConfig.getStderr(),"",false);
    }
    /**
     * 写入源文件
     */
    protected void writeSource(Submit submit){
        if (submit == null){
            return;
        }
        String sourcePath = dockerJudgeConfig.getSourcePath();
        fileReaderWriter.writer(sourcePath,submit.getContent(),false);
    }

    /**
     * 拯救已经坏了的容器，
     * 删除旧容器，重新创建新容器
     */
    protected void rescureContainer(){
        removeContainer();
        createContainer();
    }

    /**
     * 根据配置信息，尝试删除容器
     */
    protected void removeContainer(){
        try {
            dockerClient.removeContainerCmd(dockerJudgeConfig.getContainerName())
                    .withRemoveVolumes(true)
                    .exec();
        //尝试删除失败，容器可能还没有被创建
        }catch (com.github.dockerjava.api.exception.NotFoundException notFoundException){

        }
        this.ready = false;
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
            logger.trace("成功创建容器：" + exec.getId());
        }
        this.ready = true;
    }


}
