package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.PythonCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.CodeExecutor;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PythonCodeExecutor implements CodeExecutor {

    @Autowired
    private PythonCodeExecuteConfig pythonCodeExecuteConfig;

    @Override
    public ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        File desktop = new File(pythonCodeExecuteConfig.getTargetPath());
        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
                .setDir(desktop)
                .setCmdWithArgs("python " + pythonCodeExecuteConfig.getCodeFullPath())
                .build();
        ExecuteCMDResult result = build.get();
        return ExecuteResult.parse(result);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        return null;
    }
}
