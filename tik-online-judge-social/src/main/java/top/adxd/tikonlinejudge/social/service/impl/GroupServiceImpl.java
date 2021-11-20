package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.exeption.CommonException;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.executor.api.IGroupCollectionService;
import top.adxd.tikonlinejudge.social.entity.Group;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.entity.GroupUserType;
import top.adxd.tikonlinejudge.social.mapper.GroupMapper;
import top.adxd.tikonlinejudge.social.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Autowired
    private IGroupUserService groupUserService;
    @DubboReference
    private IGroupCollectionService groupCollectionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Group group) {
        //储存群组信息
        LocalDateTime now = LocalDateTime.now();
        group.setCreateTime(now);
        Long uid = UserInfoUtil.getUid();
        group.setCreateUserId(uid);
        group.setStatus(1);
        boolean saveGroup = super.save(group);

        //储存用户信息
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(group.getId());
        groupUser.setUserType(GroupUserType.MASTER);
        groupUser.setUid(uid);
        boolean saveGroupUser = groupUserService.save(groupUser);

        //储存群组问题集信息
        boolean collection = groupCollectionService.collectionGroupCreate(group.getId(), group.getName());

        return saveGroup && saveGroupUser & collection;
    }

    @Override
    public List<Group> groups(Long uid) {
        List<Long> groupIds = groupUserService.list(new QueryWrapper<GroupUser>()
                        .eq("uid", uid)
                        .select("group_id"))
                .stream()
                .map((item -> item.getGroupId()))
                .collect(Collectors.toList());
        if (groupIds.size() <= 0) {
            return new ArrayList<>();
        }
        return listByIds(groupIds);
    }
}
