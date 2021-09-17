package top.adxd.tikonlinejudge.executor.exception.runtime;

public class CmdNotFoundException extends RuntimeException{
    public CmdNotFoundException(String message){
        super(message);
    }
}
