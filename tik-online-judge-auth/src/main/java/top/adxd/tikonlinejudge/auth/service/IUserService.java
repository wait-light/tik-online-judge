package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByEmailDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByPasswordDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface IUserService extends IService<User> {
    User getUser(String email);

    User getUserByUsername(String username);

    SafeUserDto getSafeUser(Long uid);

    CommonResult updatePassword(PasswordUpdateByEmailDto dto);

    CommonResult updatePassword(PasswordUpdateByPasswordDto dto);
}
