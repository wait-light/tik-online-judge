package top.adxd.tikonlinejudge.auth.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wait_light
 * @since 2021-10-18
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态(1启用/0禁用)
     */
    private Boolean status;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 是否管理员
     */
    private Boolean admin;
}
