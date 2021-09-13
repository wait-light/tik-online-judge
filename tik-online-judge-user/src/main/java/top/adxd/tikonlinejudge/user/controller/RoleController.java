package top.adxd.tikonlinejudge.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.user.entity.Role;
import top.adxd.tikonlinejudge.user.service.IRoleService;
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
@RequestMapping("/user/role")
public class RoleController {
    
    @Autowired
    private IRoleService roleService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<Role> list = roleService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("/")
    public CommonResult save(@RequestBody Role entity) {
        return  roleService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("/")
    public CommonResult deleteBatch(@RequestBody Long[] ids){
        return roleService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return roleService.removeById(id) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Role entity){
        return roleService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        Role entity = roleService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

