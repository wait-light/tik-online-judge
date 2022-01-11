package top.adxd.tikonlinejudge.common.annotation.processor;

import java.util.List;

public interface IRoleProcessor extends IProcessAble{
    void setPendingClass(List<Class> pendingClass);
}
