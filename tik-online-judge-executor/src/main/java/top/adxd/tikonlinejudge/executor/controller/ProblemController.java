package top.adxd.tikonlinejudge.executor.controller;


import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;

import top.adxd.tikonlinejudge.common.annotation.Role;
import top.adxd.tikonlinejudge.common.util.ServletUtils;
import top.adxd.tikonlinejudge.common.vo.CommonResult;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.executor.entity.Problem;
import top.adxd.tikonlinejudge.executor.entity.ProblemData;
import top.adxd.tikonlinejudge.executor.entity.ProblemTag;
import top.adxd.tikonlinejudge.executor.service.IProblemCollectionService;
import top.adxd.tikonlinejudge.executor.service.IProblemService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wait_light
 * @since 2021-09-22
 */
@RestController
@RequestMapping({"/executor/problem"})
public class ProblemController {
    @Autowired
    private IProblemService problemService;

    @GetMapping("/problem-name")
    private CommonResult problemsName(@RequestParam Long... pid) {
        return problemService.problemNames(pid);
    }

    @GetMapping("/list")
    public CommonResult list() {
        return problemService.problemListByName(ServletUtils.getParameter(IProblemService.NAME_SEARCH_KEY));
    }

    @PostMapping("")
    public CommonResult save(@RequestBody Problem entity) {
        entity.setSecretKey(RandomUtil.randomString(IProblemCollectionService.SECRET_KEY_LENGTH));
        return problemService.save(entity) ?
                CommonResult.success().setMsg("添加成功") :
                CommonResult.error().setMsg("添加失败");
    }

    @DeleteMapping("")
    public CommonResult deleteBatch(@RequestBody Long[] ids) {
        return problemService.removeByIds(Arrays.asList(ids)) ?
                CommonResult.success().setMsg("删除成功") :
                CommonResult.error().setMsg("删除失败");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return problemService.delete(id);
    }

    @PutMapping("/{id}")
    public CommonResult update(@RequestBody Problem entity) {
        entity.setUpdateTime(LocalDateTime.now());
        return problemService.updateById(entity) ?
                CommonResult.success().setMsg("更新成功") :
                CommonResult.error().setMsg("更新失败");
    }

    @GetMapping("/{id}")
    public CommonResult info(@PathVariable("id") Long id, @RequestParam(value = "secretKey", required = false) String secretKey) {
        Problem entity = problemService.getById(id);
        if (entity != null && !entity.getSecretKey().equals(secretKey)) {
            return CommonResult.permissionDeny("无权访问");
        }
        return entity != null ?
                CommonResult.success().singleData(entity) :
                CommonResult.error();
    }

    @GetMapping("/availableProblem/{collectionId}")
    public CommonResult getAvailableProblem(@PathVariable("collectionId") Long collectionId) {
        return problemService.getAvailableProblem(collectionId);
    }
}

