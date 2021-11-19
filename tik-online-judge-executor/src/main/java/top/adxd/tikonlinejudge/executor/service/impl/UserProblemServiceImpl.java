package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.service.IUserProblemService;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserProblemServiceImpl implements IUserProblemService {
    @Autowired
    private ISubmitService submitService;

    @Override
    public CommonResult userProblemFinishSituations(Long... pid) {
        if (pid.length <= 0) {
            return CommonResult.success().singleData(new HashMap<>());
        }
        Map<Long, Boolean> finishSituations = new HashMap<>(pid.length);
        for (Long id : pid) {
            finishSituations.put(id,isFinish(id));
        }
        return CommonResult.success().singleData(finishSituations);
    }

    /**
     * 是否完成了某个问题
     * @param pid 问题id
     * @return 完成结果
     */
    private boolean isFinish(Long pid) {
        if (pid == null) {
            return false;
        }
        return submitService.getOne(new QueryWrapper<Submit>()
                .eq("problem_id", pid)
                .eq("status", JudgeStatus.ACCEPT.getValue())
                .select("id")
                .last("limit 1")) != null;
    }
}
