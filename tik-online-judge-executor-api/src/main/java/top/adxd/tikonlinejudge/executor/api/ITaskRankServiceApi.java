package top.adxd.tikonlinejudge.executor.api;

import java.util.List;

public interface ITaskRankServiceApi {
    boolean initializationTaskRank(Long taskId, List<Long> uids);
}
