package top.adxd.tikonlinejudge.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * @author wait-light
 * @date 2021/11/4.
 */
@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public CommonResult commonHandler(Exception exception) {
        logger.error(exception.getLocalizedMessage());
        return CommonResult.error("服务器内部错误");
    }
}
