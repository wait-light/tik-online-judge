package top.adxd.tikonlinejudge.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.entity.Solution;
import top.adxd.tikonlinejudge.social.mapper.SolutionMapper;
import top.adxd.tikonlinejudge.social.service.ISolutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {

    private static final String REG_TITLE = "((#+ )\\S+\\s?)|(```\\S*\\s([^`]*)```)|(!?\\[.*\\]\\(.+\\))";

    @Override
    public Long hasSolution(Long uid, Long problemId) {
        Solution one = getOne(new QueryWrapper<Solution>()
                .eq("uid", uid)
                .eq("problem_id", problemId)
                .select("id"));
        if (one == null) {
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
                        .select("title", "content", "id","create_time","view"))
                .stream()
                .map(solution -> {
                    String content = solution.getContent();
                    //去除markdown格式
                    content = content.replaceAll(REG_TITLE, "");
                    //截取一部分数据用于展示
                    content = content.substring(0, Math.min(content.length(), 50));
                    solution.setContent(content);
                    return solution;
                })
                .collect(Collectors.toList());
        return collect;
    }
}
