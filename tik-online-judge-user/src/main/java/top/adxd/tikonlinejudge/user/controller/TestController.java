package top.adxd.tikonlinejudge.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.common.util.PageUtils;

/**
 * @author wait_light
 * @create 2021/9/6
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/")
    public String aaa(){
        PageUtils.makePage();
        return "aa";
    }
}
