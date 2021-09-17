package top.adxd.tikonlinejudge.executor.vo;

/**
 * 执行状态
 */
public enum ExecuteStatus {
    /**
     * 执行成功
     */
    SUCCESS,
    /**
     * 编译错误
     */
    COMPILE_ERROR,
    /**
     * 运行出错
     */
    RUNTIME_ERROR,
    /**
     * 运行超时
     */
    TIME_LIME_EXCEEDED,
    /**
     * 系统错误
     */
    SYSTEM_ERROR,

}
