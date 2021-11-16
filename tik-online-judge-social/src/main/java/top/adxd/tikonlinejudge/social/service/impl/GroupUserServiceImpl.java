package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.mapper.GroupUserMapper;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
