package top.adxd.tikonlinejudge.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.AUTO)
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


}
