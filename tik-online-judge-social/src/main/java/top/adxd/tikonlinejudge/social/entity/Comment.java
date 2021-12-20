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
@TableName("cms_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 题解id
     */
    private Long solutionId;

    /**
     * 评论人
     */
    private Long uid;

    /**
     * 层id
     */
    private Long floorId;

    /**
     * 评论内容
     */
    @NotNull
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

    /**
     * 状态(1启用/0禁用)
     */
    private Boolean status;

}
