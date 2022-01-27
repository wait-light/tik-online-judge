package top.adxd.tikonlinejudge.executor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.TaskRank;
import top.adxd.tikonlinejudge.executor.service.ITaskRankService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/executor/task-rank")
public class TaskRankController {

    @Autowired
    private ITaskRankService taskRankService;


    @GetMapping("/list")
    public CommonResult list() {
        PageUtils.makePage();
        List<TaskRank> list = taskRankService.list();
        return CommonResult.success().listData(list);
    }

    @GetMapping("/{raceId}/problems")
    public CommonResult problems(@PathVariable("raceId") Long raceId){
        return taskRankService.taskProblemNames(raceId);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody TaskRank entity) {
        return taskRankService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return taskRankService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return taskRankService.removeById(id) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody TaskRank entity) {
        return taskRankService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{raceId}")
    public CommonResult info(@PathVariable("raceId") Long id) {
        return taskRankService.rank(id);
    }
}

