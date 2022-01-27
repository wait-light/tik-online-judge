package top.adxd.tikonlinejudge.executor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2022-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_task_submit_item")
public class TaskSubmitItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long taskId;

    private Long uid;

    private Long problemId;

    private Integer score;


}
