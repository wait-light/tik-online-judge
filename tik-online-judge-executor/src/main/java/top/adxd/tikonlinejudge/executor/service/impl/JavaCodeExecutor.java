package top.adxd.tikonlinejudge.executor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.ICodeExecutor;
import top.adxd.tikonlinejudge.executor.config.JavaCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service("javaCodeExecutor")
public class JavaCodeExecutor implements ICodeExecutor {
    @Autowired
    private JavaCodeExecuteConfig javaCodeExecuteConfig;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    private static final Logger logger = LoggerFactory.getLogger(JavaCodeExecutor.class);

    @Override
    public ExecuteResult execute(ExecuteInput executeInput,boolean isCompile) {
        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                .setCmdWithArgs("javac " + javaCodeExecuteConfig.getCodeFileName())
                .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                .build();
        ExecuteCMDResult result = build.get();
        if (result.isSuccess()) {
            CmdExecutor java_main = new CmdExecutor.CmdExecutorBuilder()
                    .setCmdWithArgs("java " + javaCodeExecuteConfig.getClassName())
                    .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                    .setInput(new String[]{executeInput.getInput()})
                    .build();
            result = java_main.get();
        }else {
            /**
             * 使用 -100代表编译错误
             *
             */
            result.setExitCode(-100);
        }

        return ExecuteResult.parse(result);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput,boolean isCompile) {
        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                .setCmdWithArgs("javac " + javaCodeExecuteConfig.getCodeFileName())
                .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                .build();
        CmdExecutor java_main = new CmdExecutor.CmdExecutorBuilder()
                .setCmdWithArgs("java " + javaCodeExecuteConfig.getClassName())
                .setDir(new File(javaCodeExecuteConfig.getTargetPath()))
                .setInput(executeInput != null ? new String[]{executeInput.getInput()} : null)
                .build();
        CompletableFuture<ExecuteResult> executeResultCompletableFuture = CompletableFuture.supplyAsync(build, threadPoolExecutor)
                .thenApplyAsync((compileResult) -> {
                    if (compileResult.isSuccess()) {
                        return ExecuteResult.parse(java_main.get());
                    } else {
                        return ExecuteResult.parse(compileResult);
                    }
                });
        return executeResultCompletableFuture;
    }

}

