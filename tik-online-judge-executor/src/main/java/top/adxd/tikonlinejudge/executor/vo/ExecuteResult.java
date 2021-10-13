package top.adxd.tikonlinejudge.executor.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 运行结果类
 */
public class ExecuteResult implements Serializable {
    public ExecuteStatus executeStatus;
    public Boolean success;
    public String outputString;
    public Long executeTime;

    public ExecuteResult() {
    }

    public ExecuteResult(ExecuteStatus executeStatus, Boolean success, String outputString, Long executeTime) {
        this.executeStatus = executeStatus;
        this.success = success;
        this.outputString = outputString;
        this.executeTime = executeTime;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public ExecuteStatus getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(ExecuteStatus executeStatus) {
        this.executeStatus = executeStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    public static ExecuteResult parse(ExecuteCMDResult executeCMDResult) {
        ExecuteResult executeResult = new ExecuteResult();
        if (executeCMDResult == null) {
            return executeResult;
        }
        executeResult.setExecuteTime(executeCMDResult.getExecuteTime());
        if (executeCMDResult.isSuccess()) {
            executeResult.setSuccess(true);
            executeResult.setExecuteStatus(ExecuteStatus.SUCCESS);
            executeResult.setOutputString(executeCMDResult.getSuccessOutput());
        } else {
            executeResult.setSuccess(false);
            if (-100 == executeCMDResult.getExitCode()) {
                executeResult.setExecuteStatus(ExecuteStatus.COMPILE_ERROR);
            } else {
                executeResult.setExecuteStatus(ExecuteStatus.RUNTIME_ERROR);
            }
            executeResult.setOutputString(executeCMDResult.getErrorOutput());
        }
        return executeResult;
    }


    @Override
    public String toString() {
        return "ExecuteResult{" +
                "executeStatus=" + executeStatus +
                ", success=" + success +
                ", outputString='" + outputString + '\'' +
                ", executeTime=" + executeTime +
                '}';
    }
}
