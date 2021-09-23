package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollection;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionItemMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.vo.ProblemSurvey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
@Service
public class ProblemCollectionServiceImpl extends ServiceImpl<ProblemCollectionMapper, ProblemCollection> implements IProblemCollectionService {

    @Autowired
    private ProblemCollectionItemMapper problemCollectionItemMapper;
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<ProblemSurvey> collectionsItem(Long collectionId) {
        //TODO 权限校验
        PageUtils.makePage();
        List<ProblemCollectionItem> problemCollectionItems = problemCollectionItemMapper
                .selectList(new QueryWrapper<ProblemCollectionItem>()
                        .eq("collection_id", collectionId)
                        .select("problem_id"));
        if (problemCollectionItems.size() <= 0) {
            return new ArrayList<>();
        }
        List<Long> problemIds = problemCollectionItems.stream().map((item) -> item.getProblemId()).collect(Collectors.toList());
        return problemMapper
                .selectList(ProblemSurvey.problemSurveySelect().in("id", problemIds))
                .stream().map((problem) -> {
                    ProblemSurvey problemSurvey = new ProblemSurvey();
                    BeanUtils.copyProperties(problem, problemSurvey);
                    return problemSurvey;
                })
                .collect(Collectors.toList());
    }
}
