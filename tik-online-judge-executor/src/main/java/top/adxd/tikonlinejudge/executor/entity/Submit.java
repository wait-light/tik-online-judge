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
 * @since 2021-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_submit")
public class Submit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @NotNull
    private Long uid;

    /**
     * 语言类型
     */
    @NotNull
    private Integer languageType;

    /**
     * 内容(编码内容)
     */
    @NotNull
    private String content;

    /**
     * 提交时间
     */
    private LocalDateTime createTime;

    /**
     * 运行结果
     */
    private Integer status;

    /**
     * 提交的问题
     */
    @NotNull
    private Long problemId;


}
