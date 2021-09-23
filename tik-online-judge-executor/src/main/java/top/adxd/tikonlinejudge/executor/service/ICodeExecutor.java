package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 代码执行接口
 */
public interface ICodeExecutor {
    ExecuteResult execute(ExecuteInput executeInput,boolean compile);
    CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput,boolean compile);
}
