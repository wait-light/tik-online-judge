package top.adxd.tikonlinejudge.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.auth.entity.UserRole;
import top.adxd.tikonlinejudge.auth.service.IUserRoleService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
@RestController
@RequestMapping("/auth/user-role")
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("/unroled/{uid}")
    public CommonResult unroled(@PathVariable("uid") Long uid) {
        return userRoleService.userUnRoledRole(uid);
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<UserRole> list = userRoleService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody UserRole entity) {
        return userRoleService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return userRoleService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{uid}/{roleId}")
    public CommonResult delete(@PathVariable("uid") Long uid, @PathVariable("roleId") Long roleId) {
        return userRoleService.removeUserRole(uid, roleId);
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody UserRole entity) {
        return userRoleService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        UserRole entity = userRoleService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

