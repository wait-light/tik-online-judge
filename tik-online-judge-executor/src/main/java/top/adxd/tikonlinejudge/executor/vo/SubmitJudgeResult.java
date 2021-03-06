package top.adxd.tikonlinejudge.executor.vo;

import lombok.Data;
import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SubmitJudgeResult implements Serializable {
    private Long submitId;
    private LocalDateTime createTime;
    private JudgeStatus status;
    private List<JudgeResult> judgeResults;
    private Long runtime;
    private Long runtimeMemory;
}
