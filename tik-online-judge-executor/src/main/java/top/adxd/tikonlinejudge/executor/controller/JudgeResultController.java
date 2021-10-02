package top.adxd.tikonlinejudge.executor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.vo.SubmitJudgeResult;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-24
 */
@RestController
@RequestMapping("/executor/judge-result")
public class JudgeResultController {

    @Autowired
    private IJudgeResultService judgeResultService;

    @GetMapping("/{problem-id}")
    public CommonResult problemJudgeResult(@PathVariable("problem-id") Long problemId) {
        //TODO 用户信息
        List<SubmitJudgeResult> lists = judgeResultService.problemSubmitsResults(problemId, 1L);
        return CommonResult.success().add("array", lists);
    }

    @GetMapping("/last/{problem-id}")
    public CommonResult problemLastJudgeResult(@PathVariable("problem-id") Long problemId) {
        //TODO 用户信息
        SubmitJudgeResult judgeResults = judgeResultService.lastSubmitResults(problemId, 1L);
        return CommonResult.success().add("array", judgeResults);
    }

    @GetMapping("/submit/{submit-id}")
    public CommonResult submitJudgeResult(@PathVariable("submit-id") Long submitId) {
        //TODO 用户信息
        SubmitJudgeResult judgeResults = judgeResultService.submitJudgeResults(submitId);
        return CommonResult.success().add("array", judgeResults);
    }

}

