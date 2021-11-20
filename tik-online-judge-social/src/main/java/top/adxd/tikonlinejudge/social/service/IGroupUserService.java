package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.social.entity.GroupUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
public interface IGroupUserService extends IService<GroupUser> {
    boolean isInGroup(Long uid, Long group);

    /**
     * 返回群组用户id，用户类型
     *
     * @param groupId 群组id
     * @return 群组用户id
     */
    CommonResult userList(Long groupId);

    /**
     * 移除群组中的用户
     *
     * @param groupId 群组id
     * @param uid     用户id
     * @return 操作结果
     */
    CommonResult removeUser(Long groupId, Long uid);
}
