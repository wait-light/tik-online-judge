package top.adxd.tikonlinejudge.user.api;

import top.adxd.tikonlinejudge.user.api.Vo.SafeUserVo;

public interface Token2User {
    Long uid(String token);
    SafeUserVo baseUserMessage(String token);
}
