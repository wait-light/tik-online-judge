package top.adxd.tikonlinejudge.executor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.adxd.tikonlinejudge.executor.exception.runtime.ParseException;
import top.adxd.tikonlinejudge.executor.vo.ExecuteResult;
import top.adxd.tikonlinejudge.executor.single.ExecuteStatus;
import top.adxd.tikonlinejudge.executor.single.JudgeStatus;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2021-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_judge_result")
public class JudgeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应的提交id
     */
    private Long submitId;

    /**
     * 提交状态
     */
    private JudgeStatus judgeStatus;

    /**
     * 执行时常
     */
    private Integer executionTime;

    /**
     * 错误信息
     */
    private String errorOutput;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 运行时内存
     */
    private Long runtimeMemory;

    /**
     * 分数
     */
    @TableField(exist = false)
    private Integer score;

    public static JudgeResult parse(ExecuteResult executeResult, Long submitId, String output, Long executionTime) {
        if (null == executeResult) {
            throw new ParseException("executeResult can not be null");
        }
        if (null == submitId) {
            throw new ParseException("submitId can not be null");
        }
        if (null == executionTime || executionTime <= 0) {
            throw new ParseException("unsupported executionTime");
        }
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setSubmitId(submitId);
        if (executeResult.success) {
            if (null != output) {
                //结果是否正确
                boolean correct = output.equals(executeResult.getOutputString());
                judgeResult.setSuccess(correct);
                if (correct) {
                    judgeResult.setJudgeStatus(JudgeStatus.ACCEPT);
                    //运行超时
                    if (executeResult.getExecuteTime() > executionTime) {
                        judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
                    }
                } else {
                    String outputString = executeResult.getOutputString();
                    boolean isFormatWrong = false;
                    //格式错误判断
                    if (outputString.endsWith("\r\n") || outputString.endsWith("\n\r")) {
                        String substring = outputString.substring(0,outputString.length() - 2);
                        if (substring.equals(output)) {
                            isFormatWrong = true;
                        }
                    } else if (outputString.endsWith("\r") || outputString.endsWith("\n")) {
                        String substring = outputString.substring(0,outputString.length() - 1);
                        if (substring.equals(output)) {
                            isFormatWrong = true;
                        }
                    } else if (output.endsWith("\r\n") || output.endsWith("\n\r")) {
                        String substring = output.substring(0,output.length() - 2);
                        if (substring.equals(outputString)) {
                            isFormatWrong = true;
                        }
                    } else if (output.endsWith("\n") || output.endsWith("\r")) {
                        String substring = output.substring(0,output.length() - 1);
                        if (substring.equals(outputString)) {
                            isFormatWrong = true;
                        }
                    }
                    if (isFormatWrong) {
                        judgeResult.setJudgeStatus(JudgeStatus.PRESENTATION_ERROR);
                    } else {
                        judgeResult.setJudgeStatus(JudgeStatus.WRONG_ANSWER);
                    }
                }
            } else {
                judgeResult.setSuccess(true);
            }
        } else {
            if (executeResult.getExecuteTime().equals(Long.MAX_VALUE)) {
                judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
            } else if (ExecuteStatus.COMPILE_ERROR.equals(executeResult.getExecuteStatus())) {
                judgeResult.setJudgeStatus(JudgeStatus.COMPILE_ERROR);
            } else if (executeResult.getExecuteTime() > executionTime) {
                judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
            } else {
                judgeResult.setJudgeStatus(JudgeStatus.RUNTIME_ERROR);
            }
            judgeResult.setSuccess(false);
        }
        if (!executeResult.success){
            judgeResult.setErrorOutput(executeResult.getOutputString());
        }
        judgeResult.setExecutionTime(executeResult.getExecuteTime());
        return judgeResult;
    }

    @Override
    public String toString() {
        return "JudgeResult{" +
                "submitId=" + submitId +
                ", judgeStatus=" + judgeStatus +
                ", success=" + success +
                ", executionTime=" + executionTime +
                ", output='" + errorOutput + '\'' +
                '}';
    }

}
