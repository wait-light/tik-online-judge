package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.mapper.JudgeResultMapper;
import top.adxd.tikonlinejudge.executor.service.IJudgeResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.ISubmitService;
import top.adxd.tikonlinejudge.executor.vo.SubmitJudgeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public SubmitJudgeResult submitJudgeResults(Long submitId) {
        List<JudgeResult> submitResults = baseMapper.selectList(new QueryWrapper<JudgeResult>().eq("submit_id", submitId));
        SubmitJudgeResult submitJudgeResult = new SubmitJudgeResult();
        submitJudgeResult.setJudgeResults(submitResults);
        return submitJudgeResult;

    }

    @Override
    public SubmitJudgeResult lastSubmitResults(Long problemId, Long userid) {
        Submit lastSubmit = submitService.getOne(new QueryWrapper<Submit>()
                .select("id", "create_time", "status")
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
                .select("id", "create_time", "status")
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

    private void fillData(SubmitJudgeResult submitJudgeResult, Submit submit) {
        submitJudgeResult.setSubmitId(submit.getId());
        submitJudgeResult.setCreateTime(submit.getCreateTime());
        submitJudgeResult.setStatus(submit.getStatus());
    }
}