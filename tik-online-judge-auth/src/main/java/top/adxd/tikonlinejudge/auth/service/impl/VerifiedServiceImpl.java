package top.adxd.tikonlinejudge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.auth.service.IUserService;
import top.adxd.tikonlinejudge.auth.service.IVerifiedService;
import top.adxd.tikonlinejudge.auth.util.JWTUtil;

/*
 * @author wait-light
 * @date 2021/11/1.
 */
@Service("verifiedService")
public class VerifiedServiceImpl implements IVerifiedService {
    @Autowired
    private IUserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public SafeUserDto baseMessage(String token) {
        Long uid = jwtUtil.uid(token);
        if (uid == null) {
            return null;
        }
        return userService.getSafeUser(uid);
    }
}
