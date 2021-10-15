package top.adxd.tikonlinejudge.executor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollectionItem;
import top.adxd.tikonlinejudge.executor.mapper.ProblemCollectionItemMapper;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionItemService;
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
public class ProblemCollectionItemServiceImpl extends ServiceImpl<ProblemCollectionItemMapper, ProblemCollectionItem> implements IProblemCollectionItemService {

    @Override
    public CommonResult deleteItem(Long problemID, Long collectionId) {
        int deleteCount = baseMapper.delete(new QueryWrapper<ProblemCollectionItem>()
                .eq("collection_id", collectionId)
                .eq("problem_id", problemID));
        return CommonResult.success("成功删除" + deleteCount +"项");
    }

    @Override
    public CommonResult addItem(Long problemID, Long collectionId) {
        ProblemCollectionItem problemCollectionItem = new ProblemCollectionItem();
        problemCollectionItem.setProblemId(problemID);
        problemCollectionItem.setCollectionId(collectionId);
        int insert = baseMapper.insert(problemCollectionItem);
        return CommonResult.success("成功添加" + insert + "项");
    }
}
