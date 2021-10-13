package top.adxd.tikonlinejudge.executor.vo;

import java.io.Serializable;

/**
 * 执行输入
 */
public class ExecuteInput implements Serializable {
    public String input;
    public Long time;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
