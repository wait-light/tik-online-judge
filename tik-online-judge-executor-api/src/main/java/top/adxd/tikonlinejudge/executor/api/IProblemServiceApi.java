package top.adxd.tikonlinejudge.executor.api;

import top.adxd.tikonlinejudge.executor.api.entity.Problem;

import java.util.List;

public interface IProblemServiceApi {
    List<Problem> problemInfoList(List<Long> pids,String... select);
}
