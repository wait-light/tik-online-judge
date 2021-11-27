package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.service.IUserProblemService;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserProblemServiceImpl implements IUserProblemService {
    @Autowired
    private ISubmitService submitService;

    @Override
    public CommonResult userProblemFinishSituations(Long... pid) {
        Map<Long, Boolean> finishSituations = userProblemFinishSituations(UserInfoUtil.getUid(), Arrays.asList(pid));
        return CommonResult.success().singleData(finishSituations);
    }

    @Override
    public CommonResult userProblemFinishSituations(List<Long> uid, List<Long> pid) {
        Map<Long, Map<Long, Boolean>> finishSituations = new HashMap<>();
        for (Long u : uid) {
            Map<Long, Boolean> longBooleanMap = userProblemFinishSituations(u, pid);
            finishSituations.put(u, longBooleanMap);
        }
        return CommonResult.success().singleData(finishSituations);
    }

    private Map<Long, Boolean> userProblemFinishSituations(Long uid, List<Long> pid) {
        if (pid == null || pid.size() <= 0 || uid == null) {
            return new HashMap<>();
        }
        Map<Long, Boolean> finishSituations = new HashMap<>(pid.size());
        for (Long id : pid) {
            finishSituations.put(id, isFinish(id, uid));
        }
        return finishSituations;
    }

    /**
     * 是否完成了某个问题
     *
     * @param pid 问题id
     * @return 完成结果
     */
    private boolean isFinish(Long pid, Long uid) {
        if (pid == null) {
            return false;
        }
        return submitService.getOne(new QueryWrapper<Submit>()
                .eq("uid", uid)
                .eq("problem_id", pid)
                .eq("status", JudgeStatus.ACCEPT.getValue())
                .select("id")
                .last("limit 1")) != null;
    }
}
