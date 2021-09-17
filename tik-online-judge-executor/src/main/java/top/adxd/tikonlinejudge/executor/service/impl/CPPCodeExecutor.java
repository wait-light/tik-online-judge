package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.stereotype.Service;
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
    @Override
    public ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException {

        CmdExecutor build = new CmdExecutor.CmdExecutorBuilder()
//                .setDir(new File("C:\\Users\\light\\Desktop\\cpptest"))
                .setCmdWithArgs("C:\\Users\\light\\Desktop\\cpptest\\a.exe")
//                .setCmdWithArgs("java Main")
                .build();
        ExecuteCMDResult executeCMDResult = build.get();
        return ExecuteResult.parse(executeCMDResult);
    }

    @Override
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException {
        return null;
    }
}
