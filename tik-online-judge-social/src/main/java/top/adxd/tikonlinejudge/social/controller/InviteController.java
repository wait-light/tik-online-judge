package top.adxd.tikonlinejudge.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.dto.ConsumeInvite;
import top.adxd.tikonlinejudge.social.dto.InviteInfo;
import top.adxd.tikonlinejudge.social.entity.Invite;
import top.adxd.tikonlinejudge.social.service.IInviteService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 邀请信息表 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/social/invite")
public class InviteController {

    @Autowired
    private IInviteService inviteService;

    @GetMapping("/myInvited")
    public CommonResult myInvited() {
        //TODO uid
        Long uid = 1L;
        return CommonResult.success().singleData(inviteService.inviteMessage(uid));
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<Invite> list = inviteService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody InviteInfo entity) {
        return inviteService.invite(entity);
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return inviteService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return inviteService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long inviteId,@RequestBody ConsumeInvite consumeInvite) {
        return inviteService.consumeInviteInfo(inviteId, consumeInvite.getInviteStatus());
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id) {
        Invite entity = inviteService.getById(id);
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

}

