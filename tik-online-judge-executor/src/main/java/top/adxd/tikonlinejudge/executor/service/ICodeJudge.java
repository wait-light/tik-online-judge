package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.vo.JudgeResult;

import javax.validation.Valid;
import java.util.List;

public interface ICodeJudge {
    List<JudgeResult> judge(Submit submit);
}
