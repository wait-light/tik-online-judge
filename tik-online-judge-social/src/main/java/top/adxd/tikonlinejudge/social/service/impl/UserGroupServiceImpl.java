package top.adxd.tikonlinejudge.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Group;
import top.adxd.tikonlinejudge.social.service.IGroupService;
import top.adxd.tikonlinejudge.social.service.IUserGroupService;

import java.time.LocalDateTime;

@Service
public class UserGroupServiceImpl implements IUserGroupService {
    @Autowired
    private IGroupService groupService;
    @Override
    public CommonResult addGroup(Group group) {
        //todo 权限校验
        Long uid = 1L;
        LocalDateTime now = LocalDateTime.now();
        group.setCreateUserId(uid);
        group.setCreateTime(now);
        return groupService.save(group)?
                CommonResult.success("创建成功").add("groupId",group.getId()):
                CommonResult.error("创建失败");
    }
}
