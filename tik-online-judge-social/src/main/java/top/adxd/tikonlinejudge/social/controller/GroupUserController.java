package top.adxd.tikonlinejudge.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;
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
@RequestMapping("/social/group-user")
public class GroupUserController {

    @Autowired
    private IGroupUserService groupUserService;

    @GetMapping("/userlist/{groupId}")
    public CommonResult userList(@PathVariable("groupId") Long groupId) {
        return groupUserService.userList(groupId);
    }

    @DeleteMapping("/{groupId}/{uid}")
    public CommonResult userRemove(@PathVariable("groupId") Long groupId,@PathVariable("uid") Long uid){
        return groupUserService.removeUser(groupId,uid);
    }

    @GetMapping("/{groupId}")
    public CommonResult groupUserType(@PathVariable("groupId") Long groupId,@RequestHeader("uid") Long uid){
        return CommonResult.success().singleData(groupUserService.getUserType(groupId,uid));
    }

//    @GetMapping("/list")
//    public CommonResult list() {
//        PageUtils.makePage();
//        List<GroupUser> list = groupUserService.list();
//        return CommonResult.success().listData(list);
//    }

//    @PostMapping("")
//    public CommonResult save(@RequestBody GroupUser entity) {
//        return groupUserService.save(entity) ?
//                CommonResult.success().setMsg("添加成功") :
//                CommonResult.error().setMsg("添加失败");
//    }
//
//    @DeleteMapping("")
//    public CommonResult deleteBatch(@RequestBody Long[] ids) {
//        return groupUserService.removeByIds(Arrays.asList(ids)) ?
//                CommonResult.success().setMsg("删除成功") :
//                CommonResult.error().setMsg("删除失败");
//    }
//
//    @DeleteMapping("/{id}")
//    public CommonResult delete(@PathVariable("id") Long id) {
//        return groupUserService.removeById(id) ?
//                CommonResult.success().setMsg("删除成功") :
//                CommonResult.error().setMsg("删除失败");
//    }
//
//    @PutMapping("/{id}")
//    public CommonResult update(@RequestBody GroupUser entity) {
//        return groupUserService.updateById(entity) ?
//                CommonResult.success().setMsg("更新成功") :
//                CommonResult.error().setMsg("更新失败");
//    }
//
//    @GetMapping("/{id}")
//    public CommonResult info(@PathVariable("id") Long id) {
//        GroupUser entity = groupUserService.getById(id);
//        return entity != null ?
//                CommonResult.success().singleData(entity) :
//                CommonResult.error();
//    }

}

