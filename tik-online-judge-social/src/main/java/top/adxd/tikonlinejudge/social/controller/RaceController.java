package top.adxd.tikonlinejudge.social.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.service.IRaceService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/social/race")
public class RaceController {
    @Autowired
    private IRaceService raceService;

    @GetMapping("/survey/{raceId}")
    public CommonResult survey(@PathVariable("raceId") Long raceId) {
        return raceService.survey(raceId);
    }
}
