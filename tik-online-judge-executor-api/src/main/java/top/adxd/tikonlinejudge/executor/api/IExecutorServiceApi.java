package top.adxd.tikonlinejudge.executor.api;

import top.adxd.tikonlinejudge.executor.api.entity.ExecutorRunningStatus;

import java.util.List;

public interface IExecutorServiceApi {

    List<ExecutorRunningStatus> executorList();

    String restart(String id);

}
