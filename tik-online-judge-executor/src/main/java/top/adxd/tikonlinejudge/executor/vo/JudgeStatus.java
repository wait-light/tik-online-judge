package top.adxd.tikonlinejudge.executor.vo;

public enum JudgeStatus {
    /**
     * 评判中
     */
    JUDGING,
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
    /**
     * 评判成功
     */
    ACCEPT,
    /**
     * 格式错误
     */
    PRESENTATION_ERROR
}
