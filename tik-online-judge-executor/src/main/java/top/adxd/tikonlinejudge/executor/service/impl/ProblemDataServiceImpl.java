package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import top.adxd.tikonlinejudge.executor.entity.Submit;
import top.adxd.tikonlinejudge.executor.mapper.ProblemDataMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-21
 */
@Service
public class ProblemDataServiceImpl extends ServiceImpl<ProblemDataMapper, ProblemData> implements IProblemDataService {

    @Override
    public List<ProblemData> getProblemDataList(Long problemId) {
        if (null == problemId || 0L== problemId){
            return new ArrayList<>();
        }
        return baseMapper.selectList(new QueryWrapper<ProblemData>().eq("problem_id", problemId));
    }

    @Override
    public List<ProblemData> getProblemDataList(Submit submit) {
        if (submit == null){
            return new ArrayList<>();
        }
        return getProblemDataList(submit.getProblemId());
    }
}
