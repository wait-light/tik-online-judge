package top.adxd.tikonlinejudge.common.annotation.processor;

import top.adxd.tikonlinejudge.common.annotation.IRoleConfig;

import java.util.List;

public interface IRoleConfigProcessor extends IProcessAble{
    void setPendingConfig(List<IRoleConfig> pendingClass);
}
