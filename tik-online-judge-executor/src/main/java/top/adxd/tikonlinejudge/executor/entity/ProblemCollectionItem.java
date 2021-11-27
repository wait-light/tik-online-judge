package top.adxd.tikonlinejudge.executor.entity;

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
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_problem_collection_item")
public class ProblemCollectionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属集合
     */
    private Long collectionId;

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 是否启用
     */
    private Boolean status;


}
