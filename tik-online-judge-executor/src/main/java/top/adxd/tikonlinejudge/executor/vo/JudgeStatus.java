package top.adxd.tikonlinejudge.executor.vo;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum JudgeStatus implements IEnum<Integer> {
    /**
     * 评判中
     */
    JUDGING(1, "中"),

    /**
     * 编译错误
     */
    COMPILE_ERROR(2, ""),

    /**
     * 运行出错
     */
    RUNTIME_ERROR(3, ""),

    /**
     * 运行超时
     */
    TIME_LIME_EXCEEDED(4, ""),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(5, ""),

    /**
     * 评判成功
     */
    ACCEPT(6, ""),

    /**
     * 格式错误
     */
    PRESENTATION_ERROR(7, ""),

    /**
     * 结果错误
     */
    WRONG_ANSWER(8, "");
    private Integer value;
    private String message;

    JudgeStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }


    @Override
    public Integer getValue() {
        return this.value;
    }
}
