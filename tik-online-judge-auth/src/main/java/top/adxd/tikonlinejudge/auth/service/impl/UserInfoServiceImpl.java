package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.IUserInfoService;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.entity.User;
import top.adxd.tikonlinejudge.auth.service.IUserService;

@DubboService
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserService userService;

    @Override
    public SafeUserDto getUser(String identify) {
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", identify)
                .or()
                .eq("email", identify));
        if (user == null) {
            return null;
        }
        SafeUserDto safeUserDto = new SafeUserDto();
        BeanUtils.copyProperties(user, safeUserDto);
        return safeUserDto;
    }

    @Override
    public String userName(Long uid) {
        User one = userService.getOne(new QueryWrapper<User>()
                .eq("uid", uid)
                .select("nickname"));
        if (one == null){
            return "";
        }
        return one.getNickname();
    }
}
