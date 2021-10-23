package top.adxd.tikonlinejudge.user.api;

import top.adxd.tikonlinejudge.user.api.dto.SafeUserDto;
import top.adxd.tikonlinejudge.user.api.dto.UserDto;

public interface UserInfo {
    Long uid(String token);
    SafeUserDto baseUserMessage(String token);
    UserDto userDetail(Long uid);
}
