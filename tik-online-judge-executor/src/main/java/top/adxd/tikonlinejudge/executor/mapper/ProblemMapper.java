package top.adxd.tikonlinejudge.executor.mapper;

import top.adxd.tikonlinejudge.executor.entity.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.adxd.tikonlinejudge.executor.vo.ProblemSimpleVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
public interface ProblemMapper extends BaseMapper<Problem> {
    List<ProblemSimpleVo> userFinishedProblem(Long uid,Boolean onlyPublic,Integer status);
}
