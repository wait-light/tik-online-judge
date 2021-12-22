package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.dto.InviteInfo;
import top.adxd.tikonlinejudge.social.entity.Invite;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.social.single.InviteStatus;

import java.util.List;

/**
 * <p>
 * 邀请信息表 服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-16
 */
public interface IInviteService extends IService<Invite> {
    List<Invite> inviteMessage(Long uid);

    /**
     * 发起邀请信息
     * @param invite 被邀请人与邀请的群组
     * @return 邀请结果
     */
    CommonResult invite(InviteInfo invite);

    /**
     * 处理邀请信息
     * @param inviteId 信息id
     * @param inviteStatus 处理状态
     * @return 处理结果
     */
    CommonResult consumeInviteInfo(Long inviteId, InviteStatus inviteStatus);
}
