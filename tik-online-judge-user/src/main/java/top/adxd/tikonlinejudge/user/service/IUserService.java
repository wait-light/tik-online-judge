package top.adxd.tikonlinejudge.user.service;

import top.adxd.tikonlinejudge.user.api.Vo.SafeUserVo;
import top.adxd.tikonlinejudge.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
public interface IUserService extends IService<User> {
    User getUser(String email);
    User getUserByUsername(String username);
    SafeUserVo getSafeUser(Long uid);
}
