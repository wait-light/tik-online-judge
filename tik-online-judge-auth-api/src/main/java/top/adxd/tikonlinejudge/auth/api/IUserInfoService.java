package top.adxd.tikonlinejudge.auth.api;

import top.adxd.tikonlinejudge.auth.api.dto.SafeUserDto;

public interface IUserInfoService {
    /**
     * 通过用户的唯一标识获取用户信息
     * @param identify 用户唯一标识
     * @return 用户基础信息
     */
    SafeUserDto getUser(String identify);
}
