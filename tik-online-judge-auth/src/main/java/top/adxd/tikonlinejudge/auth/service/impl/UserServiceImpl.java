package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByEmailDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByPasswordDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.mapper.UserMapper;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;
import top.adxd.tikonlinejudge.message.api.VerifyCodeStatus;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @DubboReference
    private IVerificationCodeService verificationCodeService;
    @Autowired
    private SecureConfig secureConfig;

    @Override
    public User getUser(String email) {
        if (email == null || "".equals(email)) {
            return null;
        }
        return getOne(new QueryWrapper<User>().eq("email", email));
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null || "".equals(username)) {
            return null;
        }
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public SafeUserDto getSafeUser(Long uid) {
        QueryWrapper<User> select = new QueryWrapper<User>()
                .eq("uid", uid)
                .select("uid", "username", "nickname", "avatar");
        User user = getOne(select);
        SafeUserDto safeUserDto = new SafeUserDto();
        BeanUtils.copyProperties(user, safeUserDto);
        return safeUserDto;
    }

    @Override
    public CommonResult updatePassword(PasswordUpdateByEmailDto dto) {
        VerifyCodeStatus verifyCodeStatus = verificationCodeService.verifyCode(dto.getEmail(), dto.getCode());
        if (verifyCodeStatus == VerifyCodeStatus.error) {
            return CommonResult.error("邮箱或验证码错误");
        }
        User user = getUser(dto.email);
        if (user == null) {
            return CommonResult.error("邮箱或验证码错误");
        }
        String newPassword = secureConfig.getSymmetricCrypto().encryptHex(dto.getNewPassword());
        user.setPassword(newPassword);
        return super.updateById(user) ? CommonResult.success("密码修改成功") : CommonResult.error("密码修改失败");
    }

    @Override
    public CommonResult updatePassword(PasswordUpdateByPasswordDto dto) {
        User user = getUserByUsername(dto.getUsername());
        if (user == null) {
            return CommonResult.error("用户名或密码错误");
        }
        String oldPassword = secureConfig.getSymmetricCrypto().encryptHex(dto.getPassword());
        if (!oldPassword.equals(user.getPassword())) {
            return CommonResult.error("用户名或密码错误");
        }
        String newPassword = secureConfig.getSymmetricCrypto().encryptHex(dto.getNewPassword());
        user.setPassword(newPassword);
        return super.updateById(user) ? CommonResult.success("密码修改成功") : CommonResult.error("密码修改失败");
    }
}
