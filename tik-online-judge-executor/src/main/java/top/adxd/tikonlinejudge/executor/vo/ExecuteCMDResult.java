package top.adxd.tikonlinejudge.executor.vo;

import java.io.Serializable;

public class ExecuteCMDResult implements Serializable {
    private String successOutput;
    private String errorOutput;
    private boolean success;
    private Long executeTime;
    private int exitCode;

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public String getSuccessOutput() {
        return successOutput;
    }

    public void setSuccessOutput(String successOutput) {
        this.successOutput = successOutput;
    }

    public String getErrorOutput() {
        return errorOutput;
    }

    public void setErrorOutput(String errorOutput) {
        this.errorOutput = errorOutput;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ExecuteCMDResult{" +
                "successOutput='" + successOutput + '\'' +
                ", errorOutput='" + errorOutput + '\'' +
                ", success=" + success +
                ", executeTime=" + executeTime +
                ", exitCode=" + exitCode +
                '}';
    }
}
