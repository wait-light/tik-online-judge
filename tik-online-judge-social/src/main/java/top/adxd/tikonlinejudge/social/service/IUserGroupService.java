package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.Group;

public interface IUserGroupService {
    CommonResult addGroup(Group group);
}
