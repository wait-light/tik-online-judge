package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.config.SecureConfig;
import top.adxd.tikonlinejudge.auth.dto.ChangeAvatarDto;
import top.adxd.tikonlinejudge.auth.dto.ChangeEmailDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import top.adxd.tikonlinejudge.auth.service.IVerifiedService;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;
import top.adxd.tikonlinejudge.common.util.ServletUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.message.api.IVerificationCodeService;
import top.adxd.tikonlinejudge.message.api.VerifyCodeStatus;

import java.time.Duration;

/**
 * @author wait-light
 * @date 2021/11/1
 */
@Service("verifiedService")
public class VerifiedServiceImpl implements IVerifiedService {
    @Autowired
    private IUserService userService;
    @Autowired
    private JWTUtil jwtUtil;
    @DubboReference
    private IVerificationCodeService emailVerificationCodeService;
    @Autowired
    private SecureConfig secureConfig;

    @Override
    public SafeUserDto baseMessage(String token) {
        Long uid = jwtUtil.uid(token);
        if (uid == null) {
            return null;
        }
        return userService.getSafeUser(uid);
    }

    @Override
    public CommonResult changeNickName(Long uid, String nickname) {
        User user = userService.getById(uid);
        //todo 合法判断
        user.setNickname(nickname);
        boolean success = userService.saveOrUpdate(user);
        return success ?
                CommonResult.success(String.format("成功修改昵称为【%s】", nickname)) :
                CommonResult.error("修改失败");
    }

    @Override
    public CommonResult accountMessage(Long uid) {
        User user = userService.getById(uid);
        if (user == null) {
            return CommonResult.error("用户不存在");
        }
        return CommonResult.success()
                .add("avatar", user.getAvatar())
                .add("email", user.getEmail())
                .add("nickname", user.getNickname());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult changeEmail(ChangeEmailDto changeEmailDto) {
        VerifyCodeStatus verifyCodeStatus = emailVerificationCodeService.verifyCode(changeEmailDto.getEmail(), changeEmailDto.getCode());
        if (verifyCodeStatus != VerifyCodeStatus.success) {
            return CommonResult.error("验证码错误");
        }
        User user = userService.getOne(new QueryWrapper<User>().eq("uid", ServletUtils.getHeader("uid")));
        if (user == null) {
            return CommonResult.error("未登录");
        }
        String hexPassword = null;
        if (changeEmailDto.getPassword() != null) {
            hexPassword = secureConfig.getSymmetricCrypto().encryptHex(changeEmailDto.getPassword());
        }
        //无需密码或者密码正确
        if (user.getPassword() == null || "".equals(user.getPassword()) || user.getPassword().equals(hexPassword)) {
            //验证成功后才进行邮箱是否有主人检测，防止通过此接口查看是否已经注册
            User email = userService.getOne(new QueryWrapper<User>().eq("email", changeEmailDto.getEmail()).select("uid"));
            if (email != null) {
                return CommonResult.error("邮箱已被绑定");
            }
            user.setEmail(changeEmailDto.getEmail());
            userService.saveOrUpdate(user);
            return CommonResult.success("更新成功");
        }
        return CommonResult.error("密码错误");
    }

    @Override
    public CommonResult changeAvatar(ChangeAvatarDto changeAvatarDto) {
        Long uid = UserInfoUtil.getUid();
        User user = userService.getById(uid);
        user.setAvatar(changeAvatarDto.getAvatar());
        return userService.updateById(user) ? CommonResult.success("更新成功") : CommonResult.error("更新失败");
    }
}
