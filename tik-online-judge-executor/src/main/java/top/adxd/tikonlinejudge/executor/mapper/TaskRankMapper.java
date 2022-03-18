package top.adxd.tikonlinejudge.executor.mapper;

import org.apache.ibatis.annotations.Select;
import top.adxd.tikonlinejudge.executor.entity.TaskRank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wait_light
 * @since 2022-01-27
 */
public interface TaskRankMapper extends BaseMapper<TaskRank> {
    @Select("select *,@rank:=@rank + 1 as `rank` from pms_task_rank,(SELECT @rank := 0) r where task_id = #{raceId} order by score desc,penalty asc")
    List<TaskRank> rank(Long raceId);
}
