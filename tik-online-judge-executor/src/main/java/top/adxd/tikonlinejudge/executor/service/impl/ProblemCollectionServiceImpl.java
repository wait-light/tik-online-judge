package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.exeption.CommonException;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IGroupCollectionService;
import top.adxd.tikonlinejudge.executor.entity.CollectionGroup;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollection;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionItemMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionMapper;
import top.adxd.tikonlinejudge.executor.mapper.ProblemMapper;
import top.adxd.tikonlinejudge.executor.service.ICollectionGroupService;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.vo.ProblemSurvey;
import top.adxd.tikonlinejudge.social.api.IUserGroupService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private ICollectionGroupService collectionGroupService;
    @DubboReference
    private IUserGroupService userGroupService;


    @Override
    public List<ProblemSurvey> collectionsItem(Long collectionId, boolean all) {
        //TODO 权限校验
        //是否进行数据分页
        if (!all) {
            PageUtils.makePage();
        }
        List<ProblemCollectionItem> problemCollectionItems = problemCollectionItemMapper
                .selectList(new QueryWrapper<ProblemCollectionItem>()
                        .eq("collection_id", collectionId)
                        .eq("status",true)
                        .select("problem_id"));
        if (problemCollectionItems.size() <= 0) {
            return new ArrayList<>();
        }
        List<Long> problemIds = problemCollectionItems.stream().map((item) -> item.getProblemId()).collect(Collectors.toList());
        return problemMapper
                .selectList(new QueryWrapper<Problem>()
                        .in("id", problemIds)
                        .select("name", "create_time", "update_time", "id")
                )
                .stream().map((problem) -> {
                    ProblemSurvey problemSurvey = new ProblemSurvey();
                    BeanUtils.copyProperties(problem, problemSurvey);
                    return problemSurvey;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addProblem(Problem problem, Long collectionId) {
        LocalDateTime now = LocalDateTime.now();
        problem.setCreateTime(now);
        problem.setUpdateTime(now);
        problem.setCollectionId(collectionId);
        Long uid = UserInfoUtil.getUid();
        problem.setUid(uid);
        int insert = problemMapper.insert(problem);
        if (insert <= 0) {
            throw new CommonException("问题添加失败");
        }
        ProblemCollectionItem problemCollectionItem = new ProblemCollectionItem();
        problemCollectionItem.setCollectionId(collectionId);
        problemCollectionItem.setProblemId(problem.getId());
        problemCollectionItem.setStatus(problem.getStatus());
        boolean itemSuccess = problemCollectionItemMapper.insert(problemCollectionItem) >= 0;
        if (!itemSuccess) {
            throw new CommonException("问题添加失败");
        }
        return CommonResult.success("问题添加成功");
    }

    @Override
    public boolean isInCollection(Long collectionId, Long problemId) {
        return problemCollectionItemMapper.selectOne(new QueryWrapper<ProblemCollectionItem>()
                .eq("collection_id", collectionId)
                .eq("problem_id", problemId)
                .select("id")) != null;
    }

    @Override
    public CommonResult collectionProblems(Long collectionId) {
        Page<Long> objects = PageUtils.makePage();
        List<Long> collect = problemCollectionItemMapper.selectList(new QueryWrapper<ProblemCollectionItem>()
                        .eq("collection_id", collectionId)
                        .select("problem_id"))
                .stream()
                .map(item -> item.getProblemId())
                .collect(Collectors.toList());
        if (collect.size() <= 0) {
            return CommonResult.success().listData(objects, collect);
        }
        List<Problem> problems = problemMapper.selectBatchIds(collect);
        return CommonResult.success().listData(objects, problems);
    }

    @Override
    public CommonResult personCollection() {
        Long uid = UserInfoUtil.getUid();
        List<Long> groupIds = userGroupService.userGroups(uid);
        if (groupIds.size() <= 0) {
            return CommonResult.success().add("list", new ArrayList<>());
        }
        List<Long> collectionIds = collectionGroupService.list(new QueryWrapper<CollectionGroup>()
                        .in("group_id", groupIds))
                .stream()
                .map(CollectionGroup::getCollectionId)
                .collect(Collectors.toList());
        if (collectionIds.size() <= 0) {
            return CommonResult.success().add("list", new ArrayList<>());
        }
        return CommonResult.success().add("list", listByIds(collectionIds));
    }
}
