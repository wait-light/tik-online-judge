package top.adxd.tikonlinejudge.executor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.config.CppCodeExecuteConfig;
import top.adxd.tikonlinejudge.executor.service.CmdExecutor;
import top.adxd.tikonlinejudge.executor.service.ICodeExecutor;
import top.adxd.tikonlinejudge.executor.vo.ExecuteCMDResult;
import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service("cppCodeExecutor")
public class CPPCodeExecutor implements ICodeExecutor {
    @Autowired
    private CppCodeExecuteConfig cppCodeExecuteConfig;
    @Override
    public ExecuteResult execute(ExecuteInput executeInput,boolean isCompile)  {

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
    public CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput,boolean isCompile)  {
        return null;
    }
}
