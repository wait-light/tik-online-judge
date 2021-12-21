package top.adxd.tikonlinejudge.social.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_solution")
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题解用户id
     */
    private Long uid;

    /**
     * 题解内容（文章内容）
     */
    @NotNull
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态(1启用/0状态)
     */
    private Boolean status;

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 浏览量
     */
    private Long view;

}
