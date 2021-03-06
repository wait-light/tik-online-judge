package top.adxd.tikonlinejudge.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.auth.api.IAuthorizationService;
import top.adxd.tikonlinejudge.auth.service.impl.PermissionCacheServiceImpl;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
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
@RequestMapping("/auth/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private PermissionCacheServiceImpl permissionCacheService;

    @GetMapping("/tree/")
    public CommonResult treeMenu() {
        return CommonResult.success().singleData(menuService.menuTree());
    }

    @GetMapping("/directory")
    public CommonResult userDirectory() {
        return CommonResult.success().singleData(menuService.userDirectoryMenuTree(UserInfoUtil.getUid()));
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<Menu> list = menuService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Menu entity) {
        return menuService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return menuService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return menuService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Menu entity) {
        if (entity.getId() == entity.getParentId()) {
            return CommonResult.error("父子节点不能相同");
        }
        return menuService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        Menu entity = menuService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

    @GetMapping("/interfaces")
    public CommonResult userInterfaces() {
        Long uid = UserInfoUtil.getUid();
        return CommonResult
                .success()
                .singleData(permissionCacheService.userAuthorization(uid));
    }

}

