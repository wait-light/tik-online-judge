package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.InviteInfo;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import top.adxd.tikonlinejudge.social.single.GroupUserType;
import top.adxd.tikonlinejudge.social.entity.Invite;
import top.adxd.tikonlinejudge.social.single.InviteStatus;
import top.adxd.tikonlinejudge.social.mapper.InviteMapper;
import top.adxd.tikonlinejudge.social.service.IGroupUserService;
import top.adxd.tikonlinejudge.social.service.IInviteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 邀请信息表 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-16
 */
@Service
public class InviteServiceImpl extends ServiceImpl<InviteMapper, Invite> implements IInviteService {

    @Autowired
    private IGroupUserService groupUserService;
    @DubboReference
    private IUserInfoService userInfoService;

    @Override
    public List<Invite> inviteMessage(Long uid) {
        return list(new QueryWrapper<Invite>()
                .eq("invitees", uid)
                .eq("status", InviteStatus.UNTREATED.getValue())
                .orderByDesc("create_time"));
    }

    @Override
    public CommonResult invite(InviteInfo inviteInfo) {
        Long uid = UserInfoUtil.getUid();
        boolean hasInvitePower = groupUserService.getOne(new QueryWrapper<GroupUser>()
                .eq("uid", uid)
                .eq("group_id", inviteInfo.getGroupId())
                .eq("user_type", GroupUserType.MASTER.getValue())) != null;
        if (!hasInvitePower) {
            return CommonResult.permissionDeny("权限不足");
        }
        SafeUserDto invitees = userInfoService.getUser(inviteInfo.getInvitees());
        if (invitees == null) {
            return CommonResult.error("用户不存在");
        }
        boolean inGroup = groupUserService.isInGroup(invitees.getUid(), inviteInfo.getGroupId());
        if (inGroup) {
            return CommonResult.error("用户已加入群聊");
        }
        Invite invite = new Invite();
        LocalDateTime now = LocalDateTime.now();
        invite.setCreateTime(now);
        invite.setInitiator(uid);
        invite.setInvitees(invitees.getUid());
        invite.setGroupId(inviteInfo.getGroupId());
        invite.setStatus(InviteStatus.UNTREATED);
        return save(invite) ? CommonResult.success("成功发出邀请") : CommonResult.error("邀请失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult consumeInviteInfo(Long inviteId, InviteStatus inviteStatus) {
        Long uid = UserInfoUtil.getUid();
        Invite inviteInfo = getById(inviteId);
        if (inviteInfo == null) {
            return CommonResult.error("消息有误");
        }
        if (inviteInfo.getInvitees() != uid || inviteInfo.getStatus() != InviteStatus.UNTREATED) {
            return CommonResult.error("处理非法");
        }
        boolean inGroup = groupUserService.isInGroup(uid, inviteInfo.getGroupId());
        if (inGroup) {
            inviteInfo.setStatus(InviteStatus.ACCEPT);
            updateById(inviteInfo);
            return CommonResult.success("处理成功");
        }

        LocalDateTime now = LocalDateTime.now();
        inviteInfo.setUpdateTime(now);
        inviteInfo.setStatus(inviteStatus);
        updateById(inviteInfo);
        if (inviteInfo.getStatus() == InviteStatus.ACCEPT) {
            GroupUser groupUser = new GroupUser();
            groupUser.setUid(uid);
            groupUser.setUserType(GroupUserType.COMMON);
            groupUser.setGroupId(inviteInfo.getGroupId());
            groupUserService.save(groupUser);
        }

        return CommonResult.success("处理成功");
    }
}
