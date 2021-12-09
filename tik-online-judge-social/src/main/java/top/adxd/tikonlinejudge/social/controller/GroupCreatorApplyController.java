package top.adxd.tikonlinejudge.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.entity.GroupCreatorApply;
import top.adxd.tikonlinejudge.social.service.IGroupCreatorApplyService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/social/group-creator-apply")
public class GroupCreatorApplyController {

    @Autowired
    private IGroupCreatorApplyService groupCreatorApplyService;

    @PostMapping("/apply")
    public CommonResult apply(@RequestBody GroupCreatorApply entity) {
        return groupCreatorApplyService.apply(entity) ?
                CommonResult.success("成功发起申请") :
                CommonResult.error("发起申请失败");
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<GroupCreatorApply> list = groupCreatorApplyService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody GroupCreatorApply entity) {
        return groupCreatorApplyService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return groupCreatorApplyService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return groupCreatorApplyService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody GroupCreatorApply entity) {
        return groupCreatorApplyService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        GroupCreatorApply entity = groupCreatorApplyService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}
