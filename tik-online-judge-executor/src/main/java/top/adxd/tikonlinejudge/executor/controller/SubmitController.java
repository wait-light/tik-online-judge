package top.adxd.tikonlinejudge.executor.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.service.mq.SubmitSender;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-21
 */
@RestController
@RequestMapping("/executor/submit")
public class SubmitController {
    @Autowired
    private ISubmitService submitService;
    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private SubmitSender submitSender;


    @PostMapping("/judge")
//    @FrequencyLimit(value = 12,name = "judgeAsync")
    public CommonResult judgeAsync(@RequestBody @Valid Submit submit, @RequestHeader("uid") Long uid) {
        submit.setCreateTime(LocalDateTime.now());
        submit.setUid(uid);
        boolean submitSuccess = submitService.save(submit);
        if (!submitSuccess) {
            return CommonResult.error("提交失败");
        }
        
        submitSender.send(submit);
        return CommonResult.success("提交成功");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        Submit entity = submitService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }
}

