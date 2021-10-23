package top.adxd.tikonlinejudge.executor.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.Solution;
import top.adxd.tikonlinejudge.executor.service.ISolutionService;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.user.api.UserInfo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-10-14
 */
@RestController
@RequestMapping("/executor/solution")
public class SolutionController {
    
    @Autowired
    private ISolutionService solutionService;
    @DubboReference
    private UserInfo userInfo;

    @GetMapping("/hasSolution/{problemId}")
    public CommonResult hasSolution(@PathVariable("problemId")Long problemId,@RequestHeader("token") String token){
        Long uid = userInfo.uid(token);
        if (uid == null){
            return CommonResult.success().add("solutionId",0);
        }
        return CommonResult.success().add("solutionId",solutionService.hasSolution(uid,problemId));
    }

    @GetMapping("/solutions/{problemId}")
    public CommonResult solutionList(@PathVariable("problemId")Long problemId){
        List<Solution> solutions = solutionService.solutionList(problemId);
        return CommonResult.success().listData(solutions);
    }

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<Solution> list = solutionService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Solution entity,@RequestHeader String token) {
        Long uid = userInfo.uid(token);
        LocalDateTime now = LocalDateTime.now();
        entity.setUid(uid);
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return  solutionService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids){
        return solutionService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return solutionService.removeById(id) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Solution entity){
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateTime(now);
        return solutionService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        Solution entity = solutionService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

