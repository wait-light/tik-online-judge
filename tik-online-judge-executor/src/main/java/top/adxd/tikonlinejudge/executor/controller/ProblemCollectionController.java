package top.adxd.tikonlinejudge.executor.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemCollection;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionItemService;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.executor.vo.ProblemSurvey;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/executor/problem-collection")
public class ProblemCollectionController {

    @Autowired
    private IProblemCollectionService problemCollectionService;

    @GetMapping("/public")
    public CommonResult list() {
//        PageUtils.makePage();
        List<ProblemCollection> list =
                problemCollectionService
                        .list(new QueryWrapper<ProblemCollection>()
                                .eq("public_collection", true));
        return CommonResult.success().add("list", list);
    }

    //TODO 个人加入的题集
    @GetMapping("/priate")
    public CommonResult privateList() {
//        PageUtils.makePage();
        List<ProblemCollection> list =
                problemCollectionService
                        .list(new QueryWrapper<ProblemCollection>()
                                .eq("public_collection", true));
        return CommonResult.success().listData(list);
    }

    /**
     *
     * @return
     */
    @GetMapping("/{collection-id}")
    public CommonResult collectionItem(@PathVariable("collection-id") Long collectionId){
        List<ProblemSurvey> problems = problemCollectionService.collectionsItem(collectionId);
        return CommonResult.success().listData(problems);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody ProblemCollection entity) {
        return problemCollectionService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return problemCollectionService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return problemCollectionService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody ProblemCollection entity) {
        return problemCollectionService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

}
