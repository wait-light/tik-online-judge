package top.adxd.tikonlinejudge.auth.dto;

import top.adxd.tikonlinejudge.auth.entity.RoleAsk;

public class AskDto extends RoleAsk {
    public String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
