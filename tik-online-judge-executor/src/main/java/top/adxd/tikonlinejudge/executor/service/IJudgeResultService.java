package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.vo.SubmitJudgeResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-24
 */
public interface IJudgeResultService extends IService<JudgeResult> {
    SubmitJudgeResult submitJudgeResults(Long submitId);
    SubmitJudgeResult lastSubmitResults(Long problemId, Long userid);
    List<SubmitJudgeResult> problemSubmitsResults(Long problemId,Long userid);
    void updateCommitAfterJudge(List<JudgeResult> judgeResults, Submit submit);
}
