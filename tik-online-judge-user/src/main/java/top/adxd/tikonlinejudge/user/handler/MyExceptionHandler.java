package top.adxd.tikonlinejudge.user.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wait_light
 * @create 2021/9/11
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return e.getMessage();
    }
}
