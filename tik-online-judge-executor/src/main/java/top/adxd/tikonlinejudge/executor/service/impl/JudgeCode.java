package top.adxd.tikonlinejudge.executor.service.impl;

import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.vo.JudgeResult;

public interface JudgeCode {
    public JudgeResult judge(Submit submit);
}
