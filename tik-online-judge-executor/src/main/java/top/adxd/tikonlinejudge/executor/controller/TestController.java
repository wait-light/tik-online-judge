package top.adxd.tikonlinejudge.executor.controller;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.api.IExecutorServiceApi;
import top.adxd.tikonlinejudge.executor.api.entity.ExecutorRunningStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @DubboReference(interfaceClass = IExecutorServiceApi.class,cluster = "absolute-broadcast")
    private IExecutorServiceApi executorServiceApi;

    @RequestMapping("/a")
    public Object aa() {

        List<ExecutorRunningStatus> executorRunningStatuses = executorServiceApi.executorList();
        System.out.println(executorRunningStatuses);
        return "呵呵";
    }

    public List<ExecutorRunningStatus> test(List<ExecutorRunningStatus> statuses) {
        System.out.println("合并");
        List<ExecutorRunningStatus> result = new ArrayList<>();
        result.addAll(statuses);
        return result;
    }
}
