package top.adxd.tikonlinejudge.executor.service;

import top.adxd.tikonlinejudge.executor.entity.JudgeResult;
import top.adxd.tikonlinejudge.executor.entity.Submit;

import java.util.List;

public interface ICodeJudge {
    List<JudgeResult> judge(Submit submit);
}