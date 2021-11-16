package top.adxd.tikonlinejudge.social.dto;

import top.adxd.tikonlinejudge.social.entity.InviteStatus;

import java.io.Serializable;

public class ConsumeInvite implements Serializable {
    public InviteStatus inviteStatus;

    public InviteStatus getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(InviteStatus inviteStatus) {
        this.inviteStatus = inviteStatus;
    }
}
