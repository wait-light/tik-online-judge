package top.adxd.tikonlinejudge.social.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.adxd.tikonlinejudge.social.single.GroupCreatorApplyStatus;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2021-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_group_creator_apply")
public class GroupCreatorApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请者id
     */
    private Long uid;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 审核时间
     */
    private LocalDateTime updateTime;

    /**
     * 申请状态
     */
    private GroupCreatorApplyStatus status;

    /**
     * 审核人
     */
    private Long judgeUid;

    /**
     * 申请人地址
     */
    private String address;

    /**
     * 申请原因
     */
    private String reason;


}
