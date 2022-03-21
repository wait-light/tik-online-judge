package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.util.StringUtils;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByEmailDto;
import top.adxd.tikonlinejudge.auth.dto.PasswordUpdateByPasswordDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.mapper.UserMapper;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;
import top.adxd.tikonlinejudge.message.api.VerifyCodeStatus;

import java.time.LocalDateTime;

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
    public static final String DEFAULT_PASSWORD = "123456";

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

    @Override
    public CommonResult resetPassword(Long uid) {
        Long operatorUid = UserInfoUtil.getUid();
        User user = getById(uid);
        if (user.getAdmin() && !user.getUid().equals(operatorUid)) {
            return CommonResult.permissionDeny("无操作权限");
        }
        String resetPassword = secureConfig.getSymmetricCrypto().encryptHex(DEFAULT_PASSWORD);
        user.setPassword(resetPassword);
        return super.updateById(user) ? CommonResult.success(String.format("密码重置为【%s】", DEFAULT_PASSWORD)) : CommonResult.error("重置失败");
    }

    @Override
    public CommonResult updateUser(User user) {
        if (user.getUid() == null) {
            return CommonResult.error("用户不存在");
        }
        User u = getById(user.getUid());
        if (u == null) {
            return CommonResult.error("用户不存在");
        }
        if (user.getStatus() != null && user.getStatus()) {
            u.setStatus(user.getStatus());
        }
        if (user.getNickname() != null && StringUtils.hasText(user.getNickname())) {
            u.setNickname(user.getNickname());
        }
        if (user.getAvatar() != null && StringUtils.hasText(user.getAvatar())) {
            u.setAvatar(user.getAvatar());
        }
        u.setTelephone(user.getTelephone());
        u.setEmail(user.getEmail());
        return updateById(u) ? CommonResult.success("更新成功") : CommonResult.error("更新失败");
    }

    @Override
    public CommonResult addUser(User user) {
        if (user.getEmail() != null && StringUtils.hasLength(user.getEmail())) {
            User emailUser = getUser(user.getEmail());
            if (emailUser != null) {
                return CommonResult.error("用户邮箱已被使用");
            }
        }
        if (user.getUsername() != null && StringUtils.hasLength(user.getUsername())) {
            User userByUsername = getUserByUsername(user.getUsername());
            if (userByUsername != null) {
                return CommonResult.error("用户名已被使用");
            }
        }
        if (user.getUsername() == null && user.getEmail() == null) {
            return CommonResult.error("用户名或者邮箱至少需要填写一个");
        }
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);
        user.setCreateTime(now);
        String resetPassword = secureConfig.getSymmetricCrypto().encryptHex(DEFAULT_PASSWORD);
        user.setPassword(resetPassword);
        return save(user) ? CommonResult.success("添加成功") : CommonResult.error("添加失败");
    }

    @Override
    public CommonResult userListByUserName(String userName) {
        PageUtils.makePage();
        return CommonResult
                .success()
                .listData(
                        list(new QueryWrapper<User>()
                                .like(userName != null && !userName.trim().equals("")
                                        , "username"
                                        , userName
                                ))
                );
    }
}
