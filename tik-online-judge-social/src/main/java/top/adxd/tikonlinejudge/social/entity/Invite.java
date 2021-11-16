package top.adxd.tikonlinejudge.social.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 邀请信息表
 * </p>
 *
 * @author wait_light
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_invite")
public class Invite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邀请人
     */
    @TableField("initiator")
    private Long initiator;

    /**
     * 受邀人
     */
    @TableField("invitees")
    private Long invitees;

    /**
     * 邀请时间
     */
    private LocalDateTime createTime;

    /**
     * 邀请状态
     */
    private InviteStatus status;

    /**
     * 邀请到的群组
     */
    private Long groupId;

    /**
     * 处理时间
     */
    private LocalDateTime updateTime;
}
