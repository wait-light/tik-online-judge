package top.adxd.tikonlinejudge.executor.vo;

public class ExecuteCMDResult{
    private String successOutput;
    private String errorOutput;
    private boolean success;
    private Long executeTime;

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
}
