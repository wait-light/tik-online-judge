package top.adxd.tikonlinejudge.executor.service.docker.judge;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
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
import top.adxd.tikonlinejudge.executor.service.docker.env.DockerEnvService;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;
import top.adxd.tikonlinejudge.executor.single.Language;
import top.adxd.tikonlinejudge.executor.util.PathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Docker进行编译、运行的模板类
 *
 * @author light
 */
public abstract class AbstractDockerJudgeTemplate<T extends IDockerJudgeConfig> implements ICodeJudge {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDockerJudgeTemplate.class);
    private static final String TRIM_END_REGEX = "[\\s]*$";
    public static final Integer DEFAULT_TIME_LIMIT = 5000;
    public static final Long DEFAULT_MEMORY_LIMIT = 131072L;
    public static final Integer DEFAULT_SCORE = 1;
    //唯一标识
    private String id;
    //防止直接与默认Long最小值操作的时与所预期的想法不同。（越界）
    protected T dockerJudgeConfig;

    private static final Long MIN_TIME = Long.MIN_VALUE / 2;
    @Autowired
    protected DockerEnvService dockerEnvService;
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

    /**
     * 当容器被创建完成即准备好了.
     */
    protected volatile boolean ready;
    /**
     * 当代码正在执行
     */
    protected volatile boolean running;

    protected AbstractDockerJudgeTemplate(T dockerJavaCodeJudge) {
        this();
        this.dockerJudgeConfig = dockerJavaCodeJudge;
    }

    protected AbstractDockerJudgeTemplate() {
        this.id = RandomUtil.randomString(20);
    }

    public String getId() {
        return id;
    }

    public void setDockerJudgeConfig(T dockerJudgeConfig) {
        this.dockerJudgeConfig = dockerJudgeConfig;
    }

    public T getDockerJudgeConfig() {
        return dockerJudgeConfig;
    }


    public abstract Language getLanguage();

    public boolean isRunning() {
        return running;
    }

    private void submitJudgingStatusUpdate(Long id, JudgeStatus judgeStatus) {
        submitService.update(new UpdateWrapper<Submit>()
                .eq("id", id)
                .set("status", judgeStatus));
    }

    /**
     * 防止多个线程同时访问某个语言的评判
     *
     * @param submit
     * @return
     */
    @Override
    public synchronized List<JudgeResult> judge(Submit submit) {
        if (!ready) {
            try {
                rescureContainer();
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        running = true;

        submitJudgingStatusUpdate(submit.getId(), JudgeStatus.JUDGING);

        List<JudgeResult> results = new ArrayList<>();
        try {
            writeSource(submit);
            List<ProblemData> problemDataList = problemDataService.getProblemDataList(submit);
            //保证不报空指针异常
            if (problemDataList.size() == 0) {
                ProblemData problemData = new ProblemData();
                problemData.setInput("");
                problemDataList.add(problemData);
            }
            int count = 0;
            for (ProblemData problemData : problemDataList) {
                writeInputOutput(problemData);
                if (count == 0) {
                    //第一次需要编译
                    setNeedCompile(true);
                    clearCompileInfo();
                } else if (count == 1) {
                    setNeedCompile(false);
                }
                JudgeResult judge = null;
                try {
                    setMaxRuntimeInfo(1024 * 100L, 5000L);
                    judge = judge(problemData, submit.getId());
                    results.add(judge);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                    e.printStackTrace();
                    judge = new JudgeResult();
                    judge.setScore(0);
                    judge.setSuccess(false);
                    judge.setJudgeStatus(JudgeStatus.SYSTEM_ERROR);
                    judge.setSubmitId(submit.getId());
                }
                //编译错误，后续不在运行
                if (judge.getJudgeStatus() == JudgeStatus.COMPILE_ERROR) {
                    judge.setScore(0);
                    judge.setExecutionTime(DEFAULT_TIME_LIMIT);
                    judge.setRuntimeMemory(DEFAULT_MEMORY_LIMIT);
                    return results;
                }
                count++;
            }
        } catch (Exception e) {
            JudgeResult judgeResult = new JudgeResult();
            judgeResult.setScore(0);
            judgeResult.setSubmitId(submit.getId());
            judgeResult.setSuccess(false);
            judgeResult.setExecutionTime(DEFAULT_TIME_LIMIT);
            judgeResult.setRuntimeMemory(DEFAULT_MEMORY_LIMIT);
            judgeResult.setJudgeStatus(JudgeStatus.SYSTEM_ERROR);
            results.clear();
            results.add(judgeResult);
        } finally {
            running = false;
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
     * @param problemData 问题数据项
     */
    protected JudgeResult judge(ProblemData problemData, Long submitId) {
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setSubmitId(submitId);
        judgeResult.setScore(0);
        judgeResult.setExecutionTime(problemData.getTimeLimit() != null ? problemData.getTimeLimit() : DEFAULT_TIME_LIMIT);
        judgeResult.setRuntimeMemory(problemData.getMemoryLimit() != null ? problemData.getMemoryLimit() : DEFAULT_MEMORY_LIMIT);
        //启动程序
        try {
            startContainer();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        Integer timeLimit = problemData.getTimeLimit() != null ? problemData.getTimeLimit() : DEFAULT_TIME_LIMIT;
        try {
            //强制停止时间
            stopProcess(timeLimit + 1);
        } catch (NotModifiedException e) {

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        String compileErrorMessage = getCompileErrorMessage();
        if (!"".equals(compileErrorMessage.trim())) {
            judgeResult.setJudgeStatus(JudgeStatus.COMPILE_ERROR);
            judgeResult.setErrorOutput(compileErrorMessage);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        //非正常退出
        String stderr = getStderr();
        if (!"".equals(stderr.trim())) {
            judgeResult.setErrorOutput(stderr);
            judgeResult.setJudgeStatus(JudgeStatus.RUNTIME_ERROR);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        Long memoryLimit = problemData.getMemoryLimit() != null ? problemData.getMemoryLimit() : DEFAULT_MEMORY_LIMIT;
        RuntimeInfo runtimeInfo = getRuntimeInfo();
        Integer executeTime = runtimeInfo.getRuntime();
        judgeResult.setRuntimeMemory(runtimeInfo.getRuntimeMemory());
        judgeResult.setExecutionTime(executeTime);

        if (executeTime > timeLimit) {
            judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        if (runtimeInfo.getRuntimeMemory() > memoryLimit) {
            judgeResult.setJudgeStatus(JudgeStatus.MEMORY_LIMIT_EXCEEDED);
            judgeResult.setSuccess(false);
            return judgeResult;
        }
        String output = getStdout();
        if (output.equals(problemData.getOutput())) {
            judgeResult.setSuccess(true);
            judgeResult.setJudgeStatus(JudgeStatus.ACCEPT);
            judgeResult.setScore(problemData.getScore() != null ? problemData.getScore() : DEFAULT_SCORE);
            return judgeResult;
        }
        judgeResult.setSuccess(false);
        //判断是否格式错误
        //策略：判断是否因为尾部的空格等不可见的符号导致错误
        if (trimEnds(output).equals(problemData.getOutput()) || output.equals(trimEnds(problemData.getOutput()))) {
            judgeResult.setJudgeStatus(JudgeStatus.PRESENTATION_ERROR);
        } else {
            judgeResult.setJudgeStatus(JudgeStatus.WRONG_ANSWER);
        }
        if (judgeResult.getSuccess()) {
            judgeResult.setScore(problemData.getScore() != null ? problemData.getScore() : DEFAULT_SCORE);
        }
        return judgeResult;
    }

    private String trimEnds(String src) {
        if (src == null) {
            return src;
        }
        return src.replaceFirst(TRIM_END_REGEX, "");
    }

    /**
     * 强行停止容器
     *
     * @param time 等待时间
     */
    protected void stopProcess(Integer time) {
        dockerClient.stopContainerCmd(dockerJudgeConfig.getContainerName()).withTimeout(time).exec();
    }

    /**
     * 设置本次启动是否需要编译
     *
     * @param needCompile
     */
    protected void setNeedCompile(boolean needCompile) {
        if (dockerJudgeConfig instanceof ICompileAbleConfig) {
            String needCompilePath = ((ICompileAbleConfig) dockerJudgeConfig).needCompile();
            if (needCompile) {
                fileReaderWriter.writer(needCompilePath, "1", false);
            } else {
                fileReaderWriter.writer(needCompilePath, "0", false);
            }
        }
    }

    protected void startContainer() {
        dockerClient
                .startContainerCmd(dockerJudgeConfig.getContainerName())
                .exec();
    }

    protected String getCompileErrorMessage() {
        if (dockerJudgeConfig instanceof ICompileAbleConfig) {
            String compileInfoPath = ((ICompileAbleConfig) dockerJudgeConfig).getCompileInfo();
            return fileReaderWriter.Reader(compileInfoPath);
        }
        return "";
    }

    /**
     * 获取文件中程序执行的输出
     *
     * @return 程序执行的输出
     */
    protected String getStdout() {
        return fileReaderWriter.Reader(dockerJudgeConfig.getStdout());
    }

    protected String getStderr() {
        return fileReaderWriter.Reader(dockerJudgeConfig.getStderr());
    }

    /**
     * @return 返回可编译语言的编译时间
     */
    protected Long getCompileTime() {
        if (!(dockerJudgeConfig instanceof ICompileAbleConfig)) {
            return 0L;
        }
        String compileTimePath = ((ICompileAbleConfig) dockerJudgeConfig).getCompileTime();
        String compileTime = fileReaderWriter.Reader(compileTimePath);
        try {
            return Long.parseLong(compileTime);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return MIN_TIME;
        }
    }

    /**
     * @return 运行资源消耗信息
     */
    protected RuntimeInfo getRuntimeInfo() {
        String runtimeInfo = fileReaderWriter.Reader(dockerJudgeConfig.getRuntimeAndRuntimeMemory());
        String[] runtimeInfoSplit = runtimeInfo.split(" ");
        Long runtimeMemory = Long.parseLong(runtimeInfoSplit[0]);
        Integer runtime = new Double(Double.parseDouble(runtimeInfoSplit[1]) * 1000).intValue();
        return new RuntimeInfo(runtimeMemory, runtime);
    }

    /**
     * 写入输入、输出
     */
    protected void writeInputOutput(ProblemData problemData) {
        if (problemData == null) {
            return;
        }
        fileReaderWriter.writer(dockerJudgeConfig.getInput(), problemData.getInput(), false);
        //输出文件置空
        fileReaderWriter.writer(dockerJudgeConfig.getStdout(), "", false);
        //错误输出文件置空
        fileReaderWriter.writer(dockerJudgeConfig.getStderr(), "", false);
    }

    protected void setMaxRuntimeInfo(Long maxRuntimeMemory, Long maxRuntime) {
        if (maxRuntime == null || maxRuntime < 0) {
            maxRuntime = 0L;
        }
        if (maxRuntimeMemory == null || maxRuntimeMemory < 0) {
            maxRuntimeMemory = 0L;
        }
        //设置基础运行时间和内存信息
        fileReaderWriter.writer(dockerJudgeConfig.getRuntimeAndRuntimeMemory(), maxRuntimeMemory + " " + maxRuntime, false);
    }

    /**
     * 写入源文件
     */
    protected void writeSource(Submit submit) {
        if (submit == null) {
            return;
        }
        String sourcePath = dockerJudgeConfig.getSourcePath();
        fileReaderWriter.writer(sourcePath, submit.getContent(), false);
    }

    /**
     * 拯救已经坏了的容器，
     * 删除旧容器，重新创建新容器
     */
    public void rescureContainer() {
        running = false;
        if (!dockerEnvService.hasImage(dockerJudgeConfig.getImageName(), null)) {
            dockerEnvService.build(dockerJudgeConfig);
        }
        removeContainer();
        createContainer();
    }

    /**
     * 根据配置信息，尝试删除容器
     */
    protected void removeContainer() {
        try {
            dockerClient.removeContainerCmd(dockerJudgeConfig.getContainerName())
                    .withRemoveVolumes(true)
                    .exec();
            //尝试删除失败，容器可能还没有被创建
        } catch (com.github.dockerjava.api.exception.NotFoundException notFoundException) {

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        this.ready = false;
    }


    private boolean isWindowsPath(String path) {

        return false;
    }

    /**
     * 根据配置信息 尝试创建容器
     */
    protected void createContainer() {
        HostConfig hostConfig = new HostConfig();
        Volume inner = new Volume(dockerJudgeConfig.getWorkDir());
        /**
         * 将绑定的地址转换为unix地址
         * PathUtil.dosPath2unixPath(dockerJudgeConfig.getPath())。
         * 开发环境原因，wsl中的地址与windows本身的路径不同
         * 还需要将wsl中的root地址改为 /
         * 即修改 wsl中的 /etc/wsl.conf为如下内容
         * [automount]
         * root = /
         * options = "metadata"
         */
        Bind bind = new Bind(PathUtil.dosPath2unixPath(dockerJudgeConfig.getPath()), inner);
        hostConfig.setBinds(bind);
        CreateContainerResponse exec = null;
        boolean success = true;
        try {
            exec = dockerClient
                    .createContainerCmd(dockerJudgeConfig.getImageName())
                    .withName(dockerJudgeConfig.getContainerName())
                    .withHostConfig(hostConfig)
                    .exec();
        } catch (Exception e) {
            success = false;
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        if (success && null != exec) {
            logger.trace("成功创建容器：" + exec.getId());
        }
        this.ready = true;
    }

    public static class RuntimeInfo {
        private Long runtimeMemory;
        private Integer runtime;

        public RuntimeInfo(Long runtimeMemory, Integer runtime) {
            this.runtimeMemory = runtimeMemory;
            this.runtime = runtime;
        }

        public Integer getRuntime() {
            return runtime;
        }

        public Long getRuntimeMemory() {
            return runtimeMemory;
        }
    }

}
