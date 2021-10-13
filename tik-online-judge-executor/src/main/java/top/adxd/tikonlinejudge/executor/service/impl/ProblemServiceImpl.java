package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionItemMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    private ProblemCollectionItemMapper problemCollectionItemMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult delete(Long problemId) {
        this.removeById(problemId);
        problemCollectionItemMapper.delete(new QueryWrapper<ProblemCollectionItem>()
                .eq("problem_id",problemId));
        return CommonResult.success("删除成功");
    }
}
