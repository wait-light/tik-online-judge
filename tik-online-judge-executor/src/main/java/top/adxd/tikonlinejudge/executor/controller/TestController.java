package top.adxd.tikonlinejudge.executor.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.api.IExecutorServiceApi;
import top.adxd.tikonlinejudge.executor.api.entity.ExecutorRunningStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @DubboReference(cluster = "broadcast", merger = "list")
    private IExecutorServiceApi executorServiceApi;

    @RequestMapping("/a")
    public Object aa() {
        return executorServiceApi.executorList();
    }

    public List<ExecutorRunningStatus> test(List<ExecutorRunningStatus> statuses) {
        System.out.println("合并");
        List<ExecutorRunningStatus> result = new ArrayList<>();
        result.addAll(statuses);
        return result;
    }
}
