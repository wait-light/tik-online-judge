package top.adxd.tikonlinejudge.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.adxd.tikonlinejudge.auth.single.MenuType;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
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
    private String name;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型(0:目录 1.接口)
     */
    private MenuType type;
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

    /**
     * 请求方法
     */
    private String requestMethod;

}
