package top.adxd.tikonlinejudge.executor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.ExecutorConfig;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.CodeExecutor;
import top.adxd.tikonlinejudge.executor.config.JavaCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteStatus;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

@Service
public class JavaCodeExecutor implements CodeExecutor {
    @Autowired
    private JavaCodeExecuteConfig javaCodeExecuteConfig;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private ExecutorConfig baseExecutorConfig;
    private static final Logger logger = LoggerFactory.getLogger(JavaCodeExecutor.class);

    @Override
    public ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                .setCmdWithArgs("javac " + javaCodeExecuteConfig.getCodeFileName())
                .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                .build();
        ExecuteCMDResult result = build.get();
        if (result.isSuccess()){
            CmdExecutor java_main = new CmdExecutor.CmdExecutorBuilder()
                    .setCmdWithArgs("java " + javaCodeExecuteConfig.getClassName())
                    .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                    .build();
            result = java_main.get();
        }

        return ExecuteResult.parse(result);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        return null;
    }

}

