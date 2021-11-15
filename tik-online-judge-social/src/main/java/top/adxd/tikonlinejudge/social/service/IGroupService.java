package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.social.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
public interface IGroupService extends IService<Group> {
    /**
     *
     * @param uid 用户id
     * @return 用户加入的群组
     */
    List<Group> groups(Long uid);
}
