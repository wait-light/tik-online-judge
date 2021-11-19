package top.adxd.tikonlinejudge.executor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.service.IUserProblemService;

@RestController
@RequestMapping("/executor/user")
public class UserProblemController {
    @Autowired
    private IUserProblemService userProblemService;

    @GetMapping("/problem/status")
    public CommonResult userProblemStatus(@RequestParam Long... pid) {
        return userProblemService.userProblemFinishSituations(pid);
    }
}
