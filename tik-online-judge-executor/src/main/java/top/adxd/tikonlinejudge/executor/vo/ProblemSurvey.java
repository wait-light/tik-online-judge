package top.adxd.tikonlinejudge.executor.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import top.adxd.tikonlinejudge.executor.entity.Problem;

import java.time.LocalDateTime;

@Data
public class ProblemSurvey {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问题名称
     */
    private String name;

    /**
     * 上传人
     */
    private Long uid;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public static QueryWrapper<Problem> problemSurveySelect(){
        return new QueryWrapper<Problem>()
                .select("id","name","uid","create_time","update_time");
    }
}
