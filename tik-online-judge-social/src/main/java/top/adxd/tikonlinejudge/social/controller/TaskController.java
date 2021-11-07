package top.adxd.tikonlinejudge.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.social.entity.Task;
import top.adxd.tikonlinejudge.social.service.ITaskService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/social/task")
public class TaskController {
    
    @Autowired
    private ITaskService taskService;

    @GetMapping("/list")
    public CommonResult list(){
        PageUtils.makePage();
        List<Task> list = taskService.list();
        return CommonResult.success().listData(list);
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Task entity) {
        return  taskService.save(entity) ?
            CommonResult.success().setMsg("添加成功") :
            CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids){
        return taskService.removeByIds(Arrays.asList(ids)) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return taskService.removeById(id) ?
            CommonResult.success().setMsg("删除成功") :
            CommonResult.error().setMsg("删除失败");
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Task entity){
        return taskService.updateById(entity) ?
            CommonResult.success().setMsg("更新成功"):
            CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        Task entity = taskService.getById(id);
        return entity != null ?
            CommonResult.success().singleData(entity):
            CommonResult.error();
    }

}

