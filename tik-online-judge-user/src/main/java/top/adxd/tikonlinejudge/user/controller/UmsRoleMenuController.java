package top.adxd.tikonlinejudge.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.user.entity.UmsRoleMenu;
import top.adxd.tikonlinejudge.user.service.IUmsRoleMenuService;
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
@RequestMapping("/user/ums-role-menu")
public class UmsRoleMenuController {
    
    @Autowired
    private IUmsRoleMenuService umsRoleMenuService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<UmsRoleMenu> list = umsRoleMenuService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("/save")
    public CommonResult save(@RequestBody UmsRoleMenu entity) {
        return  umsRoleMenuService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestBody Long[] ids){
        return umsRoleMenuService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody UmsRoleMenu entity){
        return umsRoleMenuService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/info/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        UmsRoleMenu entity = umsRoleMenuService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

