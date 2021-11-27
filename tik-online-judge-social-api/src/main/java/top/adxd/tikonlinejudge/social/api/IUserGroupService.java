package top.adxd.tikonlinejudge.social.api;

import java.util.List;

public interface IUserGroupService {
    /**
     * 获取用户加入的群组id
     *
     * @param uid 用户id
     * @return 群组id列表
     */
    List<Long> userGroups(Long uid);
}
