package top.adxd.tikonlinejudge.executor.config.docker;

/**
 * 可编译的语言
 */
public interface ICompileAbleConfig {
    public static final String COMPILE_TIME ="/compile.time";
    public static final String COMPILE_INFO ="/compile.info";
    /**
     * @return 编译时间输出的文件路径
     */
    String getCompileTime();

    /**
     * @return 编译信息输出的路径
     */
    String getCompileInfo();
}
