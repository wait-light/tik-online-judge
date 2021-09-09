package top.adxd.tikonlinejudge.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.user.entity.UmsMenu;
import top.adxd.tikonlinejudge.user.service.IUmsMenuService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/user/ums-menu")
public class UmsMenuController {
    
    @Autowired
    private IUmsMenuService umsMenuService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<UmsMenu> list = umsMenuService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("/save")
    public CommonResult save(@RequestBody UmsMenu entity) {
        return  umsMenuService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestBody Long[] ids){
        return umsMenuService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody UmsMenu entity){
        return umsMenuService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/info/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        UmsMenu entity = umsMenuService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

