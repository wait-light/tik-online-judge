package top.adxd.tikonlinejudge.executor.config;

/**
 * @author wait-light
 * @date 2021/10/23 下午4:45
 */
public class LanguageConfig {
    private boolean open = false;
    private String judgeImplementClass;

    public String getJudgeImplementClass() {
        return judgeImplementClass;
    }

    public void setJudgeImplementClass(String judgeImplementClass) {
        this.judgeImplementClass = judgeImplementClass;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
