package top.adxd.tikonlinejudge.social.dto;

import java.io.Serializable;

public class InviteInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String invitees;
    /**
     * 邀请到的群组
     */
    private Long groupId;

    public String getInvitees() {
        return invitees;
    }

    public void setInvitees(String invitees) {
        this.invitees = invitees;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
