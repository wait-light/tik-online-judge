package top.adxd.tikonlinejudge.executor.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.vo.SubmitJudgeResult;
import top.adxd.tikonlinejudge.user.api.UserInfo;

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
    @DubboReference
    private UserInfo userInfo;

    @GetMapping("/{problem-id}")
    public CommonResult problemJudgeResult(@PathVariable("problem-id") Long problemId,@RequestHeader("token") String token) {
        Long uid = userInfo.uid(token);
        List<SubmitJudgeResult> lists = judgeResultService.problemSubmitsResults(problemId, uid);
        return CommonResult.success().add("array", lists);
    }

    @GetMapping("/last/{problem-id}")
    public CommonResult problemLastJudgeResult(@PathVariable("problem-id") Long problemId,@RequestHeader("token") String token) {
        Long uid = userInfo.uid(token);
        SubmitJudgeResult judgeResults = judgeResultService.lastSubmitResults(problemId, uid);
        return CommonResult.success().add("array", judgeResults);
    }

    @GetMapping("/submit/{submit-id}")
    public CommonResult submitJudgeResult(@PathVariable("submit-id") Long submitId) {
        SubmitJudgeResult judgeResults = judgeResultService.submitJudgeResults(submitId);
        return CommonResult.success().add("array", judgeResults);
    }
}

