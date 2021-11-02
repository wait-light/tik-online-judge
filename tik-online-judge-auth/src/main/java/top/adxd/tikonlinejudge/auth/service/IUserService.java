package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface IUserService extends IService<User> {
    User getUser(String email);
    User getUserByUsername(String username);
    SafeUserDto getSafeUser(Long uid);
}
