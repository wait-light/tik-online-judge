package top.adxd.tikonlinejudge.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.user.entity.RoleMenu;
import top.adxd.tikonlinejudge.user.service.IRoleMenuService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.user.service.IUserRoleMenuService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-13
 */
@RestController
@RequestMapping("/user/role-menu")
public class RoleMenuController {

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleMenuService userRoleMenuService;

    @GetMapping("/menu/{id}")
    public CommonResult roleMenus(@PathVariable("id") Long roleId) {
        return CommonResult.success().singleData(roleMenuService.userMenus(roleId));
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<RoleMenu> list = roleMenuService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody RoleMenu entity) {
        return roleMenuService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return roleMenuService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return roleMenuService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody RoleMenu entity) {
        return roleMenuService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        RoleMenu entity = roleMenuService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

