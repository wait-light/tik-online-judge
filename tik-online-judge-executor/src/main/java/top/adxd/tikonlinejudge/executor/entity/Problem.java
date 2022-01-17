package top.adxd.tikonlinejudge.executor.entity;

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
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_problem")
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问题名称
     */
    @NotNull
    private String name;

    /**
     * 上传人
     */
    private Long uid;

    /**
     * 问题描述
     */
    private String problemDescribe;

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Boolean status;

    private String inputDescrible;

    private String outputDescrible;

    /**
     * 所属合集
     */
    private Long collectionId;

    /**
     * 是否与其他集合共享
     */
    private Boolean share;

    /**
     * 秘钥
     */
    private String secretKey;
}
