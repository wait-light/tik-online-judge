package top.adxd.tikonlinejudge.executor.vo;

import top.adxd.tikonlinejudge.executor.exception.runtime.ParseException;

public class JudgeResult {
    public Long submitId;
    public JudgeStatus judgeStatus;
    public Boolean success;
    public Long executionTime;
    public String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public JudgeStatus getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(JudgeStatus judgeStatus) {
        this.judgeStatus = judgeStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Long submitId) {
        this.submitId = submitId;
    }

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
                    if (judgeResult.getExecutionTime() > executionTime) {
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
            } else if (judgeResult.getExecutionTime() > executionTime) {
                judgeResult.setJudgeStatus(JudgeStatus.TIME_LIME_EXCEEDED);
            } else {
                judgeResult.setJudgeStatus(JudgeStatus.RUNTIME_ERROR);
            }
            judgeResult.setSuccess(false);
        }
        judgeResult.setOutput(executeResult.getOutputString());
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
                ", output='" + output + '\'' +
                '}';
    }
}
