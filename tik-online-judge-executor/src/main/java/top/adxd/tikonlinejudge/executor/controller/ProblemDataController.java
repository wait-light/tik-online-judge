package top.adxd.tikonlinejudge.executor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import top.adxd.tikonlinejudge.executor.service.IProblemDataService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-18
 */
@RestController
@RequestMapping("/executor/problem-data")
public class ProblemDataController {
    
    @Autowired
    private IProblemDataService problemDataService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<ProblemData> list = problemDataService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody ProblemData entity) {
        return  problemDataService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids){
        return problemDataService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return problemDataService.removeById(id) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody ProblemData entity){
        return problemDataService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        ProblemData entity = problemDataService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

