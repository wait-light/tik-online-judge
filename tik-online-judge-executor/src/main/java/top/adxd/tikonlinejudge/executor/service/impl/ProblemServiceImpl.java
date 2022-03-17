package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IProblemServiceApi;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionItemMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.vo.ProblemSurvey;

import java.util.*;
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
@DubboService(interfaceClass = IProblemServiceApi.class)
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService, IProblemServiceApi {

    @Autowired
    private ProblemCollectionItemMapper problemCollectionItemMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult delete(Long problemId) {
        this.removeById(problemId);
        problemCollectionItemMapper.delete(new QueryWrapper<ProblemCollectionItem>()
                .eq("problem_id", problemId));
        return CommonResult.success("删除成功");
    }

    @Override
    public CommonResult getAvailableProblem(Long collectionId) {
        /**
         * 所有公开或者的本合集私有的问题 的集合
         */
        Set<Long> sharedOrSelfProblemId = baseMapper.selectList(new QueryWrapper<Problem>()
                .eq("share", true)
                .or()
                .eq("collection_id", collectionId)
                .select("id"))
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toSet());
        /**
         * 本集合已经添加的问题的集合
         */
        Set<Long> selfProblemId = problemCollectionItemMapper.selectList(new QueryWrapper<ProblemCollectionItem>()
                .eq("collection_id", collectionId)
                .select("problem_id"))
                .stream()
                .map(item -> item.getProblemId())
                .collect(Collectors.toSet());
        //取差集即是剩下可以选择的问题
        sharedOrSelfProblemId.removeAll(selfProblemId);
        //防止报空
        if (sharedOrSelfProblemId.size() == 0) {
            sharedOrSelfProblemId.add(0L);
        }
        PageUtils.makePage();

        List<ProblemSurvey> collect =
                baseMapper.selectList(new QueryWrapper<Problem>()
                        .in("id", sharedOrSelfProblemId)
                        .select("name", "create_time", "update_time", "id")
                )
                        .stream().map((problem) -> {
                    ProblemSurvey problemSurvey = new ProblemSurvey();
                    BeanUtils.copyProperties(problem, problemSurvey);
                    return problemSurvey;
                }).collect(Collectors.toList());
        return CommonResult.success().listData(collect);
    }

    @Override
    public CommonResult problemNames(Long... problemIds) {
        if (problemIds.length <= 0) {
            return CommonResult.success().singleData(new ArrayList<>());
        }
        Map<Long, String> problems = list(new QueryWrapper<Problem>()
                .in("id", problemIds)
                .select("id", "name"))
                .stream()
                .collect(Collectors.toMap(Problem::getId, Problem::getName));
        return CommonResult.success().singleData(problems);
    }

    @Override
    public String problemName(Long problemId) {
        Problem problem = getOne(new QueryWrapper<Problem>().eq("id", problemId).select("name"));
        if (problem == null) {
            return "";
        }
        return problem.getName();
    }

    @Override
    public CommonResult problemListByName(String problemName) {
        PageUtils.makePage();
        return CommonResult
                .success()
                .listData(
                        list(new QueryWrapper<Problem>()
                                .like(problemName != null && !problemName.trim().equals("")
                                        , "name"
                                        , problemName
                                ))
                );
    }

    @Override
    public List<top.adxd.tikonlinejudge.executor.api.entity.Problem> problemInfoList(List<Long> pids, String... select) {
        if (pids == null || pids.size() <= 0) {
            return new ArrayList<>();
        }
        return baseMapper.selectList(new QueryWrapper<Problem>().select(select).in("id", pids))
                .stream()
                .map(item -> {
                    top.adxd.tikonlinejudge.executor.api.entity.Problem problem = new top.adxd.tikonlinejudge.executor.api.entity.Problem();
                    BeanUtils.copyProperties(item, problem);
                    return problem;
                }).collect(Collectors.toList());
    }
}
