package top.adxd.tikonlinejudge.auth.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ChangeAvatarDto implements Serializable {
    @NotNull
    public String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
