package top.adxd.tikonlinejudge.auth.dto;

import java.io.Serializable;

/**
 * @author wait-light
 * @date 2021/11/4.
 */

public class Nickname implements Serializable {
    public String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
