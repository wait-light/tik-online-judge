package top.adxd.tikonlinejudge.executor.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.ExecutorConfig;
import top.adxd.tikonlinejudge.executor.exception.runtime.CmdNotFoundException;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.CodeExecutor;
import top.adxd.tikonlinejudge.executor.service.impl.javadependency.JavaCodeExecuteProperties;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteStatus;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

@Service
public class JavaCodeExecutor implements CodeExecutor {
    @Autowired
    private JavaCodeExecuteProperties javaCodeExecuteConfig;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private ExecutorConfig bashExecutorConfig;
    private static final Logger logger = LoggerFactory.getLogger(JavaCodeExecutor.class);

    @Override
    public ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        Compile compile = new Compile(executeInput);
        ExecuteResult executeResult = compile.get();
        if (!executeResult.success){
            return executeResult;
        }
        CmdExecutor java_main = new CmdExecutor.CmdExecutorBuilder()
                .setCmdWithArgs("java Main")
                .setDir(new File("D:\\study\\spring\\cloud\\project\\tik-online-judge\\tik-online-judge-executor\\classTarget"))
                .build();
        ExecuteCMDResult executeCMDResult = java_main.get();
        return ExecuteResult.parse(executeCMDResult);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        return null;
    }


    private class Compile implements Supplier<ExecuteResult> {
        private ExecuteInput executeInput;

        public Compile(ExecuteInput executeInput) {
            this.executeInput = executeInput;
        }

        @Override
        public ExecuteResult get() {
            ExecutorConfig config = ExecutorConfig.convert(executeInput);
            ExecuteResult executeResult = new ExecuteResult();
            CmdExecutor cmdExecutor = new CmdExecutor.CmdExecutorBuilder()
                    .setCmdWithArgs("javac " + javaCodeExecuteConfig.getClassFullPath() + " -encoding utf-8")
                    .build();
            /**CompletableFuture<ExecuteCMDResult> executeCMDResultCompletableFuture = CompletableFuture.supplyAsync(executeCMD,threadPoolExecutor);
             * 如果还用线程池执行就会发生死锁
             * 解决方法:
             * 1.直接调用 executeCMD.get();
             * 2.使用CompletableFuture.supplyAsync(executeCMD)
             * 单参数方法 (会默认使用其自带的线程池,不会发生死锁)
             */
            try {
                ExecuteCMDResult executeCMDResult = cmdExecutor.get();
                executeResult.setExecuteTime(executeCMDResult.getExecuteTime());
                if (executeCMDResult.isSuccess()) {
                    executeResult.setSuccess(true);
                    executeResult.setExecuteStatus(ExecuteStatus.SUCCESS);
                    executeResult.setOutputString(executeCMDResult.getSuccessOutput());
                } else {
                    executeResult.setSuccess(false);
                    executeResult.setExecuteStatus(ExecuteStatus.COMPILE_ERROR);
                    executeResult.setOutputString(executeCMDResult.getErrorOutput());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return executeResult;
        }
    }
}

