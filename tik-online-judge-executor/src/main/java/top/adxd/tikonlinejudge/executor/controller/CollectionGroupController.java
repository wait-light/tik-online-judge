package top.adxd.tikonlinejudge.executor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.CollectionGroup;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.service.ICollectionGroupService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-17
 */
@RestController
@RequestMapping("/executor/collection-group")
public class CollectionGroupController {

    @Autowired
    private ICollectionGroupService collectionGroupService;


    @GetMapping("/{groupId}/collections")
    public CommonResult groupCollections(@PathVariable("groupId") Long groupId) {
        return collectionGroupService.groupCollections(groupId);
    }

    @GetMapping("/problems/{groupId}")
    public CommonResult problems(@PathVariable("groupId") Long groupId) {
        return CommonResult.success().listData(collectionGroupService.groupProblems(groupId));
    }

    @PostMapping("/problems/{groupId}")
    public CommonResult problemAdd(@PathVariable("groupId") Long groupId, @RequestBody Problem problem) {
        return collectionGroupService.addProblem(groupId, problem);
    }

    @PutMapping("/problems/{groupId}")
    public CommonResult problemUpdate(@PathVariable("groupId") Long groupId, @RequestBody Problem problem) {
        return collectionGroupService.updateProblem(groupId, problem);
    }

    @DeleteMapping("/problems/{groupId}/{problemId}")
    public CommonResult problemDelete(@PathVariable("groupId") Long groupId, @PathVariable("problemId") Long problemId) {
        return collectionGroupService.deleteProblem(groupId, problemId) ?
                CommonResult.success("移除成功") :
                CommonResult.error("移除失败");
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<CollectionGroup> list = collectionGroupService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody CollectionGroup entity) {
        return collectionGroupService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return collectionGroupService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return collectionGroupService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody CollectionGroup entity) {
        return collectionGroupService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        CollectionGroup entity = collectionGroupService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

