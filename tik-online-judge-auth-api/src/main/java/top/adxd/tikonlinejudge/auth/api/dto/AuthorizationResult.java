package top.adxd.tikonlinejudge.auth.api.dto;

import java.io.Serializable;

public class AuthorizationResult implements Serializable {
    public Boolean success;
    public Long uid;
    public String message;
    public Boolean admin;

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public AuthorizationResult() {
    }

    public AuthorizationResult(Boolean success, Long uid, String message, Boolean admin) {
        this.success = success;
        this.uid = uid;
        this.message = message;
        this.admin = admin;
    }
}
