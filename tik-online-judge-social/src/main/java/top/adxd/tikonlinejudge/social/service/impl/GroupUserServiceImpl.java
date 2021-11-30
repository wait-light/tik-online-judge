package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.single.GroupUserType;
import top.adxd.tikonlinejudge.social.mapper.GroupUserMapper;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {


    @Override
    public boolean isInGroup(Long uid, Long groupId) {
        return getOne(new QueryWrapper<GroupUser>()
                .eq("uid", uid)
                .eq("group_id", groupId)
                .select("id")) != null;
    }

    @Override
    public CommonResult userList(Long groupId) {
        List<GroupUser> users = list(new QueryWrapper<GroupUser>()
                .eq("group_id", groupId));
        return CommonResult.success().singleData(users);
    }

    @Override
    public CommonResult removeUser(Long groupId, Long uid) {
        Long currentUser = UserInfoUtil.getUid();
        GroupUser user = getOne(new QueryWrapper<GroupUser>()
                .eq("uid", currentUser)
                .eq("group_id", groupId));
        GroupUserType userType = user.getUserType();
        if (userType == GroupUserType.MASTER) {
            if (uid == user.getUid()) {
                return CommonResult.permissionDeny("禁止访问");
            } else {
                return remove(new QueryWrapper<GroupUser>()
                        .eq("uid", uid)
                        .eq("group_id", groupId)) ?
                        CommonResult.success("成功移除") :
                        CommonResult.error("移除失败");
            }
        }
        return CommonResult.permissionDeny("禁止访问");
    }

    @Override
    public GroupUserType getUserType(Long groupId, Long uid) {
        GroupUser groupUser = getOne(new QueryWrapper<GroupUser>()
                .eq("uid", uid)
                .eq("group_id", groupId));
        return groupUser != null ? groupUser.getUserType() : null;
    }
}
