package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.Solution;
import top.adxd.tikonlinejudge.executor.mapper.SolutionMapper;
import top.adxd.tikonlinejudge.executor.service.ISolutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-14
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {
    private static final String SPECIAL_SYMBOL_REG = "[\\n`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*()——+|{}【】‘；：”“’。， 、？]";
    @Override
    public Long hasSolution(Long uid, Long problemId) {
        Solution one = getOne(new QueryWrapper<Solution>()
                .eq("uid", uid)
                .eq("problem_id", problemId)
                .select("id"));
        if (one == null){
            return null;
        }
        return one.getId();
    }

    @Override
    public List<Solution> solutionList(Long problemId) {
        PageUtils.makePage();
        List<Solution> collect = baseMapper.selectList(new QueryWrapper<Solution>()
                        .eq("problem_id", problemId)
                        .eq("status", true)
                        .select("title", "content","id"))
                .stream()
                .map(solution -> {
                    String content = solution.getContent();
                    //截取一部分数据用于展示
                    content = content.substring(0, Math.min(content.length(), 30));
                    //去除特殊符号
                    content = content.replaceAll(SPECIAL_SYMBOL_REG,"");
                    solution.setContent(content);
                    return solution;
                })
                .collect(Collectors.toList());
        return collect;
    }
}
