package top.adxd.tikonlinejudge.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.entity.Group;
import top.adxd.tikonlinejudge.social.service.IGroupService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/social/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/myGroups")
    public CommonResult myGroups() {
        List<Group> groups = groupService.groups(UserInfoUtil.getUid());
        return CommonResult.success().singleData(groups);
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<Group> list = groupService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Group entity) {
        return groupService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return groupService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return groupService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Group entity) {
        return groupService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        Group entity = groupService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

