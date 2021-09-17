package top.adxd.tikonlinejudge.executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.executor.config.ExecutorConfig;
import top.adxd.tikonlinejudge.executor.exception.runtime.CmdNotFoundException;
import top.adxd.tikonlinejudge.executor.util.BeanUtil;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.function.Supplier;

/**
 * 命令执行类
 */
@Component
public class CmdExecutor implements Supplier<ExecuteCMDResult>  {
    private static ExecutorConfig bashExecutorConfig;
    @Autowired
    public void setBashExecutorConfig( ExecutorConfig executorConfig){
        CmdExecutor.bashExecutorConfig = executorConfig;
    }
    private static final Logger logger = LoggerFactory.getLogger(CmdExecutor.class);
    private String cmdWithArgs;
    private String[] input;
    private ExecutorConfig executorConfig;
    private String[] envp;
    private File dir;

    private CmdExecutor() {
    }

    /**
     * 命令执行者构建器
     */
    public static class CmdExecutorBuilder {
        private String cmdWithArgs;
        private String[] input;
        private ExecutorConfig executorConfig;
        private String[] envp;
        private File dir;

        public String[] getEnvp() {
            return envp;
        }

        public CmdExecutorBuilder setEnvp(String[] envp) {
            this.envp = envp;
            return this;
        }

        public File getDir() {
            return dir;
        }

        public CmdExecutorBuilder setDir(File dir) {
            this.dir = dir;
            return this;
        }

        public String getCmdWithArgs() {
            return cmdWithArgs;
        }

        public CmdExecutorBuilder setCmdWithArgs(String cmdWithArgs) {
            this.cmdWithArgs = cmdWithArgs;
            return this;
        }

        public String[] getInput() {
            return input;
        }

        public CmdExecutorBuilder setInput(String[] input) {
            this.input = input;
            return this;
        }

        public ExecutorConfig getExecutorConfig() {
            return executorConfig;
        }

        public CmdExecutorBuilder setExecutorConfig(ExecutorConfig executorConfig) {
            this.executorConfig = executorConfig;
            return this;
        }

        public CmdExecutor build() {
            CmdExecutor cmdExecutor = new CmdExecutor();
            if (null == cmdExecutor || "".equals(cmdWithArgs.trim())) {
                throw new CmdNotFoundException("cmd can not be empty");
            }
            cmdExecutor.executorConfig = executorConfig;
            cmdExecutor.cmdWithArgs = cmdWithArgs;
            cmdExecutor.dir = dir;
            cmdExecutor.envp = envp;
            return cmdExecutor;
        }
    }


    @Override
    public ExecuteCMDResult get() {
        ExecuteCMDResult executeCMDResult = new ExecuteCMDResult();
        StringBuilder resultStr = new StringBuilder();
        final long beginTime = System.currentTimeMillis();
        try {
            // 执行指令
            final Process process = Runtime.getRuntime().exec(cmdWithArgs, envp, dir);
            if (process != null) {
                ExecutorConfig config;
                if (executorConfig != null) {
                    config = executorConfig;
                } else {
                    config = bashExecutorConfig;
                }
                byte[] bytes = new byte[1024];
                int len = -1;
                //输出到命令行
                if (input != null && input.length != 0) {
                    OutputStream out2Console = process.getOutputStream();
                    for (int i = 0; i < input.length; i++) {
                        out2Console.write(input[i].getBytes());
                        out2Console.flush();
                    }
                    out2Console.close();
                }
                boolean b = process.waitFor(config.getMaxExecuteTime(), config.getTimeUnit());
                executeCMDResult.setExecuteTime(System.currentTimeMillis() - beginTime);
                // 获取错误输出
                InputStream errorStream = process.getErrorStream();
                len = -1;
                while ((len = errorStream.read(bytes)) != -1) {
                    resultStr.append(new String(bytes, 0, len, Charset.forName("gbk")));
                }
                errorStream.close();
                executeCMDResult.setErrorOutput(resultStr.toString());
                if (resultStr.length() > 0) {
                    executeCMDResult.setSuccess(false);
                    return executeCMDResult;
                }
                //清空
                len = -1;
                resultStr.delete(0, resultStr.length());
                // 获取输出
                InputStream inputStream = process.getInputStream();
                while ((len = inputStream.read(bytes)) != -1) {
                    resultStr.append(new String(bytes, 0, len,Charset.forName("gbk")));
                }
                inputStream.close();
                executeCMDResult.setSuccess(true);
                executeCMDResult.setSuccessOutput(resultStr.toString());
            }
        } catch (IOException ioException) {
            executeCMDResult.setSuccess(false);
            executeCMDResult.setErrorOutput(resultStr.toString());
            executeCMDResult.setExecuteTime(System.currentTimeMillis() - beginTime);
            logger.error(ioException.getMessage());
        } catch (InterruptedException exception) {
            executeCMDResult.setSuccess(false);
            executeCMDResult.setErrorOutput("Time out");
            executeCMDResult.setExecuteTime(System.currentTimeMillis() - beginTime);
            logger.error(exception.getMessage());
        }
        return executeCMDResult;
    }
}