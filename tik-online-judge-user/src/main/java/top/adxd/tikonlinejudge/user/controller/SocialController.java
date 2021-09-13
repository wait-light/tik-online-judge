package top.adxd.tikonlinejudge.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.user.entity.Social;
import top.adxd.tikonlinejudge.user.service.ISocialService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
@RestController
@RequestMapping("/user/social")
public class SocialController {
    
    @Autowired
    private ISocialService socialService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<Social> list = socialService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("/")
    public CommonResult save(@RequestBody Social entity) {
        return  socialService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("/")
    public CommonResult deleteBatch(@RequestBody Long[] ids){
        return socialService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return socialService.removeById(id) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Social entity){
        return socialService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        Social entity = socialService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

