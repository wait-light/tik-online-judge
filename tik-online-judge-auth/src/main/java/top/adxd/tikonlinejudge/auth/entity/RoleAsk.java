package top.adxd.tikonlinejudge.auth.entity;

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
 * @since 2021-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_role_ask")
public class RoleAsk implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 申请人
     */
    private Long uid;

    /**
     * 处理人
     */
    private Long handler;

    /**
     * 处理时间
     */
    private LocalDateTime updateTime;

    /**
     * 处理状态
     */
    private Integer status;

    private String reason;


}
