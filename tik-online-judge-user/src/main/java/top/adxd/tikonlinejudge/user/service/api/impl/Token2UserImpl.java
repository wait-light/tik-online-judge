package top.adxd.tikonlinejudge.user.service.api.impl;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.user.api.Token2User;
import top.adxd.tikonlinejudge.user.api.Vo.SafeUserVo;
import top.adxd.tikonlinejudge.user.config.TokenConfig;
import top.adxd.tikonlinejudge.user.service.IUserService;

/**
 * @author light
 */
@DubboService
public class Token2UserImpl implements Token2User {
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
    public SafeUserVo baseUserMessage(String token){
        if (JWTUtil.verify(token, tokenConfig.getSecretKey().getBytes())){
            JWT jwt = JWTUtil.parseToken(token);
            Object uid = jwt.getPayload("uid");
            return userService.getSafeUser(Long.parseLong(uid.toString()));
        }
        return null;
    }
}
