package top.adxd.tikonlinejudge.executor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Map;

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
@TableName("pms_task_rank")
public class TaskRank implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long taskId;
    private Long uid;

    private Integer score;
    private Long penalty;

    @TableField(exist = false)
    private Integer rank;

    @TableField(exist = false)
    private String userNickname;

    @TableField(exist = false)
    private Map<Long, Integer> problemScores;
}
