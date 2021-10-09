package top.adxd.tikonlinejudge.executor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.annotation.FrequencyLimit;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ICodeJudge;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import org.springframework.web.bind.annotation.RestController;

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
    private ICodeJudge dockerJavaCodeJudge;
    @Autowired
    private IJudgeResultService judgeResultService;
    @Autowired
    private ThreadPoolExecutor executor;

//    @PostMapping("/judge")
//    public CommonResult judge(@RequestBody @Valid Submit submit) {
//        //TODO 攻击检查
//        //TODO 用户
//        submit.setUid(1L);
//        submit.setCreateTime(LocalDateTime.now());
//        boolean submitSuccess = submitService.save(submit);
//        if (!submitSuccess) {
//            return CommonResult.error("提交失败");
//        }
//        List<JudgeResult> judge = codeJudge.judge(submit);
//        return CommonResult.success().listData(judge);
//    }

    @PostMapping("/judge")
//    @FrequencyLimit(value = 12,name = "judgeAsync")
    public CommonResult judgeAsync(@RequestBody @Valid Submit submit) {
        //TODO 用户
        submit.setUid(1L);
        submit.setCreateTime(LocalDateTime.now());
        boolean submitSuccess = submitService.save(submit);
        if (!submitSuccess) {
            return CommonResult.error("提交失败");
        }
        /**
         * 异步运行
         */
        CompletableFuture
                .supplyAsync(() -> {
                    return dockerJavaCodeJudge.judge(submit);
                }, executor)
                .thenAcceptAsync((result) -> {
                    judgeResultService.updateCommitAfterJudge(result,submit);
                }, executor);
        return CommonResult.success("提交成功");
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<Submit> list = submitService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Submit entity) {
        return submitService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return submitService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return submitService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Submit entity) {
        return submitService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        Submit entity = submitService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

