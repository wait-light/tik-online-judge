package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.social.api.IUserGroupService;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DubboService(interfaceClass = IUserGroupService.class)
public class UserGroupApiServiceImpl implements IUserGroupService {
    @Autowired
    private IGroupUserService groupUserService;

    @Override
    public List<Long> userGroups(Long uid) {
        if (uid == null) {
            return new ArrayList<>();
        }
        return groupUserService.list(new QueryWrapper<GroupUser>()
                        .eq("uid", uid)
                        .select("group_id"))
                .stream()
                .map(GroupUser::getGroupId)
                .collect(Collectors.toList());
    }
}
