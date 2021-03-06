package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.entity.TaskSubmit;
import top.adxd.tikonlinejudge.executor.mapper.JudgeResultMapper;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.service.ITaskRankService;
import top.adxd.tikonlinejudge.executor.service.ITaskSubmitService;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;
import top.adxd.tikonlinejudge.executor.vo.SubmitJudgeResult;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-24
 */
@Service
public class JudgeResultServiceImpl extends ServiceImpl<JudgeResultMapper, JudgeResult> implements IJudgeResultService {
    @Autowired
    private ISubmitService submitService;
    @Autowired
    private ITaskSubmitService taskSubmitService;
    @Autowired
    private ITaskRankService taskRankService;

    @Override
    public SubmitJudgeResult submitJudgeResults(Long submitId) {
        Long uid = UserInfoUtil.getUid();
        Submit submit = submitService.getOne(new QueryWrapper<Submit>().eq("id", submitId).eq("uid", uid));
        if (submit == null) {
            return null;
        }
        List<JudgeResult> submitResults = baseMapper.selectList(new QueryWrapper<JudgeResult>().eq("submit_id", submitId));
        SubmitJudgeResult submitJudgeResult = new SubmitJudgeResult();
        submitJudgeResult.setJudgeResults(submitResults);
        return submitJudgeResult;

    }

    @Override
    public SubmitJudgeResult lastSubmitResults(Long problemId, Long userid) {
        Submit lastSubmit = submitService.getOne(new QueryWrapper<Submit>()
                .select("id", "create_time", "status", "runtime_memory", "runtime")
                .eq("uid", userid)
                .eq("problem_id", problemId)
                .orderByDesc("create_time")
                .last("limit 1"));
        if (lastSubmit == null) {
            return null;
        }
        SubmitJudgeResult submitJudgeResult = submitJudgeResults(lastSubmit.getId());
        fillData(submitJudgeResult, lastSubmit);
        return submitJudgeResult;
    }

    @Override
    public List<SubmitJudgeResult> problemSubmitsResults(Long problemId, Long userid) {
        List<Submit> submits = submitService.list(new QueryWrapper<Submit>()
                .select("id", "create_time", "status", "runtime_memory", "runtime")
                .eq("uid", userid)
                .eq("problem_id", problemId)
                .orderByDesc("create_time"));
        List<SubmitJudgeResult> result = new ArrayList<>();
        if (null == submits || submits.size() == 0) {
            return result;
        }
        for (Submit submit : submits) {
            SubmitJudgeResult submitJudgeResult = submitJudgeResults(submit.getId());
            fillData(submitJudgeResult, submit);
            result.add(submitJudgeResult);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommitAfterJudge(List<JudgeResult> judgeResults, Submit submit) {
        if (judgeResults == null || judgeResults.size() <= 0 || submit == null) {
            return;
        }
        JudgeStatus submitStatus = null;
        int acceptCount = 0;
        Integer maxRuntime = Integer.MIN_VALUE;
        Long maxRuntimeMemory = Long.MIN_VALUE;
        Integer score = 0;
        for (JudgeResult judgeResult : judgeResults) {
            if (judgeResult.getJudgeStatus() == JudgeStatus.ACCEPT) {
                acceptCount++;
            }
            score += judgeResult.getScore();
            maxRuntime = Math.max(maxRuntime, judgeResult.getExecutionTime());
            maxRuntimeMemory = Math.max(maxRuntimeMemory, judgeResult.getRuntimeMemory());
        }
        if (acceptCount == judgeResults.size()) {
            submitStatus = JudgeStatus.ACCEPT;
        } else if (acceptCount > 0) {
            submitStatus = JudgeStatus.PART_ACCEPT;
        } else if (judgeResults.get(0).getJudgeStatus() == JudgeStatus.COMPILE_ERROR) {
            submitStatus = JudgeStatus.COMPILE_ERROR;
        } else {
            submitStatus = JudgeStatus.WRONG_ANSWER;
        }
        this.saveBatch(judgeResults);
        submit.setStatus(submitStatus);
        submit.setRuntimeMemory(maxRuntimeMemory);
        submit.setRuntime(maxRuntime);
        submit.setScore(score);
        submitService.updateById(submit);
        //若是比赛的提交，则处理提交后的数据
        TaskSubmit taskSubmit = taskSubmitService.getOne(new QueryWrapper<TaskSubmit>().eq("submit_id", submit.getId()));
        if (taskSubmit != null) {
            taskRankService.scoreUpdate(submit, taskSubmit.getTaskId());
        }
    }

    private void fillData(SubmitJudgeResult submitJudgeResult, Submit submit) {
        BeanUtils.copyProperties(submit, submitJudgeResult);
        submitJudgeResult.setSubmitId(submit.getId());
    }
}
