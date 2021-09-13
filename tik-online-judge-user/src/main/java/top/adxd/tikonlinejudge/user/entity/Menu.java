package top.adxd.tikonlinejudge.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("ums_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型(0:菜单 1.按钮)
     */
    private Integer type;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 父级id
     */
    private Long parentId;
}
