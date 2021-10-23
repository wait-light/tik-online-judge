package top.adxd.tikonlinejudge.user.service.api.impl;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.user.api.UserInfo;
import top.adxd.tikonlinejudge.user.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.user.api.dto.UserDto;
import top.adxd.tikonlinejudge.user.config.TokenConfig;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IUserService;

/**
 * @author light
 */
@DubboService
public class Token2UserImpl implements UserInfo {
    @Autowired
    private TokenConfig tokenConfig;
    @Autowired
    private IUserService userService;
    @Override
    public Long uid(String token){
        if (JWTUtil.verify(token, tokenConfig.getSecretKey().getBytes())){
            JWT jwt = JWTUtil.parseToken(token);
            Object uid = jwt.getPayload("uid");
            return Long.parseLong(uid.toString());
        }
        return null;
    }
    @Override
    public SafeUserDto baseUserMessage(String token){
        if (JWTUtil.verify(token, tokenConfig.getSecretKey().getBytes())){
            JWT jwt = JWTUtil.parseToken(token);
            Object uid = jwt.getPayload("uid");
            return userService.getSafeUser(Long.parseLong(uid.toString()));
        }
        return null;
    }

    @Override
    public UserDto userDetail(Long uid) {
        User user = userService.getById(uid);
        if (user == null){
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
}
