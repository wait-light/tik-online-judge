package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.api.IGroupCollectionService;
import top.adxd.tikonlinejudge.executor.entity.CollectionGroup;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollection;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.CollectionGroupMapper;
import top.adxd.tikonlinejudge.executor.service.ICollectionGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionItemService;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionService;
import top.adxd.tikonlinejudge.executor.service.IProblemService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-11-17
 */
@Service
@DubboService(interfaceClass = IGroupCollectionService.class)
public class CollectionGroupServiceImpl extends ServiceImpl<CollectionGroupMapper, CollectionGroup> implements ICollectionGroupService, IGroupCollectionService {
    @Autowired
    private IProblemCollectionService problemCollectionService;
    @Autowired
    private IProblemCollectionItemService problemCollectionItemService;
    @Autowired
    private IProblemService problemService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean collectionGroupCreate(Long groupId, String groupName) {
        LocalDateTime now = LocalDateTime.now();
        ProblemCollection problemCollection = new ProblemCollection();
        problemCollection.setPublicCollection(false);
        problemCollection.setCreateTime(now);
        problemCollection.setBeginTime(now);
        problemCollection.setEndTime(now);
        problemCollection.setUpdateTime(now);
        problemCollection.setName(groupName);
        problemCollection.setStatus(true);
        problemCollectionService.save(problemCollection);
        CollectionGroup collectionGroup = new CollectionGroup();
        collectionGroup.setCollectionId(problemCollection.getId());
        collectionGroup.setGroupId(groupId);
        save(collectionGroup);
        return true;
    }

    @Override
    public List<Problem> groupProblems(Long groupId) {
        //todo 权限校验
        CollectionGroup collectionGroup = getOne(new QueryWrapper<CollectionGroup>()
                .eq("group_id", groupId));
        if (collectionGroup == null) {
            return new ArrayList<>();
        }
        PageUtils.makePage();
        List<Long> problemIds = problemCollectionItemService
                .list(new QueryWrapper<ProblemCollectionItem>()
                        .eq("collection_id", collectionGroup.getCollectionId()))
                .stream()
                .map((item) -> item.getProblemId())
                .collect(Collectors.toList());
        if (problemIds.size() <= 0) {
            return new ArrayList<>();
        }
        return problemService.listByIds(problemIds);
    }

    @Transactional
    @Override
    public CommonResult addProblem(Long groupId, Problem problem) {
        //todo 权限校验
        Long collection = getCollection(groupId);
        return problemCollectionService.addProblem(problem, collection);
    }

    @Override
    public boolean deleteProblem(Long groupId, Long problemId) {
        //todo 权限校验
        Long collection = getCollection(groupId);
        return problemCollectionItemService.remove(new QueryWrapper<ProblemCollectionItem>()
                .eq("collection_id", collection)
                .eq("problem_id", problemId));
    }

    @Override
    public CommonResult updateProblem(Long groupId, Problem problem) {
        Long collection = getCollection(groupId);
        boolean inCollection = problemCollectionService.isInCollection(collection, problem.getId());
        if (!inCollection) {
            return CommonResult.permissionDeny("无权限操作");
        }
        //todo 权限校验
        return problemService.updateById(problem) ? CommonResult.success("更新成功") : CommonResult.error("更新失败");
    }

    @Override
    public CommonResult groupCollections(Long groupId) {
        LocalDateTime now = LocalDateTime.now();
        Long collection = getCollection(groupId);
        ProblemCollection groupCollection = problemCollectionService.getById(collection);
        //公开的集合 且在时间之内
        List<ProblemCollection> list = problemCollectionService.list(new QueryWrapper<ProblemCollection>()
                        .eq("public_collection", true)
                        .eq("status", true)
                        .and(i -> i.le("begin_time", now).ge("end_time", now)
                                .or(t -> t.apply("begin_time >= end_time"))))
                .stream().collect(Collectors.toList());
        list.add(0, groupCollection);
        return CommonResult.success().singleData(list);
    }

    private Long getCollection(Long groupId) {
        CollectionGroup group = getOne(new QueryWrapper<CollectionGroup>()
                .eq("group_id", groupId));
        return group.getCollectionId();
    }

}
