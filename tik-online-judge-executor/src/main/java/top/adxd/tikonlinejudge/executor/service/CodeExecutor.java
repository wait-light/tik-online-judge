package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.vo.ExecuteInput;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 代码执行接口
 */
public interface CodeExecutor {
    ExecuteResult execute(ExecuteInput executeInput) throws ExecutionException, InterruptedException;
    CompletableFuture<ExecuteResult> executeAsync(ExecuteInput executeInput) throws ExecutionException, InterruptedException;
}
