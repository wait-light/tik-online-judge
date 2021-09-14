package top.adxd.tikonlinejudge.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@TableName("ums_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    @NotNull
    @Size(min = 1,max = 64,message = "角色名长度非法")
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户id
     */
    private Long createUserId;

    /**
     * 1启用/0关闭
     */
    private Boolean status;


}
