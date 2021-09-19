package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.CppCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.CodeExecutor;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CPPCodeExecutor implements CodeExecutor {
    @Autowired
    private CppCodeExecuteConfig cppCodeExecuteConfig;
    @Override
    public ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException {

        File file = new File(cppCodeExecuteConfig.getTargetPath());
        CmdExecutor compile = new CmdExecutor.CmdExecutorBuilder()
                .setDir(file)
                .setCmdWithArgs(cppCodeExecuteConfig.getGppPath() + " " + cppCodeExecuteConfig.getCodeFileName())
                .build();
        ExecuteCMDResult result = compile.get();
        if (result.isSuccess()){
            CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                    .setDir(file)
                    .setCmdWithArgs(cppCodeExecuteConfig.getTargetProcessPath())
                    .build();
            result = build.get();
        }

        return ExecuteResult.parse(result);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        return null;
    }
}