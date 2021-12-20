package top.adxd.tikonlinejudge.common.exeption;

public class RoleExcludeException extends RuntimeException{
    public RoleExcludeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleExcludeException(String message) {
        super(message);
    }
}
