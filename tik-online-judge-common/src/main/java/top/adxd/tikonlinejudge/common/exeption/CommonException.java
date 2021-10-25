package top.adxd.tikonlinejudge.common.exeption;

/**
 * @author light
 */
public class CommonException extends RuntimeException {
    public CommonException(String msg){
        super(msg);
    }
    public CommonException(){
        super();
    }
}
