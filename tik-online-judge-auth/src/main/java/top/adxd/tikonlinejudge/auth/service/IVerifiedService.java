package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;

/*
 * @author wait-light
 * @date 2021/11/1.
 */
public interface IVerifiedService {
    SafeUserDto baseMessage(String token);
}
