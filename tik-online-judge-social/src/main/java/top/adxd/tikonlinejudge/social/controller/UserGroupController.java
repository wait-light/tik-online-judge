package top.adxd.tikonlinejudge.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Group;
import top.adxd.tikonlinejudge.social.service.IUserGroupService;

@RestController
@RequestMapping("/social/user-group")
public class UserGroupController {
    @Autowired
    private IUserGroupService userGroupService;

    @PostMapping("")
    public CommonResult addGroup(@RequestBody Group group) {
        return userGroupService.addGroup(group);
    }
}
