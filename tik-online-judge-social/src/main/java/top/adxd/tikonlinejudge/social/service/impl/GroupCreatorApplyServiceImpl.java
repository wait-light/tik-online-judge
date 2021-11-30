package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.social.entity.GroupCreatorApply;
import top.adxd.tikonlinejudge.social.mapper.GroupCreatorApplyMapper;
import top.adxd.tikonlinejudge.social.service.IGroupCreatorApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.social.single.GroupCreatorApplyStatus;

import java.time.LocalDateTime;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-30
 */
@Service
public class GroupCreatorApplyServiceImpl extends ServiceImpl<GroupCreatorApplyMapper, GroupCreatorApply> implements IGroupCreatorApplyService {

    @Override
    public boolean apply(GroupCreatorApply groupCreatorApply) {
        Long uid = UserInfoUtil.getUid();
        boolean hasPower = getOne(new QueryWrapper<GroupCreatorApply>()
                .eq("status", GroupCreatorApplyStatus.ACCEPT.getValue())
                .or()
                .isNull("status")
                .or()
                .eq("status", GroupCreatorApplyStatus.UN_JUDGE.getValue())
                .and((u) -> u.eq("uid", uid))
                .last("limit 1")
                .select("id")) != null;
        if (hasPower) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        groupCreatorApply.setCreateTime(now);
        groupCreatorApply.setStatus(GroupCreatorApplyStatus.UN_JUDGE);
        groupCreatorApply.setUid(uid);
        groupCreatorApply.setJudgeUid(null);
        groupCreatorApply.setUpdateTime(null);
        return save(groupCreatorApply);
    }
}
