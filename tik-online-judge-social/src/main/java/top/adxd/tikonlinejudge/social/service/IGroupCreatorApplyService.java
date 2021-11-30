package top.adxd.tikonlinejudge.social.service;

import top.adxd.tikonlinejudge.social.entity.GroupCreatorApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-30
 */
public interface IGroupCreatorApplyService extends IService<GroupCreatorApply> {
    boolean apply(GroupCreatorApply groupCreatorApply);
}
